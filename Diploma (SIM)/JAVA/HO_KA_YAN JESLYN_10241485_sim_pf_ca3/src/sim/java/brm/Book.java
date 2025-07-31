package sim.java.brm;

/**
 * @author Jeslyn
 *
 */
public class Book {
	private int bookId;
	private String bookName;
	private String bookDescription;
	private String bookGenre;
	private double bookRentalPrice;

	public Book(int bookId, String bookGenre, double bookRentalPrice, String bookName, String bookDescription) {
		this.bookId = bookId;
		this.bookGenre = bookGenre;
		this.bookRentalPrice = bookRentalPrice;
		this.bookName = bookName;
		this.bookDescription = bookDescription;	
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public double getBookRentalPrice() {
		return bookRentalPrice;
	}

	public void setBookRentalPrice(double bookRentalPrice) {
		this.bookRentalPrice = bookRentalPrice;
	}
}
