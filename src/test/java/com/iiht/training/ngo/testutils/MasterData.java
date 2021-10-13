package com.iiht.training.ngo.testutils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.ngo.dto.DonarDto;
import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.dto.DonationRequestDto;
import com.iiht.training.ngo.dto.NgoDto;

public class MasterData {

	public static NgoDto getNgoDto() {
		NgoDto ngoDto = new NgoDto();
		ngoDto.setNgoId(1L);
		ngoDto.setNgoName("Trust");
		ngoDto.setAddress("Bangalore");
		ngoDto.setUsername("mytrust");
		ngoDto.setPassword("123456");
		ngoDto.setPhoneNumber(1234567890L);
		ngoDto.setStartedIn(LocalDate.of(2020, 05, 20));
		ngoDto.setDocuments("All the Documents");
		return ngoDto;
	}

	public static List<NgoDto> getNgoDtoList() {
		List<NgoDto> ngoDtos = new ArrayList<>();
		NgoDto ngoDto = new NgoDto();
		ngoDto.setNgoId(1L);
		ngoDto.setNgoName("Trust");
		ngoDto.setAddress("Bangalore");
		ngoDto.setUsername("mytrust");
		ngoDto.setPassword("123456");
		ngoDto.setPhoneNumber(1234567890L);
		ngoDto.setStartedIn(LocalDate.of(2020, 05, 20));
		ngoDto.setDocuments("All the Documents");
		ngoDtos.add(ngoDto);
		ngoDto = new NgoDto();
		ngoDto.setNgoId(2L);
		ngoDto.setNgoName("Trust Of India");
		ngoDto.setAddress("Chennai");
		ngoDto.setUsername("trust001");
		ngoDto.setPassword("111#111");
		ngoDto.setPhoneNumber(9090121234L);
		ngoDto.setStartedIn(LocalDate.of(2019, 05, 20));
		ngoDto.setDocuments("Registration Documents");
		ngoDtos.add(ngoDto);
		return ngoDtos;
	}

	public static DonarDto geDonarDto() {
		DonarDto donarDto = new DonarDto();
		donarDto.setDonarId(1L);
		donarDto.setDonarName("David");
		donarDto.setEmailId("david@abc.com");
		donarDto.setNgoId(1L);
		donarDto.setUsername("david.123");
		donarDto.setPassword("123#123");
		donarDto.setAddress("Mexico");
		donarDto.setPhoneNumber(9890989098L);
		return donarDto;

	}

	public static List<DonarDto> getDonarDtoList() {
		List<DonarDto> donarDtos = new ArrayList<>();
		DonarDto donarDto = new DonarDto();
		donarDto.setDonarId(1L);
		donarDto.setDonarName("David");
		donarDto.setEmailId("david@abc.com");
		donarDto.setNgoId(1L);
		donarDto.setUsername("david.123");
		donarDto.setPassword("123#123");
		donarDto.setAddress("Mexico");
		donarDto.setPhoneNumber(9890989098L);
		donarDtos.add(donarDto);
		donarDto = new DonarDto();
		donarDto.setDonarId(2L);
		donarDto.setDonarName("Coua");
		donarDto.setEmailId("coua@abc.com");
		donarDto.setNgoId(1L);
		donarDto.setUsername("coua.123");
		donarDto.setPassword("123#123");
		donarDto.setAddress("Brazil");
		donarDto.setPhoneNumber(9900990099L);
		donarDtos.add(donarDto);
		return donarDtos;
	}

	public static DonationDto getDonationDto() {
		DonationDto donationDto = new DonationDto();
		donationDto.setDonationId(1L);
		donationDto.setNgoId(1L);
		donationDto.setDonarId(1L);
		donationDto.setAmount(50000.0);
		donationDto.setDonationDate(LocalDate.of(2022, 10, 20));
		donationDto.setDonationType("Health");
		return donationDto;

	}

	public static List<DonationDto> getDonationDtoList() {
		List<DonationDto> donationDtos = new ArrayList<>();
		DonationDto donationDto = new DonationDto();
		donationDto.setDonationId(1L);
		donationDto.setNgoId(1L);
		donationDto.setDonarId(1L);
		donationDto.setAmount(50000.0);
		donationDto.setDonationDate(LocalDate.now());
		donationDto.setDonationType("Health");
		donationDtos.add(donationDto);
		donationDto = new DonationDto();
		donationDto.setDonationId(2L);
		donationDto.setNgoId(1L);
		donationDto.setDonarId(2L);
		donationDto.setAmount(100000.0);
		donationDto.setDonationDate(LocalDate.now());
		donationDto.setDonationType("Animal Welfare");
		donationDtos.add(donationDto);
		return donationDtos;

	}

	public static DonationRequestDto getDonationRequestDto() {
		DonationRequestDto requestDto = new DonationRequestDto();
		requestDto.setRequestId(1L);
		requestDto.setNgoId(1L);
		requestDto.setDonarId(2L);
		requestDto.setAmount(25000.0);
		requestDto.setDonationEndDate(LocalDate.of(2021, 12, 10));
		requestDto.setRequestStatus("Sent");
		return requestDto;
	}

	public static List<DonationRequestDto> getDonationRequestDtoList() {
		List<DonationRequestDto> list = new ArrayList<>();
		DonationRequestDto requestDto = new DonationRequestDto();
		requestDto.setRequestId(1L);
		requestDto.setNgoId(1L);
		requestDto.setDonarId(2L);
		requestDto.setAmount(25000.0);
		requestDto.setDonationEndDate(LocalDate.of(2021, 12, 10));
		requestDto.setRequestStatus("Sent");
		list.add(requestDto);
		requestDto = new DonationRequestDto();
		requestDto.setRequestId(2L);
		requestDto.setNgoId(2L);
		requestDto.setDonarId(1L);
		requestDto.setAmount(250000.0);
		requestDto.setDonationEndDate(LocalDate.of(2021, 11, 10));
		requestDto.setRequestStatus("Sent");
		list.add(requestDto);
		return list;

	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
