package com.general;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.movimiento.module.LoteServiceRemote;
import com.movimiento.dto.LoteDTO;

/**
 * Formulario que muestra la grilla de Almacenes.
 * 
 * @author Gisella
 * 
 */
@ManagedBean
@RequestScoped
public class loteForm {

	@EJB
	private LoteServiceRemote loteService;

	private List<LoteDTO> filteredLots;

	private LoteDTO loteDTO;
		
	public List<LoteDTO> getFilteredLots() {
		return filteredLots;
	}

	public void setFilteredLots(List<LoteDTO> filteredLots) {
		this.filteredLots = filteredLots;
	}

	public Collection<LoteDTO> getLotes() {
		return loteService.listAll();
	}

	public LoteDTO getLoteDTO() {
		return loteDTO;
	}

	public void setLoteDTO(LoteDTO loteDTO) {
		this.loteDTO = loteDTO;
	}

	public void downloadExcel() throws IOException {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

		try {
			response.reset(); // Some JSF component library or some Filter might
								// have set some headers in the buffer
								// beforehand. We want to get rid of them, else
								// it may collide.
			response.setContentType("application/vnd.ms-excel");// Check
																// http://www.iana.org/assignments/media-types
																// for all
																// types. Use if
																// necessary
																// ExternalContext#getMimeType()
																// for
																// auto-detection
																// based on
																// filename.
			response.setHeader("Content-Disposition", "attachment; filename=lotes.xls"); // The
																							// Save
																							// As
																							// popup
																							// magic
																							// is
																							// done
																							// here.
																							// You
																							// can
																							// give
																							// it
																							// any
																							// file
																							// name
																							// you
																							// want,
																							// this
																							// only
																							// won't
																							// work
																							// in
																							// MSIE,
																							// it
																							// will
																							// use
																							// current
																							// request
																							// URL
																							// as
																							// file
																							// name
																							// instead.

			WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet s = w.createSheet("Demo", 0);

			s.addCell(new Label(0, 0, "Código del Lote"));
			s.addCell(new Label(1, 0, "Almacen"));
			s.addCell(new Label(2, 0, "Articulo"));
			s.addCell(new Label(3, 0, "Modelo"));
			s.addCell(new Label(4, 0, "Fecha de Vencimiento"));
			s.addCell(new Label(5, 0, "Stock"));
			s.addCell(new Label(6, 0, "Ubicación"));

			Collection<LoteDTO> loteList = loteService.listAll();

			for (int i = 1; i <= loteList.size(); i++) {

				for (LoteDTO ldto : loteList) {

					s.addCell(new Label(0, i, ldto.getCodLote()));
					// s.addCell(new Label(1, i, ldto.getAlmacen()));
					s.addCell(new Label(2, i, ldto.getArticulo()));
					// s.addCell(new Label(3, i, ldto.getModelo()));
					s.addCell(new Label(4, i, ldto.getFechaVto().toString()));
					// s.addCell(new Label(5, i,
					// ldto.getSockActual().toString()));
					// s.addCell(new Label(6, i, ldto.getUbicacion()));

				}
			}

			w.write();
			w.close();
		} catch (Exception e) {
		}

		fc.responseComplete(); // Important! Otherwise JSF will attempt to
								// render the response which obviously will fail
								// since it's already written with a file and
								// closed.

	}

}
