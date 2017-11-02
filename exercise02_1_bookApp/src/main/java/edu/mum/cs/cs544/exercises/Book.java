package edu.mum.cs.cs544.exercises;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String ISBN;
	private String author;
	private double price;

	public Book() {
	}

	public Book(String title, String author,  String isbn, double price) {
		this.title = title;
		this.ISBN = isbn;
		this.price = price;
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setISBN(String isbn) {
		this.ISBN = isbn;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	

}
