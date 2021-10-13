package com.iiht.training.ngo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.ngo.dto.DonationRequestDto;
import com.iiht.training.ngo.entity.DonationRequestEntity;
import com.iiht.training.ngo.repository.DonationRequestRepository;
import com.iiht.training.ngo.service.DonationRequestService;

@Service(value = "donationRequestService")
public class DonationRequestServiceImpl implements DonationRequestService {

	@Autowired
	private DonationRequestRepository donationRequestRepository;

	@Override
	public DonationRequestDto createDonationRequest(DonationRequestDto donationRequestDto) {
		DonationRequestEntity entity = new DonationRequestEntity();
		BeanUtils.copyProperties(donationRequestDto, entity);
		donationRequestRepository.save(entity);
		return donationRequestDto;
	}

	@Override
	public List<DonationRequestDto> getDonationRequestNotification(Long ngoId) {
		List<DonationRequestEntity> findByNgoId = donationRequestRepository.findByNgoId(ngoId);
		List<DonationRequestDto> donationRequestDtos = new ArrayList<>();
		for (DonationRequestEntity entity : findByNgoId) {
			DonationRequestDto requestDto = new DonationRequestDto();
			BeanUtils.copyProperties(entity, requestDto);
			donationRequestDtos.add(requestDto);
		}
		return donationRequestDtos;
	}

	@Override
	public List<DonationRequestDto> getDonationRequestByDonarId(Long donarId) {
		List<DonationRequestEntity> findByDonarId = donationRequestRepository.findByDonarId(donarId);
		List<DonationRequestDto> donationRequestDtos = new ArrayList<>();
		for (DonationRequestEntity entity : findByDonarId) {
			DonationRequestDto requestDto = new DonationRequestDto();
			BeanUtils.copyProperties(entity, requestDto);
			donationRequestDtos.add(requestDto);
		}
		return donationRequestDtos;
	}

}
