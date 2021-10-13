package com.iiht.training.ngo.service;

import java.util.List;

import com.iiht.training.ngo.dto.NgoDto;

public interface NgoService {

	public NgoDto registerNgo(NgoDto ngoDto);

	public NgoDto updateNgo(NgoDto ngoDto);

	public Boolean deleteNgo(Long ngoId);

	public NgoDto getNgoById(Long id);

	public List<NgoDto> getAllNgos();

}
