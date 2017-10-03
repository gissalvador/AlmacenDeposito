package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.AlmacenDTO;

@Remote
public interface AlmacenServiceRemote {

	Collection<AlmacenDTO> listAll();

	void addAlmacen(AlmacenDTO almacen);

}
