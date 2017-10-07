package com.movimiento.module;

import javax.ejb.Remote;

import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;

@Remote
public interface MovAjustarServiceRemote {

	LoteDTO buscarLotes(String codLote);

	void crearMovAjustar(MovEntradaDTO movAjustar);

	void crearMovAjustarSalida(MovSalidaDTO movAjustarFaltante);

	void crearMovAjustarDesajuste(LoteDTO lote, String usuario);

}
