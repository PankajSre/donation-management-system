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

import com.iiht.training.ngo.dto.NgoDto;
import com.iiht.training.ngo.exceptions.InvalidDataException;
import com.iiht.training.ngo.service.NgoService;

@RestController
@RequestMapping("/ngos")
public class NgoController {

	@Autowired
	private NgoService ngoService;

	@PostMapping("/register-ngo")
	public ResponseEntity<?> registerNgo(@Valid @RequestBody NgoDto ngoDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Data is not valid");
		}
		ngoService.registerNgo(ngoDto);
		return ResponseEntity.ok(ngoDto);
	}

	@PutMapping("/update-ngo")
	public ResponseEntity<?> updateNgo(@Valid @RequestBody NgoDto ngoDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("The Data is not valid");
		}
		ngoService.updateNgo(ngoDto);
		return ResponseEntity.ok(ngoDto);
	}

	@DeleteMapping("/delete/{ngoId}")
	public ResponseEntity<?> deleteNgoById(@PathVariable Long ngoId) {
		ngoService.deleteNgo(ngoId);
		return ResponseEntity.ok(true);
	}

	@GetMapping("/by-id/{ngoId}")
	public ResponseEntity<?> getNgoById(@PathVariable Long ngoId) {
		NgoDto ngoById = ngoService.getNgoById(ngoId);
		return ResponseEntity.ok(ngoById);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllRegesteredNgos() {
		List<NgoDto> allNgos = ngoService.getAllNgos();
		return ResponseEntity.ok(allNgos);
	}

}
