package com.bridgelabz.books.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Purpose:Creating DTO for books
 * 
 * @author Manoj
 * @Param all the required variables to view in web page Version 1.0
 */
@Data
public class BooksDTO {

	@NotNull(message = "bookName Should Not be Null")
	private String bookName;
	@NotNull(message = "author Should Not be Null")
	private String author;
	@NotNull(message = "description Should Not be Null")
	private String description;
	@NotNull(message = "price Should Not be Null")
	private double price;
	@NotNull(message = "quantity Should Not be Null")
	private int quantity;
}
