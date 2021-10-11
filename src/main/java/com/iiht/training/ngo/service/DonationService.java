package com.iiht.training.ngo.service;

import java.util.List;

import com.iiht.training.ngo.dto.DonationDto;

public interface DonationService {

	public DonationDto registerDonation(DonationDto donationDto);

	public DonationDto updateDonation(DonationDto donationDto);

	public Boolean deleteDonation(Long donationId);

	public DonationDto getDonationById(Long donationId);

	public List<DonationDto> getAllDonations();

	public List<DonationDto> getDonationsByDonorId(Long donarId);

	public List<DonationDto> getDonationsByNgoId(Long ngoId);
}
