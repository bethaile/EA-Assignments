package edu.mum.cs.cs544.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Office {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String roomnumber;
	private String building;
	
	@OneToMany(mappedBy="office")
	private List<Employee> employees = new ArrayList();
	
	public Office() {
		
	}
	
	public Office(String building, String roomnumber  ) {
		this.building = building;
		this.roomnumber = roomnumber;
	}

	public String getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
