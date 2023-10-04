package com.example.demo;
import jakarta.persistence.*;

@Entity
public class Student_Entity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	//Entities
    private Integer id;
	private String firstName;
    private String lastName;
    private String subjectMajor;
    private int yearOfGraduation;
    
    // getters and setters
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSubjectMajor() {
		return subjectMajor;
	}
	public void setSubjectMajor(String subjectMajor) {
		this.subjectMajor = subjectMajor;
	}
	public int getYearOfGraduation() {
		return yearOfGraduation;
	}
	public void setYearOfGraduation(int yearOfGraduation) {
		this.yearOfGraduation = yearOfGraduation;
	}

}
