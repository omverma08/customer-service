package com.LTIMindtree.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="hobbies")
	private String hobbies;
	
	@Column(name="status")
	private String status;
	
	
	

	public Customer(int id, String name, String gender, String qualification, String hobbies, String status) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.qualification = qualification;
		this.hobbies = hobbies;
		this.status = status;
	}


	public Customer(){
		
	}
	
	
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
		return "Customer [id=" + id + ", name=" + name + ", gender=" + gender + ", qualification=" + qualification
				+ ", hobbies=" + hobbies + ", status=" + status + "]";
	}
	
	
	
	

}
