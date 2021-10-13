package com.iiht.training.ngo.boundary;

import static com.iiht.training.ngo.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.ngo.testutils.TestUtils.currentTest;
import static com.iiht.training.ngo.testutils.TestUtils.testReport;
import static com.iiht.training.ngo.testutils.TestUtils.yakshaAssert;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.training.ngo.dto.DonarDto;
import com.iiht.training.ngo.dto.DonationDto;
import com.iiht.training.ngo.dto.DonationRequestDto;
import com.iiht.training.ngo.dto.NgoDto;
import com.iiht.training.ngo.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNgoNameNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setNgoName("");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoNameMinThree() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setNgoName("Ab");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoNameMaxHundred() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		String ngoName = "";
		for (int i = 0; i < 120; i++) {
			ngoName.concat("A");
		}
		ngoDto.setNgoName(ngoName);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoUsernameNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setUsername("");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoUsernameMinThree() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setUsername("Ab");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoUsernameMaxHundred() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		String username = "";
		for (int i = 0; i < 120; i++) {
			username.concat("A");
		}
		ngoDto.setUsername(username);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoPasswordNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPassword("");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoPasswordMinThree() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPassword("Ab");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoPasswordMaxHundred() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		String password = "";
		for (int i = 0; i < 120; i++) {
			password.concat("A");
		}
		ngoDto.setPassword(password);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoAddressNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setAddress("");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoAddressMinThree() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setAddress("Ab");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoAddressMaxHundred() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		String address = "";
		for (int i = 0; i < 120; i++) {
			address.concat("A");
		}
		ngoDto.setAddress(address);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoPhoneNumberNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPhoneNumber(null);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoPhoneNumberMinTen() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPhoneNumber(1234L);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoPhoneNumberMaxTen() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setPhoneNumber(123456789012L);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoStartedInNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setStartedIn(null);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoStartedInPastDate() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setStartedIn(LocalDate.of(2022, 10, 20));
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoDocumentsNotNull() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setDocuments(null);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoDocumentsMinThree() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		ngoDto.setDocuments("Ab");
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testNgoDocumentsMaxHundred() throws Exception {
		NgoDto ngoDto = MasterData.getNgoDto();
		String document = "";
		for (int i = 0; i < 120; i++) {
			document.concat("A");
		}
		ngoDto.setDocuments(document);
		Set<ConstraintViolation<NgoDto>> violations = validator.validate(ngoDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarNgoIdNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setNgoId(null);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarNameNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setDonarName("");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDomarNameMinThree() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setDonarName("Ab");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarNameMaxHundred() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		String donarName = "";
		for (int i = 0; i < 120; i++) {
			donarName.concat("A");
		}
		donarDto.setDonarName(donarName);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarUsernameNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setUsername("");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarUsernameMinThree() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setUsername("Ab");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarUsernameMaxHundred() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		String username = "";
		for (int i = 0; i < 120; i++) {
			username.concat("A");
		}
		donarDto.setUsername(username);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarPasswordNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setPassword("");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarPasswordMinThree() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setPassword("Ab");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarPasswordMaxHundred() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		String password = "";
		for (int i = 0; i < 120; i++) {
			password.concat("A");
		}
		donarDto.setPassword(password);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarEmailNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setEmailId("");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarEmailMinThree() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setEmailId("Ab");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarEmailMaxHundred() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		String email = "";
		for (int i = 0; i < 120; i++) {
			email.concat("A");
		}
		donarDto.setEmailId(email);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarEmailValidFormat() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setEmailId("abc");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarPhoneNumberNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setPhoneNumber(null);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarPhoneNumberMinTen() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setPhoneNumber(12345L);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarPhoneNumberMaxTen() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setPhoneNumber(123456789012L);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarAddressNotNull() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setAddress("");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarAddressMinThree() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		donarDto.setAddress("Ab");
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonarAddressMaxHundred() throws Exception {
		DonarDto donarDto = MasterData.geDonarDto();
		String address = "";
		for (int i = 0; i < 120; i++) {
			address.concat("A");
		}
		donarDto.setAddress(address);
		Set<ConstraintViolation<DonarDto>> violations = validator.validate(donarDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationNgoIdNotNull() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setNgoId(null);
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonarIdNotNull() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonarId(null);
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonationTypeNotNull() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationType("");
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonationTypeMinThree() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationType("Ab");
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonationTypeMaxHundred() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		String donationType = "";
		for (int i = 0; i < 120; i++) {
			donationType.concat("A");
		}
		donationDto.setDonationType(donationType);
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationAmountNotNull() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setAmount(null);
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonationDateNotNull() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationDate(null);
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonationDateNotPastDate() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();

		donationDto.setDonationDate(LocalDate.of(2020, 10, 20));
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	@Disabled
	public void testDonationDonationDateNotFutureDate() throws Exception {
		DonationDto donationDto = MasterData.getDonationDto();
		donationDto.setDonationDate(LocalDate.of(2022, 10, 20));
		Set<ConstraintViolation<DonationDto>> violations = validator.validate(donationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationRequestNgoIdNotNull() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setNgoId(null);
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationRequestDonarIdNotNull() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setDonarId(null);
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationRequestAmountNotNull() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setAmount(null);
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationRequestRequestStatusNotNull() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setRequestStatus(null);
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationRequestRequestStatusMinThree() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setRequestStatus("Ab");
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationRequestRequestStatusMaxHundred() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		String requestStatus = "";
		for (int i = 0; i < 120; i++) {
			requestStatus.concat("A");
		}
		requestDto.setRequestStatus(requestStatus);
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}
	
	@Test
	public void testDonationDonationEndDateNotNull() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setDonationEndDate(null);
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDonationDonationEndDateNotPastDate() throws Exception {
		DonationRequestDto requestDto = MasterData.getDonationRequestDto();
		requestDto.setDonationEndDate(LocalDate.of(2020, 10, 20));
		Set<ConstraintViolation<DonationRequestDto>> violations = validator.validate(requestDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
