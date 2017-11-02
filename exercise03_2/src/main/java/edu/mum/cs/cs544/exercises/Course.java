package edu.mum.cs.cs544.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String coursenumber;
	private String name;

	@ManyToMany(mappedBy ="courses",cascade = {CascadeType.ALL})
	private List<Student> students = new ArrayList();

	public Course() {

	}

	public Course(String coursenumber, String name) {

		this.coursenumber = coursenumber;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoursenumber() {
		return coursenumber;
	}

	public void setCoursenumber(String coursenumber) {
		this.coursenumber = coursenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addStudent(Student studnet) {
		students.add(studnet);
	}

}
