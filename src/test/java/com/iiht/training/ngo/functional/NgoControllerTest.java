package com.iiht.training.ngo.functional;

import static com.iiht.training.ngo.testutils.TestUtils.businessTestFile;
import static com.iiht.training.ngo.testutils.TestUtils.currentTest;
import static com.iiht.training.ngo.testutils.TestUtils.testReport;
import static com.iiht.training.ngo.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.ngo.controller.NgoController;
import com.iiht.training.ngo.dto.DonationRequestDto;
import com.iiht.training.ngo.dto.NgoDto;
import com.iiht.training.ngo.service.DonarService;
import com.iiht.training.ngo.service.DonationRequestService;
import com.iiht.training.ngo.service.NgoService;
import com.iiht.training.ngo.testutils.MasterData;

@WebMvcTest(NgoController.class)
@AutoConfigureMockMvc
public class NgoControllerTest {
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
	public void testRegisterNgo() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		NgoDto savedNgoDto = MasterData.getNgoDto();

		savedNgoDto.setNgoId(1L);

		when(this.ngoService.registerNgo(ngoDto)).thenReturn(savedNgoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/register-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedNgoDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterNgoIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		NgoDto ngoDto = MasterData.getNgoDto();
		NgoDto savedNgoDto = MasterData.getNgoDto();

		savedNgoDto.setNgoId(1L);
		when(ngoService.registerNgo(ngoDto)).then(new Answer<NgoDto>() {

			@Override
			public NgoDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedNgoDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/register-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllNgos() throws Exception {
		List<NgoDto> ngoDtos = MasterData.getNgoDtoList();

		when(this.ngoService.getAllNgos()).thenReturn(ngoDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/all").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(ngoDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllNgosIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<NgoDto> ngoDtos = MasterData.getNgoDtoList();
		when(this.ngoService.getAllNgos()).then(new Answer<List<NgoDto>>() {

			@Override
			public List<NgoDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return ngoDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/all").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetNgoById() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setNgoId(1L);
		when(this.ngoService.getNgoById(1L)).thenReturn(ngoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(ngoDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetNgoByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		NgoDto ngoDto = MasterData.getNgoDto();
		when(this.ngoService.getNgoById(1L)).then(new Answer<NgoDto>() {

			@Override
			public NgoDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return ngoDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateNgo() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		NgoDto savedNgoDto = MasterData.getNgoDto();

		savedNgoDto.setNgoId(1L);

		when(this.ngoService.updateNgo(ngoDto)).thenReturn(savedNgoDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/ngos/update-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedNgoDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateNgoIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		NgoDto ngoDto = MasterData.getNgoDto();
		NgoDto savedNgoDto = MasterData.getNgoDto();

		savedNgoDto.setNgoId(1L);
		when(ngoService.updateNgo(ngoDto)).then(new Answer<NgoDto>() {

			@Override
			public NgoDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedNgoDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/ngos/update-ngo")
				.content(MasterData.asJsonString(ngoDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteNgo() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setNgoId(1L);
		when(this.ngoService.deleteNgo(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ngos/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteNgoIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setNgoId(1L);
		when(ngoService.deleteNgo(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ngos/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testCreateDonationRequest() throws Exception {
		DonationRequestDto donationRequestDto = MasterData.getDonationRequestDto();
		DonationRequestDto savedDonationRequestDto = MasterData.getDonationRequestDto();

		savedDonationRequestDto.setRequestId(1L);

		when(this.donationRequestService.createDonationRequest(donationRequestDto)).thenReturn(savedDonationRequestDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/create-donation-request")
				.content(MasterData.asJsonString(donationRequestDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString()
						.contentEquals(MasterData.asJsonString(savedDonationRequestDto)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	public void testCreateDonationRequestIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonationRequestDto donationRequestDto = MasterData.getDonationRequestDto();
		DonationRequestDto savedDonationRequestDto = MasterData.getDonationRequestDto();

		savedDonationRequestDto.setRequestId(1L);
		when(donationRequestService.createDonationRequest(donationRequestDto)).then(new Answer<DonationRequestDto>() {

			@Override
			public DonationRequestDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDonationRequestDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ngos/create-donation-request")
				.content(MasterData.asJsonString(donationRequestDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	@Test
	public void testGetDonationRequestNotificationsByNgoId() throws Exception {
		List<DonationRequestDto> donationRequestDtos = MasterData.getDonationRequestDtoList();
		
		when(this.donationRequestService.getDonationRequestNotification(1L)).thenReturn(donationRequestDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/donation-request-by-ngo/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donationRequestDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDonationRequestNotificationsByNgoIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonationRequestDto> donationRequestDtos = MasterData.getDonationRequestDtoList();
		when(this.donationRequestService.getDonationRequestNotification(1L)).then(new Answer<List<DonationRequestDto>>() {

			@Override
			public List<DonationRequestDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donationRequestDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/donation-request-by-ngo/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	@Test
	public void testGetDonationRequestByDonarId() throws Exception {
		List<DonationRequestDto> donationRequestDtos = MasterData.getDonationRequestDtoList();
		
		when(this.donationRequestService.getDonationRequestByDonarId(1L)).thenReturn(donationRequestDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/donation-request-by-donar/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donationRequestDtos)) ? "true"
						: "false"),
				businessTestFile);
		
	}
	
	@Test
	public void testGetDonationRequestByDonarIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonationRequestDto> donationRequestDtos = MasterData.getDonationRequestDtoList();
		when(this.donationRequestService.getDonationRequestByDonarId(1L)).then(new Answer<List<DonationRequestDto>>() {
			
			@Override
			public List<DonationRequestDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donationRequestDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ngos/donation-request-by-donar/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);
		
	}
	

}
