package com.iiht.training.ngo.service;

import java.util.List;

import com.iiht.training.ngo.dto.DonationRequestDto;

public interface DonationRequestService {

	public DonationRequestDto createDonationRequest(DonationRequestDto donationRequestDto);

	public List<DonationRequestDto> getDonationRequestNotification(Long ngoId);

	public List<DonationRequestDto> getDonationRequestByDonarId(Long donarId);

}
