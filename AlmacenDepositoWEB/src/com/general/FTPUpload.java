package com.general;


import java.io.FileInputStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.net.ftp.FTPClient;


import java.io.IOException;



@ManagedBean
@ApplicationScoped
public class FTPUpload {
	
    public void uploadDemo() {
        // TODO Auto-generated constructor stub
    	
    	FTPClient client = new FTPClient();
    	FileInputStream fis = null;
    	
    	try {
    	    client.connect("127.0.0.1");
    	    client.login("ftp", "admin");

    	    //
    	    // Create an InputStream of the file to be uploaded
    	    //
    	    String filename = "Touch.dat";
    	    fis = new FileInputStream(filename);

    	    //
    	    // Store file to server
    	    //
    	    client.storeFile(filename, fis);
    	    client.logout();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	} finally {
    	    try {
    	        if (fis != null) {
    	            fis.close();
    	        }
    	        client.disconnect();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	}
        
    	
    	
    }
	
	
}
