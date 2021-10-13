package com.iiht.training.ngo.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DonationDto {

	private Long donationId;
	@NotNull
	private Long donarId;
	@NotNull
	private Long ngoId;
	@NotBlank
	@Length(min = 3, max = 100)
	private String donationType;
	@NotNull
	private Double amount;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Future
	private LocalDate donationDate;

	public Long getDonationId() {
		return donationId;
	}

	public void setDonationId(Long donationId) {
		this.donationId = donationId;
	}

	public Long getDonarId() {
		return donarId;
	}

	public void setDonarId(Long donarId) {
		this.donarId = donarId;
	}

	public Long getNgoId() {
		return ngoId;
	}

	public void setNgoId(Long ngoId) {
		this.ngoId = ngoId;
	}

	public String getDonationType() {
		return donationType;
	}

	public void setDonationType(String donationType) {
		this.donationType = donationType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(LocalDate donationDate) {
		this.donationDate = donationDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, donarId, donationDate, donationId, donationType, ngoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonationDto other = (DonationDto) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(donarId, other.donarId)
				&& Objects.equals(donationDate, other.donationDate) && Objects.equals(donationId, other.donationId)
				&& Objects.equals(donationType, other.donationType) && Objects.equals(ngoId, other.ngoId);
	}
	
	

}
