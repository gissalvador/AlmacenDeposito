package com.articulo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.articulo.entities.Articulo;

public class ArticuloDTOFactory {
public static ArticuloDTO getArticuloDTO(Articulo articulo){
	if(articulo == null){
		return null;
	}
	ArticuloDTO result = new ArticuloDTO();
	result.setCodArticulo(articulo.getCodArticulo());
	result.setCodClase(articulo.getCodClase());
	result.setCodItem(articulo.getCodItem());
	result.setDescArticulo(articulo.getDescArticulo());
	result.setNomArticulo(articulo.getNomArticulo());
	result.setNroSerie(articulo.getNroSerie());
	result.setVto(articulo.getVto());
	result.setPeso(articulo.getPeso());
	result.setNroParte(articulo.getNroParte());
	result.setMedidaU(articulo.getMedidaU().getUndadMedida());
	result.setuMedida(articulo.getMedidaU().getSimbolo());
	result.setIsComp(articulo.getIsComp());
	result.setPesoUId(articulo.getPesoU().getUndadMedida());
	result.setuPeso(articulo.getPesoU().getSimbolo());
	result.setMaterialId(articulo.getMaterial().getCodMaterial());
	result.setMaterial(articulo.getMaterial().getNomMaterial());
	result.setPartidaParcialId(articulo.getPartidaparcial().getNroPParcial());
	result.setTipoArticuloId(articulo.getTipoArticulo().getCodTipoArticulo());
	result.settArticulo(articulo.getTipoArticulo().getNomArticulo());
	result.setGrupo(articulo.getGrupo().getNomGrupo());
	
	
	return result;
	
}
public static Collection<ArticuloDTO> getArticuloDTO(Collection<Articulo> articulos){
	if(articulos == null){
		return null;
	}
	
	List<ArticuloDTO> retorno = new ArrayList<ArticuloDTO>();
	for(Articulo articulo: articulos){
		retorno.add(getArticuloDTO(articulo));
	}
	return retorno;
}
}
