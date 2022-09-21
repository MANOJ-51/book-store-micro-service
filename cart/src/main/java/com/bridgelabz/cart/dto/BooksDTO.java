package com.bridgelabz.cart.dto;

import lombok.Data;

@Data
public class BooksDTO {
	private String bookName;
	private String author;
	private String description;
	private double price;
	private int quantity;
}
