package com.movimiento.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.entities.Articulo;
import com.articulo.repository.ArticuloRepository;
import com.institucional.entities.Actividad;
import com.institucional.entities.Empleado;
import com.institucional.entities.Proveedor;
import com.institucional.module.ActividadService;
import com.institucional.module.EmpleadoService;
import com.institucional.module.ProveedorService;
import com.institucional.repository.ActividadRepository;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovEntradaFactory;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.Lote;
import com.movimiento.entities.MovEntrada;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.Solicitud;
import com.movimiento.repository.DetalleMERepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.ModeloRepository;
import com.movimiento.repository.MovEntradaRepository;
import com.movimiento.repository.MovOrigenRepository;

/**
 * Session Bean implementation class MovEntradaService
 */
/**
 * @author UTN
 *
 */
/**
 * @author UTN
 * 
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MovEntradaService implements MovEntradaServiceRemote {
	@EJB
	private MovEntradaRepository meRepository;

	@EJB
	private MovOrigenRepository moRepository;

	@EJB
	private ActividadRepository actRepository;

	@EJB
	private AlmacenRepository almRepository;

	@EJB
	private ModeloRepository modRepository;

	@EJB
	private ArticuloRepository artRepository;

	@EJB
	private LoteRepository lRepository;

	@EJB
	private DetalleMERepository detRepository;

	@EJB
	private MovEntradaValidations validator;

	@EJB
	private DetalleValidation validator2;

	@EJB
	private LoteService ls;

	@EJB
	private MovOrigenService mos;

	@EJB
	private SolicitudService ss;

	@EJB
	private ActividadService aService;

	@EJB
	private EmpleadoService eService;

	@EJB
	private ProveedorService pService;

	/**
	 * Default constructor.
	 */
	public MovEntradaService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * Encargado de la lógica de creación de un movimiento de entrada
	 * 
	 * 
	 **/

	public String crearMovEntrada(MovEntradaDTO movEntrada,
			Collection<DetMovEntradaDTO> detallesMovEntrada) {

		List<ValidationError> errors = validator
				.validaraddMovEntrada(movEntrada);
		
		
		if (detallesMovEntrada.size() == 0) {

			errors.add(new ValidationError("Detalle Movimiento Entrada",
					"El detalle del movimiento no puede ser nulo"));
		}
		
		if (errors.size() > 0) {
			throw new BusinessException(
					"Errores al agregar el Movimiento de Entrada.", errors);
		}

		for (DetMovEntradaDTO ddto : detallesMovEntrada) {

			errors = validator2.validarDetalle(ddto);
			if (errors.size() > 0) {
				throw new BusinessException(
						"Errores al agregar el Movimiento de Entrada.", errors);
			}

		}

		Float importetotal = (float) 0;

		List<DetalleMovEntrada> detList;
		detList = new ArrayList<DetalleMovEntrada>();

		lRepository = new LoteRepository();

		Collection<Lote> loteList = new ArrayList<Lote>();

		for (DetMovEntradaDTO ddto : detallesMovEntrada) {

			if (loteList.size() == 0) {

				Lote l = new Lote();

				l = ls.addLote(ddto, movEntrada);

				loteList.add(l);

				DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

				dme.setLote(l);

				importetotal += dme.getCantidadME()
						* dme.getImporteUnitarioME();

				detList.add(dme);

			} else {

				boolean art = false;

				for (Lote lote : loteList) {

					if (lote.getArticulo().getCodArticulo() == ddto
							.getCodArticulo()) {

						Articulo articulo = artRepository.get(ddto
								.getCodArticulo());

						if (articulo.getVto() == true) {

							if (lote.getFechaVto().equals(ddto.getFechaVto())
									&& lote.getModelo().getCodModelo() == ddto
											.getModelo()) {

								ls.addLoteAlmacen(movEntrada.getAlmacen(),
										lote, ddto.getCantidad(),
										ddto.getUbicacion());

								art = true;

								DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

								dme.setLote(lote);

								importetotal += dme.getCantidadME()
										* dme.getImporteUnitarioME();

								detList.add(dme);
							}

						} else {

							if (lote.getModelo().getCodModelo() == ddto
											.getModelo()) {

								ls.addLoteAlmacen(movEntrada.getAlmacen(),
										lote, ddto.getCantidad(),
										ddto.getUbicacion());

								art = true;

								DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

								dme.setLote(lote);

								importetotal += dme.getCantidadME()
										* dme.getImporteUnitarioME();

								detList.add(dme);
							}

						}

					}

				}

				if (!art) {

					Lote l = new Lote();

					l = ls.addLote(ddto, movEntrada);

					loteList.add(l);

					DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

					dme.setLote(l);

					importetotal += dme.getCantidadME()
							* dme.getImporteUnitarioME();

					detList.add(dme);
				}
			}
		}

		movEntrada.setImporteTotal(importetotal);

		MovEntrada movEntradaNuevo = addMovEntrada(movEntrada, detList);
		//System.out.println(movEntradaNuevo.getCodMovEntrada());
		return movEntradaNuevo.getCodMovEntrada();
	}

	/**
	 * 
	 * Crear una Colleccion de detalle del Movimiento de Entrada
	 * 
	 * 
	 **/

	public DetalleMovEntrada addDetalleMovEntrada(DetMovEntradaDTO ddto) {

		DetalleMovEntrada dme = new DetalleMovEntrada();

		dme.setCantidadME(ddto.getCantidad());
		dme.setImporteUnitarioME(ddto.getImporteUnitario());
		dme.setNroSerie_Proveedor(ddto.getNroSerie_Proveedor());

		detRepository.add(dme);

		return dme;
	}

	/**
	 * 
	 * Crear Movimiento de Entrada
	 * 
	 * 
	 **/

	public MovEntrada addMovEntrada(MovEntradaDTO movEntrada,
			List<DetalleMovEntrada> detList) {

		Solicitud s = ss.addSolicitud(movEntrada);

		Actividad act = aService.getActividad(movEntrada.getActividad());

		MovOrigen mo = mos.addMovOrigen(movEntrada);

		MovEntrada movEntradaNuevo = new MovEntrada();

		movEntradaNuevo.setFechaMovEntrada(movEntrada.getFechaIngreso());
		movEntradaNuevo.setObservaciones(movEntrada.getObservaciones());
		movEntradaNuevo.setTotalMovEntrada(movEntrada.getImporteTotal());
		movEntradaNuevo.setActividad(act);
		movEntradaNuevo.setMovOrigen(mo);
		movEntradaNuevo.setSolicitud(s);
		movEntradaNuevo.setDetalleME(detList);
		movEntradaNuevo.setAlmacen(almRepository.get(movEntrada.getAlmacen()));

		movEntradaNuevo.setUsuario(movEntrada.getUsuario());
		Date date = new Date();
		movEntradaNuevo.setFechaCreado(date);

		meRepository.add(movEntradaNuevo);

		return movEntradaNuevo;

	}

	public Collection<MovEntradaDTO> listAll() {

		return MovEntradaFactory.getMovEntradaDTO(meRepository.getAll());
	}

	@Override
	public MovEntradaDTO findById(String id) {
		// TODO Auto-generated method stub
		return MovEntradaFactory.getMovEntradaDTO(meRepository.get(id));
	}

	@Override
	public MovEntradaDTO cargarPP(MovEntradaDTO movEntrada) {
		if (movEntrada.getCodTipMovOrigen() == 1) {
			Empleado emp = new Empleado();

			emp = eService.getEmpleado(movEntrada.getCuitlegajo());
			movEntrada.setNomPerPro(emp.getNomPersona());
			movEntrada.setPerPro("Empleado: ");

		} else {

			Proveedor pro = new Proveedor();
			//System.out.println(movEntrada.getCuitlegajo());
			pro = pService.getProveedor(movEntrada.getCuitlegajo());
			movEntrada.setNomPerPro(pro.getAlias());
			movEntrada.setPerPro("Proveedor: ");
		}

		return movEntrada;

	}

}
