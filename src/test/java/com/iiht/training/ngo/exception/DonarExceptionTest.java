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

import com.iiht.training.ngo.controller.DonarController;
import com.iiht.training.ngo.dto.DonarDto;
import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.dto.NgoDto;
import com.iiht.training.ngo.exceptions.DonarNotFoundException;
import com.iiht.training.ngo.exceptions.DonationNotFoundException;
import com.iiht.training.ngo.exceptions.NgoNotFoundException;
import com.iiht.training.ngo.model.exception.ExceptionResponse;
import com.iiht.training.ngo.service.DonarService;
import com.iiht.training.ngo.service.DonationRequestService;
import com.iiht.training.ngo.service.DonationService;
import com.iiht.training.ngo.service.NgoService;
import com.iiht.training.ngo.testutils.MasterData;

@WebMvcTest(DonarController.class)
@AutoConfigureMockMvc
public class DonarExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NgoService ngoService;
	@MockBean
	private DonationService donationService;
	@MockBean
	private DonationRequestService donationRequestService;

	@MockBean
	private DonarService donarService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterDonarInvalidDataException() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		DonarDto savedDonarDto = MasterData.geDonarDto();

		savedDonarDto.setDonarId(1L);
		donarDto.setDonarName("Ab");

		when(this.donarService.registerDonar(donarDto)).thenReturn(savedDonarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donars/register-donar")
				.content(MasterData.asJsonString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateDonarInvalidDataException() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		DonarDto savedDonarDto = MasterData.geDonarDto();

		savedDonarDto.setDonarId(1L);
		donarDto.setDonarName("Ab");

		when(this.donarService.updateDonar(donarDto)).thenReturn(savedDonarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donars/update-donar")
				.content(MasterData.asJsonString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetDonarByIdDonarNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Donar with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.donarService.getDonarById(1L)).thenThrow(new DonarNotFoundException("Donar with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteDonarByIdDonarNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Donar with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.donarService.deleteDonar(1L)).thenThrow(new DonarNotFoundException("Donar with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/donars/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testRegisterDonationInvalidDataException() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto savedDonationDto = MasterData.getDonationDto();

		savedDonationDto.setDonationId(1L);
		donationDto.setDonationType("Ab");

		when(this.donationService.registerDonation(donationDto)).thenReturn(savedDonationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donations/add-donation")
				.content(MasterData.asJsonString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateDonationInvalidDataException() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto savedDonationDto = MasterData.getDonationDto();

		savedDonationDto.setDonationId(1L);
		donationDto.setDonationType("Ab");

		when(this.donationService.updateDonation(donationDto)).thenReturn(savedDonationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donations/update-donation")
				.content(MasterData.asJsonString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testGetDonationByIdDonationNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Donation with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.donationService.getDonationById(1L)).thenThrow(new DonationNotFoundException("Donation with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testDeleteDonationByIdDonarNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Donation with Id - 1 not Found!", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());

		when(this.donationService.deleteDonation(1L)).thenThrow(new DonationNotFoundException("Donation with Id - 1 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/donations/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);

	}
}
