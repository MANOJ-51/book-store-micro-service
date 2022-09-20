package com.bridgelabz.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.books.model.BooksModel;

/**
 * Purpose:Creating repository for books
 * 
 * @author Manoj
 * @Param all the required variables to save in repository Version 1.0
 */
public interface IBooksRepository  extends JpaRepository<BooksModel, Long>{

}
