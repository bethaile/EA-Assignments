package edu.mum.cs.cs544.exercises;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String employeenumber;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name= "office_id")
	private Office office;
	
	public Employee() {
		
	}
	
	public Employee(String name, String employeenumber) {
		this.name = name;
		this.employeenumber = employeenumber;
	}

	public String getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(String employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	
	

}
