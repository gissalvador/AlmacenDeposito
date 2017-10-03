package com.movimiento.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.MovSalidaDTO;

@Remote
public interface MovSalidaServiceRemote {

	Collection<LoteDTO> buscarLotes(MovSalidaDTO movSalida,
			Collection<DetMovSalidaDTO> detallesMovSalida);

	void crearMovSalida(MovSalidaDTO movSalida);

	Collection<MovSalidaDTO> listAll();

	MovSalidaDTO findById(String id);

	MovSalidaDTO cargarPP(MovSalidaDTO movSalida);

}
