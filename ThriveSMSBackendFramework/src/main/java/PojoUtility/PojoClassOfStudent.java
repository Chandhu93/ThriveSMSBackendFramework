package PojoUtility;

public class PojoClassOfStudent {
	
	private String fullName;
	private String address;
	private String email;
	private String phone;
	private String dateOfBirth;
	private String gender;
	
	
	public PojoClassOfStudent() {}


	public PojoClassOfStudent(String fullName, String address, String email, String phone, String dateOfBirth,
			String gender) {
		super();
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}
