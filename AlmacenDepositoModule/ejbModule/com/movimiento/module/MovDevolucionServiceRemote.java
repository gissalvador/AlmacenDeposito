package com.movimiento.module;

import javax.ejb.Remote;

import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;

@Remote
public interface MovDevolucionServiceRemote {

	MovSalidaDTO buscarMovSalida(String codMovSalida);

	void crearMovDevolucion(MovEntradaDTO movDevolucion);

}
