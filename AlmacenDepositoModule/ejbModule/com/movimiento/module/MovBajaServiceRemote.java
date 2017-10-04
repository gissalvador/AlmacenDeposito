package com.movimiento.module;


import javax.ejb.Remote;

import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovSalidaDTO;

@Remote
public interface MovBajaServiceRemote {

	LoteDTO buscarLotes(MovSalidaDTO movSalida, String codLote);

	void crearMovBaja(MovSalidaDTO movBaja);

}
