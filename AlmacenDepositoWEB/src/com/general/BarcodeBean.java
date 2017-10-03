package com.general;

import java.io.File;
import java.io.FileInputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean
@ApplicationScoped
public class BarcodeBean {
	
	private StreamedContent barcode;
	public StreamedContent createBarcodeByCode() {
	    FacesContext context = FacesContext.getCurrentInstance();
	    
	    
	    if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
	        // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
	        return new DefaultStreamedContent();
	    } else {
	    	
	    	String code = context.getExternalContext().getRequestParameterMap().get("id");
	        barcode = null;
	        
	        if (code == null || code.trim().equals("")) {
	            return null;
	        }
	        File barcodeFile = new File(code);
	        try {
	            BarcodeImageHandler.saveJPEG(BarcodeFactory.createCode128B(code), barcodeFile);
	            barcode = new DefaultStreamedContent(new FileInputStream(barcodeFile), "image/jpeg");
	        } catch (Exception ex) {
	            System.out.println("ex = " + ex.getMessage());
	        }

	        return barcode;
	    }
	}

}
