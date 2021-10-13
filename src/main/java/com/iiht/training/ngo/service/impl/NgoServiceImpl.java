package com.iiht.training.ngo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.ngo.dto.NgoDto;
import com.iiht.training.ngo.entity.NgoEntity;
import com.iiht.training.ngo.exceptions.NgoNotFoundException;
import com.iiht.training.ngo.repository.NgoRepository;
import com.iiht.training.ngo.service.NgoService;

@Service
public class NgoServiceImpl implements NgoService {

	@Autowired
	private NgoRepository ngoRepository;

	@Override
	public NgoDto registerNgo(NgoDto ngoDto) {
		NgoEntity entity = new NgoEntity();
		BeanUtils.copyProperties(ngoDto, entity);
		ngoRepository.save(entity);
		return ngoDto;
	}

	@Override
	public NgoDto updateNgo(NgoDto ngoDto) {
		NgoEntity entity = new NgoEntity();
		BeanUtils.copyProperties(ngoDto, entity);
		ngoRepository.save(entity);
		return ngoDto;
	}

	@Override
	public Boolean deleteNgo(Long ngoId) {
		NgoDto ngoById = getNgoById(ngoId);
		NgoEntity ngoEntity = new NgoEntity();
		BeanUtils.copyProperties(ngoById, ngoEntity);
		ngoRepository.delete(ngoEntity);
		return true;
	}

	@Override
	public NgoDto getNgoById(Long ngoId) {
		Optional<NgoEntity> ngoEntity = ngoRepository.findById(ngoId);
		if (ngoEntity.isPresent()) {
			NgoDto ngoDto = new NgoDto();
			BeanUtils.copyProperties(ngoEntity.get(), ngoDto);
			return ngoDto;
		} else {
			throw new NgoNotFoundException("NGO with id " + ngoId + " not found");
		}
	}

	@Override
	public List<NgoDto> getAllNgos() {
		List<NgoEntity> list = ngoRepository.findAll();
		List<NgoDto> ngoDtos = new ArrayList<>();
		for (NgoEntity entity : list) {
			NgoDto ngoDto = new NgoDto();
			BeanUtils.copyProperties(entity, ngoDto);
			ngoDtos.add(ngoDto);
		}
		return ngoDtos;
	}

}
