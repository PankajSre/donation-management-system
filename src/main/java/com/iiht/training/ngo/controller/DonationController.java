package com.iiht.training.ngo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.exceptions.InvalidDataException;
import com.iiht.training.ngo.service.DonationService;

@RestController
@RequestMapping("/donations")
public class DonationController {

	@Autowired
	private DonationService donationService;

	@PostMapping("/add-donation")
	public ResponseEntity<?> addDonation(@Valid @RequestBody DonationDto donationDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donation data is not valid");
		}
		donationService.registerDonation(donationDto);
		return ResponseEntity.ok(donationDto);

	}

	@PutMapping("/update-donation")
	public ResponseEntity<?> updateDonation(@Valid @RequestBody DonationDto donationDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donation data is not valid");
		}
		donationService.updateDonation(donationDto);
		return ResponseEntity.ok(donationDto);

	}

	@DeleteMapping("/delete/{donationId}")
	public ResponseEntity<?> removeDonation(@PathVariable Long donationId) {
		donationService.deleteDonation(donationId);
		return ResponseEntity.ok(true);

	}

	@GetMapping("/get/{donationId}")
	public ResponseEntity<?> getDonationById(@PathVariable Long donationId) {
		DonationDto donationById = donationService.getDonationById(donationId);
		return ResponseEntity.ok(donationById);

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllDonations() {
		List<DonationDto> allDonations = donationService.getAllDonations();
		return ResponseEntity.ok(allDonations);

	}

	@GetMapping("/get-by-donar/{donarId}")
	public ResponseEntity<?> getDonationsByDonorId(@PathVariable Long donarId) {
		List<DonationDto> allDonations = donationService.getDonationsByDonorId(donarId);
		return ResponseEntity.ok(allDonations);

	}

	@GetMapping("/get-by-ngo/{ngoId}")
	public ResponseEntity<?> getDonationsByNgoId(@PathVariable Long ngoid) {
		List<DonationDto> allDonations = donationService.getDonationsByNgoId(ngoid);
		return ResponseEntity.ok(allDonations);

	}

}
