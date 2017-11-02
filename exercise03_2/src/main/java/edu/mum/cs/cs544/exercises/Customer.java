package edu.mum.cs.cs544.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToMany
	@JoinColumn(name = "customer_id")
	private List<Reservation> reservations = new ArrayList();
	
	public Customer() {
		
	}
	
	public Customer(String name) {
		this.name = name;
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

	

	public void addReservations(Reservation reservation) {
		reservations.add(reservation);
	}
	
	
	
	

}
