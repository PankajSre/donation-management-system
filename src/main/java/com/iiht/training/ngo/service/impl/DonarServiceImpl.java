package com.iiht.training.ngo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.ngo.dto.DonarDto;
import com.iiht.training.ngo.entity.DonarEntity;
import com.iiht.training.ngo.exceptions.DonarNotFoundException;
import com.iiht.training.ngo.repository.DonarRepository;
import com.iiht.training.ngo.service.DonarService;

@Service(value = "donarService")
public class DonarServiceImpl implements DonarService {

	@Autowired
	private DonarRepository donarRepository;

	@Override
	public DonarDto registerDonar(DonarDto donarDto) {
		DonarEntity donarEntity = new DonarEntity();
		BeanUtils.copyProperties(donarDto, donarEntity);
		donarRepository.save(donarEntity);
		return donarDto;
	}

	@Override
	public DonarDto updateDonar(DonarDto donarDto) {
		DonarEntity donarEntity = new DonarEntity();
		BeanUtils.copyProperties(donarDto, donarEntity);
		donarRepository.save(donarEntity);
		return donarDto;
	}

	@Override
	public Boolean deleteDonar(Long donarid) {
		DonarDto donarById = getDonarById(donarid);
		DonarEntity donarEntity = new DonarEntity();
		BeanUtils.copyProperties(donarById, donarEntity);
		donarRepository.delete(donarEntity);
		return true;
	}

	@Override
	public DonarDto getDonarById(Long donarId) {
		Optional<DonarEntity> findById = donarRepository.findById(donarId);
		if (findById.isPresent()) {
			DonarDto donarDto = new DonarDto();
			BeanUtils.copyProperties(findById.get(), donarDto);
			return donarDto;
		} else {
			throw new DonarNotFoundException("The Donar with id " + donarId + " does not exists");
		}
	}

	@Override
	public List<DonarDto> getAllDonars() {
		List<DonarEntity> donars = donarRepository.findAll();
		List<DonarDto> donarDtos = new ArrayList<>();
		for (DonarEntity donarEntity : donars) {
			DonarDto donarDto = new DonarDto();
			BeanUtils.copyProperties(donarEntity, donarDto);
			donarDtos.add(donarDto);
		}
		return donarDtos;
	}

	@Override
	public List<DonarDto> getDonarsRegisteredWithNgo(Long ngoId) {
		List<DonarEntity> donars = donarRepository.findByNgoId(ngoId);
		List<DonarDto> donarDtos = new ArrayList<>();
		for (DonarEntity donarEntity : donars) {
			DonarDto donarDto = new DonarDto();
			BeanUtils.copyProperties(donarEntity, donarDto);
			donarDtos.add(donarDto);
		}
		return donarDtos;
	}

}
