package sim.java.brm;

import java.util.ArrayList;

/**
 * @author Jeslyn
 *
 */
public class Rental {
	private int rentalId;
	private String rentalDate;
	private UserAccount rentalUserAccount;
	private ArrayList<Book> rentalBookList;

	public Rental(int rentalId, String rentalDate, UserAccount rentalUserAccount) {
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.rentalUserAccount = rentalUserAccount;
		this.rentalBookList = new ArrayList<>();
	}

	public void addBook(Book book) {
		this.rentalBookList.add(book);
	}

	public void removeBook(Book book) {
		this.rentalBookList.remove(book);
	}

	public double getTotalRentalPrice() {
		double totalRentalPrice = 0;

		for (Book book : rentalBookList) {
			totalRentalPrice += book.getBookRentalPrice();
		}
		
//		for(int i=0; i<rentalBookList.size(); i++) {
//			Book book = rentalBookList.get(i);
//			totalRentalPrice += book.getBookRentalPrice();
//		}
		return totalRentalPrice;
	}

	public Book getBook(int bookId) {
		for(Book book : rentalBookList) {			
			if(bookId == book.getBookId()) {
				return book;
			}
		}
		return null;
	}	
	
	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public UserAccount getRentalUserAccount() {
		return rentalUserAccount;
	}

	public void setRentalUserAccount(UserAccount rentalUserAccount) {
		this.rentalUserAccount = rentalUserAccount;
	}

	public ArrayList<Book> getRentalBookList() {
		return rentalBookList;
	}

	public void setRentalBookList(ArrayList<Book> rentalBookList) {
		this.rentalBookList = rentalBookList;
	}
}
