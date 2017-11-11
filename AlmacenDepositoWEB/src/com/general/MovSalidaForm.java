package com.general;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovSalidaServiceRemote;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


@ManagedBean
@RequestScoped
public class MovSalidaForm {
	@EJB
	private MovSalidaServiceRemote moService;
	
	private List<MovSalidaDTO>  filteredMoss;
	

	public List<MovSalidaDTO> getFilteredMoss() {
		return filteredMoss;
	}

	public void setFilteredMoss(List<MovSalidaDTO> filteredMoss) {
		this.filteredMoss = filteredMoss;
	}

	public Collection<MovSalidaDTO> getMovSalidas() {
		return moService.listAll();
	}
	
	public void downloadExcel() throws IOException {
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

  
  
       try
       {
    	response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    	response.setContentType("application/vnd.ms-excel");// Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        response.setHeader("Content-Disposition", "attachment; filename=MovSalida.xls"); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet s = w.createSheet("Demo", 0);
        
        
        s.addCell(new Label(0, 0, "Código de Movimiento"));
        s.addCell(new Label(1, 0, "Fecha de Salida"));
        s.addCell(new Label(2, 0, "Almacen"));
        s.addCell(new Label(3, 0, "Centro"));
        s.addCell(new Label(4, 0, "Actividad"));
        s.addCell(new Label(5, 0, "Número Actividad"));
        s.addCell(new Label(6, 0, "Tipo Movimiento Origen"));
        s.addCell(new Label(7, 0, "Persona"));
        s.addCell(new Label(8, 0, "Importe Total"));
        s.addCell(new Label(9, 0, "Observaciones"));
        
        Collection<MovSalidaDTO> msList = moService.listAll();
        
        int i= 1;
        
        for (MovSalidaDTO me: msList){
        
       
        	  s.addCell(new Label(0, i, me.getId()));
              s.addCell(new Label(1, i, me.getFechaSalida().toString()));
              s.addCell(new Label(2, i, me.getNomAlmacen()));
              s.addCell(new Label(3, i, me.getCenString()));
              s.addCell(new Label(4, i, me.getActividad().toString()));
              s.addCell(new Label(5, i, me.getNomActividad()));
              s.addCell(new Label(6, i, me.getNomTipoMovOrigen()));
              s.addCell(new Label(7, i, me.getNroComprobante()));
              s.addCell(new Label(8, i, me.getImporteTotal().toString()));
              s.addCell(new Label(9, i, me.getObservaciones()));
                         
            i+=1; 
        	
        
        }
        
        
        w.write();
        w.close();
       } catch (Exception e) {	       }
   
       
       fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.

}

	


}
