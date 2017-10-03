package com.movimiento.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.movimiento.dto.LoteDTO;


@Remote
public interface LoteServiceRemote {
	
	Collection<LoteDTO> listAll();

	LoteDTO findById(String id);

}
