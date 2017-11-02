package edu.mum.cs.cs544.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentid;
	private String fristname;
	private String lastname;
	
	@ManyToMany (cascade = {CascadeType.ALL})
	@JoinTable(name = "Student_Course",
	joinColumns = { @JoinColumn(name = "Student_id")},
	inverseJoinColumns = {@JoinColumn(name = "Course_id")})
	List<Course> courses = new ArrayList();
	
	public Student() {
		
	}
	
	public Student(String fristname, String lastname) {
		this.fristname = fristname;
		this.lastname = lastname;			
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getFristname() {
		return fristname;
	}

	public void setFristname(String fristname) {
		this.fristname = fristname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	

}
