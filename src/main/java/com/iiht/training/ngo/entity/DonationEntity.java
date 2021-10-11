package com.iiht.training.ngo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "donation")
public class DonationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long donationId;
	private Long donarId;
	private Long ngoId;
	private String donationType;
	private Double amount;
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

}
