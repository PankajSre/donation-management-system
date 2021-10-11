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
import com.iiht.training.ngo.exceptions.InvalidDataException;
import com.iiht.training.ngo.service.DonarService;

@RestController
@RequestMapping("/donars")
public class DonarController {

	@Autowired
	private DonarService donarService;

	@PostMapping("/register-donar")
	public ResponseEntity<?> registerDonar(@Valid @RequestBody DonarDto donarDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donar Data is not correct");
		}
		donarService.registerDonar(donarDto);
		return ResponseEntity.ok(donarDto);
	}

	@PutMapping("/update-donar")
	public ResponseEntity<?> updateDonar(@Valid @RequestBody DonarDto donarDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Donar Data is not correct");
		}
		donarService.registerDonar(donarDto);
		return ResponseEntity.ok(donarDto);
	}

	@DeleteMapping("/delete/{donarId}")
	public ResponseEntity<?> deleteDonarById(@PathVariable Long donarId) {
		donarService.deleteDonar(donarId);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/get/{donarId}")
	public ResponseEntity<?> getDonarById(@PathVariable Long donarId) {
		DonarDto donarById = donarService.getDonarById(donarId);
		return ResponseEntity.ok(donarById);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllDonars() {
		List<DonarDto> allDonars = donarService.getAllDonars();
		return ResponseEntity.ok(allDonars);
	}

	@GetMapping("/get-by-ngo/{ngoId}")
	public ResponseEntity<?> getDonarByNgoId(@PathVariable Long ngoId) {
		List<DonarDto> donarsRegisteredWithNgo = donarService.getDonarsRegisteredWithNgo(ngoId);
		return ResponseEntity.ok(donarsRegisteredWithNgo);
	}

}
