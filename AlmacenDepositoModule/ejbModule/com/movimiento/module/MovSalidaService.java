package com.movimiento.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.entities.Articulo;
import com.articulo.repository.ArticuloRepository;
import com.institucional.entities.Actividad;
import com.institucional.entities.Empleado;
import com.institucional.module.ActividadService;
import com.institucional.module.EmpleadoService;
import com.institucional.repository.ActividadRepository;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.LoteFactory;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.dto.MovSalidaFactory;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.DetalleMovSalida;
import com.movimiento.entities.Lote;
import com.movimiento.entities.LoteAlmacen;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.MovSalida;
import com.movimiento.repository.DetalleMERepository;
import com.movimiento.repository.DetalleMSrepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class MovSalidaService
 */
@Stateless
@LocalBean
public class MovSalidaService implements MovSalidaServiceRemote {

	@EJB
	private ArticuloRepository aRepository;

	@EJB
	private LoteRepository lRepository;

	@EJB
	private AlmacenRepository almRepository;

	@EJB
	private MovSalidaValidations validator;

	@EJB
	private DetalleSalidaValidation validator2;

	@EJB
	private MovSalidaRepository msRepository;

	@EJB
	private DetalleMSrepository detRepository;

	@EJB
	private DetalleMERepository detentradaRepository;

	@EJB
	private ActividadRepository actRepository;

	@EJB
	private ActividadService aService;

	@EJB
	private MovOrigenService mos;

	@EJB
	private SolicitudService ss;

	@EJB
	private EmpleadoService eService;

	@EJB
	private LoteService lservice;

	/**
	 * Default constructor.
	 */
	public MovSalidaService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<LoteDTO> buscarLotes(MovSalidaDTO movSalida, Collection<DetMovSalidaDTO> detallesMovSalida) {

		List<ValidationError> errors = validator.validaraddMovSalida(movSalida);

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Movimiento de Salida.", errors);
		}

		for (DetMovSalidaDTO ddto : detallesMovSalida) {

			errors = validator2.validarDetalle(ddto);
			if (errors.size() > 0) {
				throw new BusinessException("Errores al agregar el Movimiento de Entrada.", errors);
			}

		}

		List<LoteDTO> listlDTO = new ArrayList<LoteDTO>();

		for (DetMovSalidaDTO ddto : detallesMovSalida) {

			float cantidad = ddto.getCantidad();

			List<Lote> lotes;

			Articulo a = aRepository.get(ddto.getCodArticulo());

			if (a.getVto() == false) {

				if (a.getAct() == false) {

					lotes = lRepository.buscarlote(ddto.getCodArticulo());

				} else {

					lotes = lRepository.buscarXact(ddto.getCodArticulo(),
							aService.getActividad(movSalida.getActividad()).getCodActividad());

				}

			} else {

				lotes = lRepository.buscarXvto(ddto.getCodArticulo());

			}

			if (lotes.size() == 0) {

				errors.add(new ValidationError("Cantidad:",
						"Los lotes solicitados no tienen stock en el almacen " + movSalida.getAlmacen()));

			} else {

				for (Lote l : lotes) {

					for (LoteAlmacen la : l.getLoteAlmacenes()) {

						if (a.getVto() == true) {

							Date hoy = new Date();

							// System.out.println(l.getFechaVto().before(hoy));

							if (l.getFechaVto().before(hoy)) {

								lservice.addHistorialEstadoLote(6, l, movSalida.getUsuario());

							} else {

								if (la.getAlmacen().getCodAlmacen() == movSalida.getAlmacen()) {

									LoteDTO lDTO = new LoteDTO();

									if (la.getCantidad() >= cantidad && cantidad != 0) {

										lDTO = LoteFactory.getLoteDTO(l);

										lDTO.setCantidad(cantidad);

										cantidad = 0;

										listlDTO.add(lDTO);

									} else if (la.getCantidad() <= cantidad && cantidad != 0) {

										lDTO = LoteFactory.getLoteDTO(l);

										lDTO.setCantidad(la.getCantidad());

										listlDTO.add(lDTO);

										cantidad -= la.getCantidad();

									}
								}

							}
							
						} else {

							if (la.getAlmacen().getCodAlmacen() == movSalida.getAlmacen()) {

								LoteDTO lDTO = new LoteDTO();

								if (la.getCantidad() >= cantidad && cantidad != 0) {

									lDTO = LoteFactory.getLoteDTO(l);

									lDTO.setCantidad(cantidad);

									cantidad = 0;

									listlDTO.add(lDTO);

								} else if (la.getCantidad() <= cantidad && cantidad != 0) {

									lDTO = LoteFactory.getLoteDTO(l);

									lDTO.setCantidad(la.getCantidad());

									listlDTO.add(lDTO);

									cantidad -= la.getCantidad();

								}
							}

						}

					}

				}
			}

		}

		return listlDTO;

	}

	@Override
	public void crearMovSalida(MovSalidaDTO movSalida) {

		// TODO Auto-generated method stub

		List<ValidationError> errors = validator.validaraddMovSalida(movSalida);
		if (movSalida.getDetalleMS().size() == 0) {

			errors.add(new ValidationError("Detalle Movimiento Salida", "El detalle del movimiento no puede ser nulo"));
		}
		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Movimiento de Salida.", errors);
		}

		Float importetotal = (float) 0;

		List<DetalleMovSalida> detList;

		detList = new ArrayList<DetalleMovSalida>();

		int contAjuste = 0;

		for (DetMovSalidaDTO ddto : movSalida.getDetalleMS()) {

			Lote lote = lRepository.get(ddto.getLoteId());

			if (ddto.isAjustar()) {

				lservice.addHistorialEstadoLote(3, lote, movSalida.getUsuario());

				contAjuste += 1;

			} else {

				for (LoteAlmacen la : lote.getLoteAlmacenes()) {

					if (la.getAlmacen().getCodAlmacen().equals(movSalida.getAlmacen())) {

						Float cantidad = la.getCantidad() - ddto.getCantidad();

						la.setCantidad(cantidad);

					}

					Float stock = lote.getStockActual() - ddto.getCantidad();

					lote.setStockActual(stock);

					DetalleMovSalida dms = addDetalleMovSalida(ddto.getCantidad(), lote);

					importetotal += dms.getImporteUnitarioMS() * dms.getCantidadMS();

					detList.add(dms);

					break;

				}

				if (lote.getStockActual() == 0) {

					lservice.addHistorialEstadoLote(5, lote, movSalida.getUsuario());

				} else if (lote.getStockActual() > 0) {

					lservice.addHistorialEstadoLote(2, lote, movSalida.getUsuario());

				}

			}

		}

		/*
		 * Si el movimiento de salida solo tiene lotes para ser ajustados no se
		 * crea un movimiento de salida pero si cambia el historial de estado
		 * lote a Ajustar
		 */

		if (movSalida.getDetalleMS().size() != contAjuste) {

			movSalida.setImporteTotal(importetotal);

			addMovSalida(movSalida, detList);
		}

	}

	public DetalleMovSalida addDetalleMovSalida(float cant, Lote l) {

		String np = null;
		Float iu = null;

		DetalleMovSalida dms = new DetalleMovSalida();

		List<String> ls = detentradaRepository.buscarxLote(l.getCodLote());

		for (String s : ls) {

			DetalleMovEntrada dme = new DetalleMovEntrada();

			dme = detentradaRepository.get(s);

			np = dme.getNroSerie_Proveedor();
			iu = dme.getImporteUnitarioME();

			break;

		}

		dms.setCantidadMS(cant);
		dms.setImporteUnitarioMS(iu);
		dms.setNroSerie_Proveedor(np);
		dms.setLote(l);

		detRepository.add(dms);

		return dms;
	}

	public MovSalida addMovSalida(MovSalidaDTO movSalida, List<DetalleMovSalida> detList) {

		MovOrigen mo = mos.addMovOrigen(movSalida, 2);

		Actividad act = aService.getActividad(movSalida.getActividad());

		MovSalida movSalidaNuevo = new MovSalida();

		movSalidaNuevo.setFechaMovSalida(movSalida.getFechaSalida());
		movSalidaNuevo.setObservaciones(movSalida.getObservaciones());
		movSalidaNuevo.setTotalMovSalida(movSalida.getImporteTotal());
		movSalidaNuevo.setActividad(act);
		movSalidaNuevo.setMovOrigen(mo);
		movSalidaNuevo.setDetalleME(detList);
		movSalidaNuevo.setAlmacen(almRepository.get(movSalida.getAlmacen()));
		movSalidaNuevo.setUsuario(movSalida.getUsuario());
		Date date = new Date();
		movSalidaNuevo.setFechaCreado(date);

		msRepository.add(movSalidaNuevo);

		return movSalidaNuevo;

	}

	public Collection<MovSalidaDTO> listAll() {

		return MovSalidaFactory.getMovSalidaDTO(msRepository.getAll());
	}

	@Override
	public MovSalidaDTO findById(String id) {
		// TODO Auto-generated method stub
		return MovSalidaFactory.getMovSalidaDTO(msRepository.get(id));
	}

	@Override
	public MovSalidaDTO cargarPP(MovSalidaDTO movSalida) {
		if (movSalida.getCodTipMovOrigen() == 1) {
			Empleado emp = new Empleado();

			emp = eService.getEmpleado(movSalida.getLegajo());
			movSalida.setNomPersona(emp.getNomPersona());

		} else {

			/*
			 * Proveedor pro = new Proveedor();
			 * System.out.println(movSalida.getCuitlegajo()); pro =
			 * pService.getProveedor(movSalida.getCuitlegajo());
			 * movSalida.setNomPerPro(pro.getAlias());
			 * movSalida.setPerPro("Proveedor: ");
			 */
		}

		return movSalida;

	}

}
