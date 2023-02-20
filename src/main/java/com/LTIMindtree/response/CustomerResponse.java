package com.LTIMindtree.response;

public class CustomerResponse {
	
	
	private int id;
	
	
	private String name;
	
	
	private String gender;
	
	private String qualification;
	
	private String hobbies;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CustomerResponse [id=" + id + ", name=" + name + ", gender=" + gender + ", qualification="
				+ qualification + ", hobbies=" + hobbies + ", status=" + status + "]";
	}
	

	
	
}
