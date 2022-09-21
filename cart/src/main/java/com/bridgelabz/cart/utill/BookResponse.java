package com.bridgelabz.cart.utill;

import com.bridgelabz.cart.dto.BooksDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Purpose:Creating Response class
 * 
 * @author Manoj
 * @Param errorMessage,ErrorMessage,. Version 1.0
 */
@Data
@AllArgsConstructor
public class BookResponse {

	private int errorCode;
	private String errorMessage;
	private BooksDTO books;
	
	public BookResponse() {
		super();
	}

}
