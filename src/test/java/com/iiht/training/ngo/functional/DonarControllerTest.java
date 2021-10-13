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

import com.iiht.training.ngo.controller.DonarController;
import com.iiht.training.ngo.dto.DonarDto;
import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.service.DonarService;
import com.iiht.training.ngo.service.DonationService;
import com.iiht.training.ngo.testutils.MasterData;

@WebMvcTest(DonarController.class)
@AutoConfigureMockMvc
public class DonarControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DonationService donationService;
	@MockBean
	private DonarService donarService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterDonar() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		DonarDto savedDonarDto = MasterData.geDonarDto();
		savedDonarDto.setDonarId(1L);

		when(this.donarService.registerDonar(donarDto)).thenReturn(savedDonarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donars/register-donar")
				.content(MasterData.asJsonString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDonarDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterDonarIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonarDto donarDto = MasterData.geDonarDto();
		DonarDto savedDonarDto = MasterData.geDonarDto();
		savedDonarDto.setDonarId(1L);
		when(donarService.registerDonar(donarDto)).then(new Answer<DonarDto>() {

			@Override
			public DonarDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDonarDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donars/register-donar")
				.content(MasterData.asJsonString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllDonars() throws Exception {
		List<DonarDto> donarDtos = MasterData.getDonarDtoList();

		when(this.donarService.getAllDonars()).thenReturn(donarDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donarDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllDonarsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonarDto> donarDtos = MasterData.getDonarDtoList();
		when(this.donarService.getAllDonars()).then(new Answer<List<DonarDto>>() {

			@Override
			public List<DonarDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donarDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetDonarById() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setDonarId(1L);
		when(this.donarService.getDonarById(1L)).thenReturn(donarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donarDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDonarByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setDonarId(1L);
		when(this.donarService.getDonarById(1L)).then(new Answer<DonarDto>() {

			@Override
			public DonarDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donarDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateDonar() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		DonarDto savedDonarDto = MasterData.geDonarDto();
		savedDonarDto.setDonarId(1L);

		when(this.donarService.updateDonar(donarDto)).thenReturn(savedDonarDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donars/update-donar")
				.content(MasterData.asJsonString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDonarDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateDonarIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonarDto donarDto = MasterData.geDonarDto();
		DonarDto savedDonarDto = MasterData.geDonarDto();
		savedDonarDto.setDonarId(1L);

		when(donarService.updateDonar(donarDto)).then(new Answer<DonarDto>() {

			@Override
			public DonarDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDonarDto; 
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donars/update-donar")
				.content(MasterData.asJsonString(donarDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteDonar() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setDonarId(1L);

		when(this.donarService.deleteDonar(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/donars/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteDonarIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setDonarId(1L);
		when(donarService.deleteDonar(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/donars/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	public void testGetDonarByNgoId() throws Exception {
		List<DonarDto> donarDtos = MasterData.getDonarDtoList();
		when(this.donarService.getDonarsRegisteredWithNgo(1L)).thenReturn(donarDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/get-by-ngo/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donarDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDonarsByNgoIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonarDto> donarDtos = MasterData.getDonarDtoList();
		
		when(this.donarService.getDonarsRegisteredWithNgo(1L)).then(new Answer<List<DonarDto>>() {

			@Override
			public List<DonarDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donarDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donars/get-by-ngo/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	
	@Test
	public void testRegisterDonation() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto savedDonationDto = MasterData.getDonationDto();
		savedDonationDto.setDonationId(1L);
		when(this.donationService.registerDonation(donationDto)).thenReturn(savedDonationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donations/add-donation")
				.content(MasterData.asJsonString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		System.out.println(MasterData.asJsonString(savedDonationDto));
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDonationDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterDonationIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto savedDonationDto = MasterData.getDonationDto();
		savedDonationDto.setDonationId(1L);
		when(donationService.registerDonation(donationDto)).then(new Answer<DonationDto>() {

			@Override
			public DonationDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDonationDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/donations/add-donation")
				.content(MasterData.asJsonString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetAllDonations() throws Exception {
		List<DonationDto> donationDtos = MasterData.getDonationDtoList();

		when(this.donationService.getAllDonations()).thenReturn(donationDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donationDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllDonationsIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonationDto> donationDtos = MasterData.getDonationDtoList();
		when(this.donationService.getAllDonations()).then(new Answer<List<DonationDto>>() {

			@Override
			public List<DonationDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donationDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/all")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testGetDonationById() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationId(1L);
		when(this.donationService.getDonationById(1L)).thenReturn(donationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donationDto)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDonationByIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationId(1L);
		when(this.donationService.getDonationById(1L)).then(new Answer<DonationDto>() {

			@Override
			public DonationDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donationDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testUpdateDonation() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto savedDonationDto = MasterData.getDonationDto();
		savedDonationDto.setDonationId(1L);

		when(this.donationService.updateDonation(donationDto)).thenReturn(savedDonationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donations/update-donation")
				.content(MasterData.asJsonString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDonationDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testUpdateDonationIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonationDto donationDto = MasterData.getDonationDto();
		DonationDto savedDonationDto = MasterData.getDonationDto();
		savedDonationDto.setDonationId(1L);
		
		when(donationService.updateDonation(donationDto)).then(new Answer<DonationDto>() {

			@Override
			public DonationDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDonationDto; 
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/donations/update-donation")
				.content(MasterData.asJsonString(donationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	@Test
	public void testDeleteDonation() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationId(1L);

		when(this.donationService.deleteDonation(1L)).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/donations/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(true)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testDeleteDonationIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationId(1L);
		when(donationService.deleteDonation(1L)).then(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return true;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/donations/delete/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	public void testGetDonationsByDonorId() throws Exception {
		List<DonationDto> donationDtos = MasterData.getDonationDtoList();
		when(this.donationService.getDonationsByDonorId(1L)).thenReturn(donationDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get-by-donar/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donationDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDonationsByDonorIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonationDto> donationDtos = MasterData.getDonationDtoList();
		
		when(this.donationService.getDonationsByDonorId(1L)).then(new Answer<List<DonationDto>>() {

			@Override
			public List<DonationDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donationDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get-by-donar/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
	@Test
	public void testGetDonationsByNgoId() throws Exception {
		List<DonationDto> donationDtos = MasterData.getDonationDtoList();
		when(this.donationService.getDonationsByNgoId(1L)).thenReturn(donationDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get-by-ngo/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(donationDtos)) ? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetDonationsByNgoIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DonationDto> donationDtos = MasterData.getDonationDtoList();
		
		when(this.donationService.getDonationsByNgoId(1L)).then(new Answer<List<DonationDto>>() {

			@Override
			public List<DonationDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return donationDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/donations/get-by-ngo/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
	
}
