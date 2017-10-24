package com.seguridad;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.email.module.EmailService;
import com.email.module.RandomInteger;
import com.seguridad.dto.UsuarioDTO;
import com.seguridad.entities.CambioClave;
import com.seguridad.repository.CambioClaveRepository;

/**
 * Session Bean implementation class CambioClaveService
 */
@Stateless
@LocalBean
public class CambioClaveService implements CambioClaveServiceRemote {

	@EJB
	private UsuariosService uService;

	@EJB
	private UsuariosServiceValidations validador;

	@EJB
	private CambioClaveRepository ccRepository;

	@EJB
	private EmailService eService;

	/**
	 * Default constructor.
	 */
	public CambioClaveService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void recuperarClave(String login) {
		// TODO Auto-generated method stub

		List<ValidationError> errors = validador.validarActivarUsuario(login);

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Usuario.", errors);
		}

		try {
			UsuarioDTO usu = uService.findByLogin(login);

			CambioClave cclave = new CambioClave();

			Date hoy = new Date();

			Date maniana = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(hoy);
			cal.add(Calendar.DATE, 1);
			maniana = cal.getTime();

			Integer codR = RandomInteger.randInt(1000, 9999);

			cclave.setUsado(false);
			cclave.setUsuario(login);
			cclave.setFinVigencia(maniana);
			cclave.setCodRecuperar(codR.toString());

			ccRepository.add(cclave);

			eService.send(usu.getEmail(), "Recuperar Contraseña",
					"Para recupérar su contraseña ingrese el siguiente código de reuperación: " + codR);

		} catch (Exception e) {

		}

	}

	@Override
	public void comprobarCodigo(String login, String codigo) {
		// TODO Auto-generated method stub

		List<ValidationError> errors = validador.validarActivarUsuario(login);

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Usuario.", errors);
		}

		CambioClave cclave = this.getcambioClave(login, codigo);

		if (cclave == null) {

			errors.add(new ValidationError("Código Validadión", "El código de validación es incorrecto."));

		} else {

			if (cclave.getUsado() == false) {

				Date date = new Date();

				if (cclave.getFinVigencia().after(date)) {

					//System.out.println("No vencido");

					cclave.setUsado(true);

				} else {

					errors.add(new ValidationError("Código Validadión",
							"El código de validación venció el día: " + cclave.getFinVigencia()));

				}

			} else {

				errors.add(new ValidationError("Código Validadión",
						"El código de validación ya ha sido utilizado por el usuario"));

			}

		}

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Usuario.", errors);
		}

		// System.out.println(cclave.getUsado());

	}

	public CambioClave getcambioClave(String login, String codigo) {
		// TODO Auto-generated method stub

		List<CambioClave> cambioClavees = ccRepository.getcodigo(login, codigo);

		CambioClave cambioClave = new CambioClave();
		cambioClave = null;

		for (CambioClave cambioClave1 : cambioClavees) {

			cambioClave = cambioClave1;

			break;

		}

		return cambioClave;
	}

	@Override
	public void cambiarContrasenia(String login, String plainTextPasswordNuevo) {
		// TODO Auto-generated method stub

		uService.actualizarPassword(login, plainTextPasswordNuevo);

	}
}
