package com.iiht.training.ngo.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class DonarDto {

	private Long donarId;
	@NotNull
	private Long ngoId;
	@NotBlank
	@Length(min = 3, max = 100)
	private String donarName;
	@NotBlank
	@Length(min = 3, max = 50)
	private String username;
	@NotBlank
	@Length(min = 3, max = 50)
	private String password;
	@NotBlank
	@Email
	@Length(min = 3, max = 50)
	private String emailId;
	@NotNull
	@Min(value = 1000000000)
	@Max(value = 9999999999L)
	private Long phoneNumber;
	@NotBlank
	@Length(min = 3, max = 100)
	private String address;

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

	public String getDonarName() {
		return donarName;
	}

	public void setDonarName(String donarName) {
		this.donarName = donarName;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, donarId, donarName, emailId, ngoId, password, phoneNumber, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonarDto other = (DonarDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(donarId, other.donarId)
				&& Objects.equals(donarName, other.donarName) && Objects.equals(emailId, other.emailId)
				&& Objects.equals(ngoId, other.ngoId) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(username, other.username);
	}
	
	

}
