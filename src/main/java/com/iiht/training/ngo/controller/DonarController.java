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

import com.iiht.training.ngo.dto.DonarDto;
import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.exceptions.InvalidDataException;
import com.iiht.training.ngo.service.DonarService;
import com.iiht.training.ngo.service.DonationService;

@RestController
public class DonarController {

	@Autowired
	private DonarService donarService;

	@Autowired
	private DonationService donationService;

	@PostMapping("/donars/register-donar")
	public ResponseEntity<?> registerDonar(@Valid @RequestBody DonarDto donarDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donar Data is not correct");
		}
		donarService.registerDonar(donarDto);
		return ResponseEntity.ok(donarDto);
	}

	@PutMapping("/donars/update-donar")
	public ResponseEntity<?> updateDonar(@Valid @RequestBody DonarDto donarDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donar Data is not correct");
		}
		donarService.updateDonar(donarDto);
		return ResponseEntity.ok(donarDto);
	}

	@DeleteMapping("/donars/delete/{donarId}")
	public ResponseEntity<?> deleteDonarById(@PathVariable Long donarId) {
		donarService.deleteDonar(donarId);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/donars/get/{donarId}")
	public ResponseEntity<?> getDonarById(@PathVariable Long donarId) {
		DonarDto donarById = donarService.getDonarById(donarId);
		return ResponseEntity.ok(donarById);
	}

	@GetMapping("/donars/all")
	public ResponseEntity<?> getAllDonars() {
		List<DonarDto> allDonars = donarService.getAllDonars();
		return ResponseEntity.ok(allDonars);
	}

	@GetMapping("/donars/get-by-ngo/{ngoId}")
	public ResponseEntity<?> getDonarByNgoId(@PathVariable Long ngoId) {
		List<DonarDto> donarsRegisteredWithNgo = donarService.getDonarsRegisteredWithNgo(ngoId);
		return ResponseEntity.ok(donarsRegisteredWithNgo);
	}

	@PostMapping("/donations/add-donation")
	public ResponseEntity<?> addDonation(@Valid @RequestBody DonationDto donationDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donation data is not valid");
		}
		donationService.registerDonation(donationDto);
		return ResponseEntity.ok(donationDto);

	}

	@PutMapping("/donations/update-donation")
	public ResponseEntity<?> updateDonation(@Valid @RequestBody DonationDto donationDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donation data is not valid");
		}
		donationService.updateDonation(donationDto);
		return ResponseEntity.ok(donationDto);

	}

	@DeleteMapping("/donations/delete/{donationId}")
	public ResponseEntity<?> removeDonation(@PathVariable Long donationId) {
		donationService.deleteDonation(donationId);
		return ResponseEntity.ok(true);

	}

	@GetMapping("/donations/get/{donationId}")
	public ResponseEntity<?> getDonationById(@PathVariable Long donationId) {
		DonationDto donationById = donationService.getDonationById(donationId);
		return ResponseEntity.ok(donationById);

	}

	@GetMapping("/donations/all")
	public ResponseEntity<?> getAllDonations() {
		List<DonationDto> allDonations = donationService.getAllDonations();
		return ResponseEntity.ok(allDonations);

	}

	@GetMapping("/donations/get-by-donar/{donarId}")
	public ResponseEntity<?> getDonationsByDonorId(@PathVariable Long donarId) {
		List<DonationDto> allDonations = donationService.getDonationsByDonorId(donarId);
		return ResponseEntity.ok(allDonations);

	}

	@GetMapping("/donations/get-by-ngo/{ngoId}")
	public ResponseEntity<?> getDonationsByNgoId(@PathVariable Long ngoId) {
		List<DonationDto> allDonations = donationService.getDonationsByNgoId(ngoId);
		return ResponseEntity.ok(allDonations);

	}

}
