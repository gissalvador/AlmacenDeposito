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

import com.articulo.dto.GrupoDTO;
import com.articulo.modules.GrupoServiceRemote;

@ManagedBean
@RequestScoped
public class GrupoForm {
	@EJB
	private GrupoServiceRemote gService;
	
	private List<GrupoDTO>  filteredGrus;
	
	
	public List<GrupoDTO> getFilteredGrus() {
		return filteredGrus;
	}


	public void setFilteredGrus(List<GrupoDTO> filteredGrus) {
		this.filteredGrus = filteredGrus;
	}


	public Collection<GrupoDTO> getGrupos() {
		return gService.listAll();
	}
	
	
	public void downloadExcel() throws IOException {
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

  
  
       try
       {
    	response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
    	response.setContentType("application/vnd.ms-excel");// Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
        response.setHeader("Content-Disposition", "attachment; filename=Grupos.xls"); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet s = w.createSheet("Demo", 0);
        
        
        s.addCell(new Label(0, 0, "Código del Grupo"));
        s.addCell(new Label(1, 0, "Nombre del Grupo"));
        s.addCell(new Label(2, 0, "Descripción del Grupo"));
        
        Collection<GrupoDTO> grupoList = gService.listAll();
        
     
        int i=1;
        	
        for (GrupoDTO gdto: grupoList){
        
        	
        	  s.addCell(new Label(0, i, gdto.getCodGrupo().toString()));
              s.addCell(new Label(1, i, gdto.getNomGrupo()));
              s.addCell(new Label(2, i, gdto.getDesGrupo()));
              i+=1;
        	
        }
        
        
        
        w.write();
        w.close();
       } catch (Exception e) {	       }
   
       
       fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.

}

		
	

}