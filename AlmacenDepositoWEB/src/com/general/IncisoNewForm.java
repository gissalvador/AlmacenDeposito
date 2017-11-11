package com.general;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.institucional.dto.IncisoDTO;
import com.institucional.dto.PParcialDTO;
import com.institucional.dto.PPrincipalDTO;
import com.institucional.module.IncisioServiceRemote;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;



@ManagedBean
@RequestScoped
public class IncisoNewForm {
	
	@EJB
	private IncisioServiceRemote iService;
	
	private Collection<IncisoDTO> incisos;

	public Collection<IncisoDTO> getInciso() {
		return iService.listAll();
	}
	
	@PostConstruct
	private void initialize() {
		
		incisos = new ArrayList<IncisoDTO>();
		
		incisos = this.getInciso();
		
		}
	
	
	public Collection<IncisoDTO> getIncisos() {
		return incisos;
	}

	public Collection<IncisoDTO> getRepPresupuestrario() {
		return iService.listRepPresupuesto();
	}
	
public void downloadExcel() throws IOException {
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

  
  
       try
       {
    	response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    	response.setContentType("application/vnd.ms-excel");// Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        response.setHeader("Content-Disposition", "attachment; filename=RepotePresupuestario.xls"); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet s = w.createSheet("Demo", 0);
        
        
        s.addCell(new Label(0, 0, "Inciso"));
        s.addCell(new Label(1, 0, "Detalle"));
        s.addCell(new Label(2, 0, "Total Entrada"));
        s.addCell(new Label(3, 0, "Total Salida"));
      
        
        Collection<IncisoDTO> iList = iService.listRepPresupuesto();
        
        int i= 1;
        
        for (IncisoDTO inc : iList){
        	
        	Integer nroinc= inc.getNroInciso();
        	
        	
        	  s.addCell(new Label(0, i, nroinc.toString()));
              s.addCell(new Label(1, i, inc.getNomInciso()));
              s.addCell(new Label(2, i, inc.getMovEntrada().toString()));
              s.addCell(new Label(3, i, inc.getMovSalida().toString()));
              
              for(PPrincipalDTO ppri :  inc.getListPPrincipalDTO()){
            	             	          	  
            	  i+=1; 
            	  
            	  s.addCell(new Label(0, i, ppri.getNroPPrincipal().toString()));
                  s.addCell(new Label(1, i, ppri.getNomPPrincipal()));
                  s.addCell(new Label(2, i, ppri.getMovEntrada().toString()));
                  s.addCell(new Label(3, i, ppri.getMovSalida().toString()));
                  
                  for(PParcialDTO ppar : ppri.getListPParcialDTO()){
                	  
                	  i+=1; 
                	  Integer nroppar=ppar.getNroPParcial();
                	  
                	  s.addCell(new Label(0, i, nroppar.toString()));
                      s.addCell(new Label(1, i, ppar.getNomClasificacion()));
                      s.addCell(new Label(2, i, ppar.getMovEntrada().toString()));
                      s.addCell(new Label(3, i, ppar.getMovSalida().toString()));
                	  
                  }
            	  
            	  
              }
                         
            i+=1; 
        	        
        }
        
        
        w.write();
        w.close();
       } catch (Exception e) {	       }
   
       
       fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.

}

	
}
