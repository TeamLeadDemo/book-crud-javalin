package service;

import java.util.List;

import exception.ApplicationException;
import pojo.BookPojo;

public interface BookService {
	BookPojo addBook(BookPojo bookPojo) throws ApplicationException;
	BookPojo updateBook(BookPojo bookPojo) throws ApplicationException;
	boolean deleteBook(int bookId) throws ApplicationException;
	List<BookPojo> getAllBooks() throws ApplicationException;
	BookPojo getABook(int bookId) throws ApplicationException;
	void exitApplication(); 
}
