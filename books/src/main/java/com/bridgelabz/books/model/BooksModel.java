package com.bridgelabz.books.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.bridgelabz.books.dto.BooksDTO;

import lombok.Data;

/**
 * Purpose:Creating model for books
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
@Entity
@Data
@Table(name = "books_details")
public class BooksModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookId;
	private String bookName;
	private String author;
	private String description;
	private double price;
	private int quantity;
	@Lob
	private byte[] logo;

	public BooksModel(BooksDTO booksDTO) {
		this.bookName = booksDTO.getBookName();
		this.author = booksDTO.getAuthor();
		this.description = booksDTO.getDescription();
		this.price = booksDTO.getPrice();
		this.quantity = booksDTO.getQuantity();
	}

	public BooksModel() {
		super();
	}

}
