package com.bridgelabz.orders.utill;

import com.bridgelabz.orders.dto.BookDTO;
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
	private BookDTO books;
	
	public BookResponse() {
		super();
	}

}
