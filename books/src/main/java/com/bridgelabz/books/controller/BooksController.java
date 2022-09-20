package com.bridgelabz.books.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.books.dto.BooksDTO;
import com.bridgelabz.books.model.BooksModel;
import com.bridgelabz.books.service.IBooksService;
import com.bridgelabz.books.utill.ResponseClass;

/**
 * Purpose:Creating APIS for user Controller
 * 
 * @author Manoj
 * @Param Http METHODS Version 1.0
 */
@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	IBooksService iBooksService;

	/**
	 * Purpose:Creating method to add book
	 * 
	 * @author Manoj
	 * @Param book dto
	 */
	@PostMapping("/createBook")
	public ResponseEntity<ResponseClass> createBook(@RequestHeader String token,
			@Valid @RequestBody BooksDTO booksDTO) {
		ResponseClass responseClass = iBooksService.createBook(token, booksDTO);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to Update book
	 * 
	 * @author Manoj
	 * @Param token,id,bookdto
	 */
	@PutMapping("/updatebook/{bookId}")
	public ResponseEntity<ResponseClass> updateBook(@RequestHeader String token, @Valid @RequestBody BooksDTO booksDTO,
			@PathVariable Long bookId) {
		ResponseClass responseClass = iBooksService.updateBook(token, bookId, booksDTO);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to get List of books
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@GetMapping("/booksList")
	public ResponseEntity<List<?>> getList(@RequestHeader String token) {
		List<BooksModel> responseClass = iBooksService.getList(token);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to Delete book
	 * 
	 * @author Manoj
	 * @Param bookId ,token
	 */
	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<ResponseClass> deleteBook(@RequestHeader String token, @PathVariable Long bookId) {
		ResponseClass responseClass = iBooksService.deleteBook(token, bookId);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to change book quantity
	 * 
	 * @author Manoj
	 * @Param token,id,new quantity
	 */
	@PutMapping("/changeQuantity/{bookId}")
	public ResponseEntity<ResponseClass> changeQuantity(@RequestHeader String token,
			@Valid @RequestParam int newQuantity, @PathVariable Long bookId) {
		ResponseClass responseClass = iBooksService.changeQuantity(token, bookId, newQuantity);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}

	/**
	 * Purpose:Creating method to book price
	 * 
	 * @author Manoj
	 * @Param token,id,new price
	 */
	@PutMapping("/changePrice/{bookId}")
	public ResponseEntity<ResponseClass> changePrice(@RequestHeader String token, @Valid @RequestParam double newPrice,
			@PathVariable Long bookId) {
		ResponseClass responseClass = iBooksService.changePrice(token, bookId, newPrice);
		return new ResponseEntity<>(responseClass, HttpStatus.OK);
	}
}
