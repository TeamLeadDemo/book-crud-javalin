package dao;

import java.util.ArrayList;
import java.util.List;

import pojo.BookPojo;

public class BookDaoImpl implements BookDao{

	List<BookPojo> allBooksStore;
	
	public BookDaoImpl() {
		this.allBooksStore = new ArrayList<BookPojo>();
	}

	@Override
	public BookPojo addBook(BookPojo bookPojo) {
		bookPojo.setId(allBooksStore.size() + 1);
		allBooksStore.add(bookPojo);
		return bookPojo;
	}

	@Override
	public BookPojo updateBook(BookPojo bookPojo) {
		System.out.println(bookPojo.getId());
		for(int i=0; i<allBooksStore.size(); i++) {
			System.out.println(allBooksStore.get(i).getId());
			if(allBooksStore.get(i).getId() == bookPojo.getId()) {
				System.out.println("Match found!");
				allBooksStore.set(i, bookPojo);
				break;
			}
		} 
		return bookPojo;
	}

	@Override
	public boolean deleteBook(int bookId) {
		int currentSize = allBooksStore.size();
		allBooksStore.removeIf(bookPojo -> bookPojo.getId() == bookId );
		int nowSize = allBooksStore.size();
		if(currentSize == nowSize) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public List<BookPojo> getAllBooks() {
		return allBooksStore;
	}

	@Override
	public BookPojo getABook(int bookId) {
		for(BookPojo bookPojo: allBooksStore) {
			if(bookPojo.getId() == bookId) {
				return bookPojo;
			}
		}
		return null;
	}

	@Override
	public void exitApplication() {
		// TODO Auto-generated method stub
		
	}

	

}
