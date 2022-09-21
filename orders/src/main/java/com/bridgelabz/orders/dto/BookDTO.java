package com.bridgelabz.orders.dto;



import lombok.Data;

@Data
public class BookDTO {
	private String bookName;
	private String author;
	private String description;
	private double price;
	private int quantity;
}