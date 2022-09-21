package com.bridgelabz.books.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.books.dto.BooksDTO;
import com.bridgelabz.books.exception.CustomExceptions;
import com.bridgelabz.books.model.BooksModel;
import com.bridgelabz.books.repository.IBooksRepository;
import com.bridgelabz.books.utill.BookResponse;
import com.bridgelabz.books.utill.ResponseClass;
import com.bridgelabz.books.utill.TokenUtill;

/**
 * Purpose:Creating Service for books
 * 
 * @author Manoj
 * @Param business logic is present here Version 1.0
 */
@Service
public class BooksService implements IBooksService {

	@Autowired
	IBooksRepository iBooksRepository;

	@Autowired
	TokenUtill tokenUtill;

	@Autowired
	MailService mailService;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Purpose:Creating method to add book
	 * 
	 * @author Manoj
	 * @Param books dto
	 */
	@Override
	public ResponseClass createBook(String token, @Valid BooksDTO booksDTO) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("validate") + token, Boolean.class);
		if (isUserPresent) {
			BooksModel booksModel = new BooksModel(booksDTO);
			iBooksRepository.save(booksModel);
			return new ResponseClass(200, "success", booksModel);
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to Update book
	 * 
	 * @author Manoj
	 * @Param token,id,bookdto
	 */
	@Override
	public ResponseClass updateBook(String token, Long bookId, @Valid BooksDTO booksDTO) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("validate") + token, Boolean.class);
		if (isUserPresent) {
			Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
			if (isBookIdPresent.isPresent()) {
				isBookIdPresent.get().setBookName(booksDTO.getBookName());
				isBookIdPresent.get().setAuthor(booksDTO.getAuthor());
				isBookIdPresent.get().setDescription(booksDTO.getDescription());
				isBookIdPresent.get().setQuantity(booksDTO.getQuantity());
				isBookIdPresent.get().setPrice(booksDTO.getPrice());
				iBooksRepository.save(isBookIdPresent.get());
				return new ResponseClass(200, "success", isBookIdPresent.get());
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to get List of books
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public List<BooksModel> getList(String token) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("validate") + token, Boolean.class);
		if (isUserPresent) {
			List<BooksModel> getList = iBooksRepository.findAll();
			if (getList.size() > 0) {
				return getList;
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to Delete book
	 * 
	 * @author Manoj
	 * @Param bookId ,token
	 */
	@Override
	public ResponseClass deleteBook(String token, Long bookId) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("validate") + token, Boolean.class);
		if (isUserPresent) {
			Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
			if (isBookIdPresent.isPresent()) {
				iBooksRepository.delete(isBookIdPresent.get());
				return new ResponseClass(200, "success", isBookIdPresent.get());
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to change book quantity
	 * 
	 * @author Manoj
	 * @Param token,id,new quantity
	 */
	@Override
	public ResponseClass changeQuantity(String token, Long bookId, @Valid int newQuantity) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("validate") + token, Boolean.class);
		if (isUserPresent) {
			Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
			if (isBookIdPresent.isPresent()) {
				int quantity = isBookIdPresent.get().getQuantity() + newQuantity;
				isBookIdPresent.get().setQuantity(quantity);
				iBooksRepository.save(isBookIdPresent.get());
				return new ResponseClass(200, "success", isBookIdPresent.get());
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to book price
	 * 
	 * @author Manoj
	 * @Param token,id,new price
	 */
	@Override
	public ResponseClass changePrice(String token, Long bookId, @Valid double newPrice) {
		boolean isUserPresent = restTemplate.getForObject(System.getenv("validate") + token, Boolean.class);
		if (isUserPresent) {
			Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
			if (isBookIdPresent.isPresent()) {
				isBookIdPresent.get().setPrice(newPrice);
				iBooksRepository.save(isBookIdPresent.get());
				return new ResponseClass(200, "success", isBookIdPresent.get());
			}
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to validate book using token and id
	 * 
	 * @author Manoj
	 * @Param token
	 */
	@Override
	public BookResponse validateBook(Long bookId) {
		Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
		if (isBookIdPresent.isPresent()) {
			return new BookResponse(200, "success", isBookIdPresent.get());
		}
		throw new CustomExceptions(400, "token not valid");
	}

	/**
	 * Purpose:Creating method to change book quantity
	 * 
	 * @author Manoj
	 * @Param token,id,new quantity
	 */
	@Override
	public ResponseClass updateQuantity(Long bookId, @Valid int newQuantity) {
		Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
		if (isBookIdPresent.isPresent()) {
			int changedQuantity =isBookIdPresent.get().getQuantity() - newQuantity;
			isBookIdPresent.get().setQuantity(changedQuantity);
			iBooksRepository.save(isBookIdPresent.get());
			return new ResponseClass(200, "success", isBookIdPresent.get());
		}
		throw new CustomExceptions(400, "no book found");
	}

	/**
	 * Purpose:Creating method to change book quantity
	 * 
	 * @author Manoj
	 * @Param token,id,new quantity
	 */
	@Override
	public ResponseClass retrieveQuantity(Long bookId, int newQuantity) {
		Optional<BooksModel> isBookIdPresent = iBooksRepository.findById(bookId);
		if (isBookIdPresent.isPresent()) {
			int changedQuantity =isBookIdPresent.get().getQuantity() + newQuantity;
			isBookIdPresent.get().setQuantity(changedQuantity);
			iBooksRepository.save(isBookIdPresent.get());
			return new ResponseClass(200, "success", isBookIdPresent.get());
		}
		throw new CustomExceptions(400, "no book found");
	}
}
