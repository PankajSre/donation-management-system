package com.iiht.training.ngo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ngo_details")
public class NgoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ngoId;
	private String ngoName;
	private String username;
	private String password;
	private String address;
	private Long phoneNumber;
	private LocalDate startedIn;
	private String documents;

	public Long getNgoId() {
		return ngoId;
	}

	public void setNgoId(Long ngoId) {
		this.ngoId = ngoId;
	}

	public String getNgoName() {
		return ngoName;
	}

	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getStartedIn() {
		return startedIn;
	}

	public void setStartedIn(LocalDate startedIn) {
		this.startedIn = startedIn;
	}

	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

}
