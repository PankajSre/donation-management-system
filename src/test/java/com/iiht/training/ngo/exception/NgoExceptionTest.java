package com.iiht.training.ngo.exception;

import static com.iiht.training.ngo.testutils.TestUtils.currentTest;
import static com.iiht.training.ngo.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.ngo.testutils.TestUtils.testReport;
import static com.iiht.training.ngo.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.ngo.controller.NgoController;
import com.iiht.training.ngo.dto.DonationRequestDto;
import com.iiht.training.ngo.dto.NgoDto;
import com.iiht.training.ngo.exceptions.NgoNotFoundException;
import com.iiht.training.ngo.model.exception.ExceptionResponse;
import com.iiht.training.ngo.service.DonarService;
import com.iiht.training.ngo.service.DonationRequestService;
import com.iiht.training.ngo.service.NgoService;
import com.iiht.training.ngo.testutils.MasterData;

@WebMvcTest(NgoController.class)
@AutoConfigureMockMvc
public class NgoExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NgoService ngoService;
	@MockBean
	private DonationRequestService donationRequestService;
	
	@MockBean
	private DonarService donarService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterNgoInvalidDataException() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		NgoDto savedNgoDto = MasterData.getNgoDto();
		savedNgoDto.setNgoId(1L);

		ngoDto.setNgoName("Ab");

		when(this.ngoService.registerNgo(ngoDto)).thenReturn(savedNgoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/register-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
               
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	@Test
	public void testUpdateNgoInvalidDataException() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		NgoDto savedNgoDto = MasterData.getNgoDto();
		savedNgoDto.setNgoId(1L);
		
		ngoDto.setNgoName("Ab");
		
		when(this.ngoService.updateNgo(ngoDto)).thenReturn(savedNgoDto);
		System.out.println(MasterData.asJsonString(ngoDto));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/register-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
		
	}

	@Test
	public void testGetNgoByIdNgoNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("NGO with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		
		when(this.ngoService.getNgoById(1L)).thenThrow(new NgoNotFoundException("NGO with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(exResponse.getMessage());
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}
	@Test
	public void testDeleteNgoByIdNgoNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("NGO with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		
		when(this.ngoService.deleteNgo(1L)).thenThrow(new NgoNotFoundException("NGO with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ngos/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
		
	}
	

	@Test
	public void testRegisterDonationRequestInvalidDataException() throws Exception {
		DonationRequestDto donationRequestDto = MasterData.getDonationRequestDto();
		DonationRequestDto savedDonationRequestDto = MasterData.getDonationRequestDto();
		savedDonationRequestDto.setRequestId(1L);

		donationRequestDto.setRequestStatus("Ab");

		when(this.donationRequestService.createDonationRequest(donationRequestDto)).thenReturn(savedDonationRequestDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/create-donation-request")
				.content(MasterData.asJsonString(donationRequestDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
               
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

}
