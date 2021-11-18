package pojo;

// private variables
// public getters/setters
// default constructor
// parametrized construction

// overide equals()
// overide hashcode()
// overide toString()

public class BookPojo {

	// convinient if the DB columns count and type matches the pojo variables
	private int id;
	private String bookTitle;
	private String bookGenre;
	private String bookAuthor;
	private int bookCost;
	private boolean bookRemoved;
	private String bookImage;
	
	public BookPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookPojo(int id, String bookTitle, String bookGenre, String bookAuthor, int bookCost, boolean bookRemoved,
			String bookImage) {
		super();
		this.id = id;
		this.bookTitle = bookTitle;
		this.bookGenre = bookGenre;
		this.bookAuthor = bookAuthor;
		this.bookCost = bookCost;
		this.bookRemoved = bookRemoved;
		this.bookImage = bookImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public int getBookCost() {
		return bookCost;
	}

	public void setBookCost(int bookCost) {
		this.bookCost = bookCost;
	}

	public boolean isBookRemoved() {
		return bookRemoved;
	}

	public void setBookRemoved(boolean bookRemoved) {
		this.bookRemoved = bookRemoved;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookPojo other = (BookPojo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookPojo [id=" + id + ", bookTitle=" + bookTitle + ", bookGenre=" + bookGenre + ", bookAuthor="
				+ bookAuthor + ", bookCost=" + bookCost + ", bookRemoved=" + bookRemoved + ", bookImage=" + bookImage
				+ "]";
	}
	
	
}
