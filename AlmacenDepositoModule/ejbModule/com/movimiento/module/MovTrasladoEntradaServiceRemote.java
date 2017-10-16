package com.movimiento.module;

import javax.ejb.Remote;

import com.institucional.dto.AlmacenDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;

@Remote
public interface MovTrasladoEntradaServiceRemote {

	MovSalidaDTO buscarMovSalida(String nroComprobante);

	AlmacenDTO buscarAlmacen(String nroComprobante);

	void crearMovTrasladoEntrada(MovEntradaDTO movTrasladoEntrada);

}
