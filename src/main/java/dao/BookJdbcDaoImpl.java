package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import exception.ApplicationException;
import pojo.BookPojo;

public class BookJdbcDaoImpl implements BookDao {

	private static final Logger logger = LogManager.getLogger(BookJdbcDaoImpl.class);
	
	@Override
	public BookPojo addBook(BookPojo bookPojo) throws ApplicationException {
		logger.info("Entered addBook() in dao.");
		
		// this bookPojo does not have a book id set in it.
		//set the book_removed to false
		bookPojo.setBookRemoved(false);
		
		// jdbc steps 3 and 4
		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
//			String query = "insert into book_details(book_title, book_author, book_genre, book_cost, book_removed)" 
//							+ "values('"+bookPojo.getBookTitle()+"','"+bookPojo.getBookAuthor()
//							+"','"+bookPojo.getBookGenre()+"',"+bookPojo.getBookCost()
//							+","+bookPojo.isBookRemoved()+")";
//			
//			int rowsAffected = stmt.executeUpdate(query);
//			if(rowsAffected != 0) { // means the record got inserted successfully
//				// take out the primary key and store in the bookPojo object
//				bookPojo.setBookId(1);// hard coded to 1 - but later will figure out to fetch the generated
//										// primary key from DB
//			}
			
			// fixed the code to return the generated book_id
			String query = "insert into book_details(book_title, book_author, book_genre, book_cost, book_removed, book_image)" 
					+ "values('"+bookPojo.getBookTitle()+"','"+bookPojo.getBookAuthor()
					+"','"+bookPojo.getBookGenre()+"',"+bookPojo.getBookCost()
					+","+bookPojo.isBookRemoved()+",'"+bookPojo.getBookImage()+"') returning book_id";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			bookPojo.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Exited addBook() in dao.");
		return bookPojo;
	}

	@Override
	public BookPojo updateBook(BookPojo bookPojo) throws ApplicationException {
		logger.info("Entered updateBook() in dao.");
		
		// jdbc step 3 and 4
		Connection conn = DBUtil.makeConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "update book_details set book_cost="+bookPojo.getBookCost()
							+" where book_id="+bookPojo.getId();

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());		
		}
		
		logger.info("Exited updateBook() in dao.");
		return bookPojo;
	}

	@Override
	public boolean deleteBook(int bookId) throws ApplicationException {
		logger.info("Entered deleteBook() in dao.");
		
		boolean flag = true;
		Connection conn = DBUtil.makeConnection();
		int rowsAffected = 0;
		try {
			Statement stmt = conn.createStatement();
			// here we are not going to do a hard delete, we are going 
			// for a soft delete.
			String query = "update book_details set book_removed=true where book_id="+bookId;
			rowsAffected = stmt.executeUpdate(query);
			System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if(rowsAffected == 0)
			flag = false;
		
		logger.info("Exited deleteBook() in dao.");
		return flag;
	}

	@Override
	public List<BookPojo> getAllBooks() throws ApplicationException {
		logger.info("Entered getAllBooks() in dao.");
		
		// create a empty collection which is going to hold all the reords from the DB
		// as pojo Object
		List<BookPojo> allBooksStore = new ArrayList<BookPojo>();

		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from book_details where book_removed=false";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// here as we iterate through the rs we should
				// each record in a pojo object and
				// add it to the collection
				// and at the end we return the collection

				// as we iterate we are taking each record and storing it in a bookPojo object
				BookPojo bookPojo = new BookPojo(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(3),
						rs.getInt(5), rs.getBoolean(6), rs.getString(7));

				// add the bookPojo obj to a collection
				allBooksStore.add(bookPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllBooks() in dao.");
		return allBooksStore;
	}

	@Override
	public BookPojo getABook(int bookId) throws ApplicationException {
		logger.info("Entered getABook() in dao.");
		
		Connection conn = DBUtil.makeConnection();
		Statement stmt;
		BookPojo bookPojo = null;
		try {
			stmt = conn.createStatement();
			String query = "select * from book_details where book_id="+bookId
							+ "and book_removed=false";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				bookPojo = new BookPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getBoolean(6), rs.getString(7));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getABook() in dao.");
		return bookPojo;
	}

	@Override
	public void exitApplication() {
		logger.info("Entered exitApplication() in dao.");
		DBUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");
	}

}
