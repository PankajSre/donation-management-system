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
import com.iiht.training.ngo.dto.NgoDto;
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
		System.out.println(MasterData.asJsonString(ngoDto));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/register-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
               
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	

}
