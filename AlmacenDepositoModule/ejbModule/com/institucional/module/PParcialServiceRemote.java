package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.PParcialDTO;

@Remote
public interface PParcialServiceRemote {

	PParcialDTO findById(Integer pparcialId);

	Collection<PParcialDTO> listAll();




}
