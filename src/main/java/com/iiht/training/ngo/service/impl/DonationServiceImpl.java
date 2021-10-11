package com.iiht.training.ngo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.entity.DonationEntity;
import com.iiht.training.ngo.exceptions.DonationNotFoundException;
import com.iiht.training.ngo.repository.DonationRepository;
import com.iiht.training.ngo.service.DonationService;

@Service
public class DonationServiceImpl implements DonationService {

	@Autowired
	private DonationRepository donationRepository;

	@Override
	public DonationDto registerDonation(DonationDto donationDto) {
		DonationEntity donationEntity = new DonationEntity();
		BeanUtils.copyProperties(donationDto, donationEntity);
		donationRepository.save(donationEntity);
		return donationDto;
	}

	@Override
	public DonationDto updateDonation(DonationDto donationDto) {
		DonationEntity donationEntity = new DonationEntity();
		BeanUtils.copyProperties(donationDto, donationEntity);
		donationRepository.save(donationEntity);
		return donationDto;
	}

	@Override
	public Boolean deleteDonation(Long donationId) {
		DonationDto donationById = getDonationById(donationId);
		DonationEntity donationEntity = new DonationEntity();
		BeanUtils.copyProperties(donationById, donationEntity);
		donationRepository.delete(donationEntity);
		return true;
	}

	@Override
	public DonationDto getDonationById(Long donationId) {
		Optional<DonationEntity> donation = donationRepository.findById(donationId);
		if (donation.isPresent()) {
			DonationDto donationDto = new DonationDto();
			BeanUtils.copyProperties(donation, donationDto);
			return donationDto;
		} else {
			throw new DonationNotFoundException("Donation with id " + donationId + " does not exists");
		}
	}

	@Override
	public List<DonationDto> getAllDonations() {
		List<DonationEntity> donations = donationRepository.findAll();
		List<DonationDto> donationDtos = new ArrayList<>();
		for (DonationEntity donationEntity : donations) {
			DonationDto donationDto = new DonationDto();
			BeanUtils.copyProperties(donationEntity, donationDto);
			donationDtos.add(donationDto);

		}
		return donationDtos;
	}

	@Override
	public List<DonationDto> getDonationsByDonorId(Long donarId) {
		List<DonationEntity> donations = donationRepository.getByDonarId(donarId);
		List<DonationDto> donationDtos = new ArrayList<>();
		for (DonationEntity donationEntity : donations) {
			DonationDto donationDto = new DonationDto();
			BeanUtils.copyProperties(donationEntity, donationDto);
			donationDtos.add(donationDto);

		}
		return donationDtos;
	}

	@Override
	public List<DonationDto> getDonationsByNgoId(Long ngoId) {
		List<DonationEntity> donations = donationRepository.getByNgoId(ngoId);
		List<DonationDto> donationDtos = new ArrayList<>();
		for (DonationEntity donationEntity : donations) {
			DonationDto donationDto = new DonationDto();
			BeanUtils.copyProperties(donationEntity, donationDto);
			donationDtos.add(donationDto);

		}
		return donationDtos;
	}

}
