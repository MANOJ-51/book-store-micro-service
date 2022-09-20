package com.bridgelabz.books.service;

import java.util.List;
import javax.validation.Valid;
import com.bridgelabz.books.dto.BooksDTO;
import com.bridgelabz.books.model.BooksModel;
import com.bridgelabz.books.utill.ResponseClass;

/**
 * Purpose:Creating APIS for user Controller
 * 
 * @author Manoj
 * @Param abstract METHODS Version 1.0
 */
public interface IBooksService {

	ResponseClass createBook(String token, @Valid BooksDTO booksDTO);

	ResponseClass updateBook(String token, Long bookId, @Valid BooksDTO booksDTO);

	List<BooksModel> getList(String token);

	ResponseClass deleteBook(String token, Long bookId);

	ResponseClass changeQuantity(String token, Long bookId, @Valid int newQuantity);

	ResponseClass changePrice(String token, Long bookId, @Valid double newPrice);

}
