package com.institucional.module;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.institucional.dto.IncisoDTO;
import com.institucional.dto.IncisoFactory;
import com.institucional.dto.PParcialDTO;
import com.institucional.dto.PPrincipalDTO;
import com.institucional.repository.IncisoRepository;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovEntradaService;
import com.movimiento.module.MovSalidaService;
import com.movimiento.module.MovSalidaServiceRemote;

/**
 * Session Bean implementation class IncisioService
 */
@Stateless
@LocalBean
public class IncisioService implements IncisioServiceRemote {

	@EJB
	private IncisoRepository iRepository;
	@EJB
	private MovEntradaService meService;
	@EJB
	private MovSalidaService moService;

	/**
	 * Default constructor.
	 * 
	 * 
	 */
	public IncisioService() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public IncisoDTO findById(Integer incisoId) {
		return IncisoFactory.getIncisoDTO(iRepository.get(incisoId));
	}

	@Override
	public Collection<IncisoDTO> listAll() {
		return IncisoFactory.getIncisoDTO(iRepository.getAll());
	}

	@Override
	public Collection<IncisoDTO> listRepPresupuesto() {
		// TODO Auto-generated method stub

		Collection<IncisoDTO> listaInciso = IncisoFactory.getIncisoDTO(iRepository.get2y4());

		Collection<MovEntradaDTO> listMovEntrada = meService.listAll();

		Collection<MovSalidaDTO> listMovSalida = moService.listAll();

		for (MovEntradaDTO me : listMovEntrada) {

			for (DetMovEntradaDTO de : me.getListDetalleDTO()) {

				this.sumar(listaInciso, de.getPparcial(), de.getImporteUnitario() * de.getCantidad()); 

			}

		}

		for (MovSalidaDTO mo : listMovSalida) {

			for (DetMovSalidaDTO ds : mo.getDetalleMS()) {

				this.sumarMS(listaInciso, ds.getPparcial(), ds.getImporteUnitario()*ds.getCantidad()); 

			}

		}

		return listaInciso;

	}

	public void sumar(Collection<IncisoDTO> listaInciso, Integer inc, Float impoteUnitario) {

		Float sum = 0f;
		Float totalI = 0f;
		Float totalPPri = 0f;

		for (IncisoDTO inciso : listaInciso) {
			
			totalI = 0f;

			for (PPrincipalDTO pprincipal : inciso.getListPPrincipalDTO()) {
				
				
				totalPPri = 0f;

				for (PParcialDTO pparcial : pprincipal.getListPParcialDTO()) {

					sum = 0f;

					if (inc == pparcial.getNroPParcial()) {

						sum = impoteUnitario;

						pparcial.setMovEntrada(sum + pparcial.getMovEntrada());
						
						totalPPri+=sum;


					}

				}

				totalI+=totalPPri;

				pprincipal.setMovEntrada(totalPPri + pprincipal.getMovEntrada());

			}

			inciso.setMovEntrada(totalI + inciso.getMovEntrada());


		}

	}

	public void sumarMS(Collection<IncisoDTO> listaInciso, Integer inc, Float impoteUnitario) {

		Float sum = 0f;
		Float totalI = 0f;
		Float totalPPri = 0f;

		for (IncisoDTO inciso : listaInciso) {
			
			totalI = 0f;

			for (PPrincipalDTO pprincipal : inciso.getListPPrincipalDTO()) {
				
				totalPPri = 0f;

				for (PParcialDTO pparcial : pprincipal.getListPParcialDTO()) {

					sum = 0f;

					if (inc == pparcial.getNroPParcial()) {

						sum = impoteUnitario;

						pparcial.setMovSalida(sum + pparcial.getMovSalida());
						
						totalPPri+=sum;

					}
					
					

				}
				
				totalI+=totalPPri;

				pprincipal.setMovSalida(totalPPri + pprincipal.getMovSalida());

			}

			inciso.setMovSalida(totalI + inciso.getMovSalida());

		}

	}

}
