package com.movimiento.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovSalidaDTO;

@Remote
public interface MovTrasladoSalidaServiceRemote {

	Collection<LoteDTO> buscarLotes(MovSalidaDTO movTrasladoSalida,
			Collection<DetMovSalidaDTO> detallesMovTrasladoSalida);

	void crearMovTrasladoSalida(MovSalidaDTO movTrasladoSalida);
	
	
	

}
