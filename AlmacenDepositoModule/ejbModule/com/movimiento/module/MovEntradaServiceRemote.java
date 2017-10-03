package com.movimiento.module;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.Remote;



import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.MovEntradaDTO;

@Remote
public interface MovEntradaServiceRemote {

	String crearMovEntrada(MovEntradaDTO movEntrada, Collection<DetMovEntradaDTO> detallesMovEntrada) throws IOException;
	
	Collection<MovEntradaDTO> listAll();

	MovEntradaDTO findById(String id);

	MovEntradaDTO cargarPP(MovEntradaDTO movEntrada);

}
