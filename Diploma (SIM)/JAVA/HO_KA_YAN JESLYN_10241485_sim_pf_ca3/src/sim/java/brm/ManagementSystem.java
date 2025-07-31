package sim.java.brm;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jeslyn
 *
 */
public class ManagementSystem {

	private static List<AdminAccount> adminAccountList = new ArrayList<>();
	private static List<Rental> rentalList = new ArrayList<>();
	private static List<UserAccount> userAccountList = new ArrayList<>();
	private static List<Book> bookList = new ArrayList<>();
	private static AdminAccount currentLoginAdminAccount = null;
	static Scanner scanner;
	static Scanner sc;
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		sc = new Scanner(System.in);
		try {
			loadAdminAccountList();
			loadBookList();
			loadUserAccountList();
			loadRentalList();
			
			adminAccountSignIn();

			displayMenu();

			while (scanner.hasNextLine()) {
				String input = scanner.next();
				try {	
					switch (input) {
					case "1":
						System.out.println("Add admin account");
						addAdminAccount();
						break;
					case "2":
						System.out.println("Delete admin account");							
						deleteAdminAccount();
						break;
					case "3":
						System.out.println("Show admin account list");						
						showAdminAccountList();
						break;
					case "4":
						System.out.println("Add book");
						addBook();
						break;
					case "5":
						System.out.println("Delete book");							
						deleteBook();
						break;
					case "6":
						System.out.println("Show book list");
						showBookList();
						break;
					case "7":
						System.out.println("Most expensive book rental");
						getMostExpensiveBookRental();
						break;
					case "8":
						System.out.println("Cheapest book rental");
						getMostCheapestBookRental();
						break;
					case "9":
						System.out.println("Add user account");
						addUserAccount();
						break;
					case "10":
						System.out.println("Delete user account");
						deleteUserAccount();
						break;
					case "11":
						System.out.println("Show user account list");
						showUserAccountList();
						break;
					case "12":
						System.out.println("List all user rental records");
						listAllUserRentalRecords();
						break;
					case "13":
						System.out.println("Add user rental records");
						addUserRentalRecords();
						break;
					case "14":
						System.out.println("Remove user rental records");
						removeUserRentalRecords();
						break;
						
					case "15":
						System.out.println("Show top user that rent the most book");
						topUserThatRentTheMostBook();
						break;
					case "16":
						System.out.println("");
						System.out.println("Show individual user matched books (Based on their genre preferences)");
						showIndividualUserMatchedBooks();
						break;

					case "m":
					case "M":
						displayMenu();
						break;
					case "e":
					case "E":
						System.out.println("Exit");
						System.out.println("Thank you and goodbye.");
//		    				scanner.close();
//		    				System.exit(0);
						break;//Exit use break so that program exit gracefully by rung finally to set scanner to close.
					default:
						System.out.println("Unrecognized option");
						break;
					}
					if ("e".equals(input) || "E".equals(input) )
						break;	
				} catch (InputMismatchException ime) {
					System.out.println("Exception Encounter: integer expected. Please enter an integer");
				} catch (NumberFormatException nfe) {
					System.out.println("Exception Encounter: integer expected. Please enter an integer");
				}

				if (!"m".equals(input) && !"M".equals(input) ) {

					System.out.print("Option Selection? ");
				}
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Exception Encounter: integer expected. Please enter an integer");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			scanner.close();
			sc.close();
			System.out.println("*******Program End*********");
		}
	}

	private static void adminAccountSignIn() {
		System.out.println("**************************** Bookkeep Rental Management *****************************");
		System.out.println("Please sign in! (case sensitive)");
		System.out.print("Admin User name: ");
		String userName = sc.nextLine();
		System.out.print("Admin Password: ");
		String password = sc.nextLine();
		
		for (AdminAccount adminAccount : adminAccountList) {
			if (userName != null && userName.equals(adminAccount.getAdminUserName()) && password != null && password.equals(adminAccount.getAdminPassword())) {
				currentLoginAdminAccount = adminAccount;
				System.out.println("");
				System.out.println("Welcome " + adminAccount.getAdminName() + "!");
			}
		}
		System.out.println("");
		if(currentLoginAdminAccount == null) {
			System.out.println("Invaid Admin User Name or Password!");

			adminAccountSignIn();
		}

	}

	private static void displayMenu() {
		System.out.println("*************************************************************************************");
		System.out.println("*            MENU OPTION                                                            *");
		System.out.println("*    Please select an option:                                                       *");
		System.out.println("*    1.  Add admin account                                                          *");
		System.out.println("*    2.  Delete admin account                                                       *");
		System.out.println("*    3.  Show admin account list                                                    *");

		System.out.println("*    4.  Add book                                                                   *");
		System.out.println("*    5.  Delete book                                                                *");
		System.out.println("*    6.  Show book list                                                             *");
		System.out.println("*    7.  Most expensive book rental                                                 *");
		System.out.println("*    8.  Cheapest book rental                                                       *");

		System.out.println("*    9.  Add user account                                                           *");
		System.out.println("*    10. Delete user account                                                        *");
		System.out.println("*    11. Show user account list                                                     *");

		System.out.println("*    12. List all user rental records                                               *");
		System.out.println("*    13. Add user rental records                                                    *");
		System.out.println("*    14. Remove user rental records                                                 *");
		System.out.println("*    15. Show top user that rent the most books                                     *");
		System.out.println("*    16. Show individual user matched books (Based on their genre preferences)      *");
		System.out.println("*    M. Display Menu                                                                *");
		System.out.println("*    E.  Exit                                                                       *");
		System.out.println("*************************************************************************************");
		System.out.print("Option Selection? ");

	}

	private static void loadAdminAccountList() {
		System.out.println("[Method]: loadAdminAccountList");
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/AdminAccountList.txt"));

			sc.useDelimiter(System.getProperty("line.separator"));

			while (sc.hasNext()) {
				String line = sc.next();
				System.out.println("Admin Account: " + line);
				String[] lineArray = line.split("\\|");
				if (line.startsWith("#")) {
					// Skip
				} else {
					AdminAccount adminAccount = new AdminAccount(Integer.parseInt(lineArray[0]), lineArray[1], lineArray[2], lineArray[3]);
					adminAccountList.add(adminAccount);
				}
			}
			System.out.println("");
		} catch (FileNotFoundException e) {
			System.out.print("Exception Encounter: src/AdminAccountList.txt file not found!");
			return;
		} finally {
			sc.close();
		}
	}
	
	private static void loadBookList() {
		System.out.println("[Method]: loadBookList");
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/BookList.txt"));

			sc.useDelimiter(System.getProperty("line.separator"));

			while (sc.hasNext()) {
				String line = sc.next();
				System.out.println("Book: " + line);
				String[] lineArray = line.split("\\|");
				if (line.startsWith("#")) {
					// Skip
				} else {
					Book book = new Book(Integer.parseInt(lineArray[0]), lineArray[1], Double.parseDouble(lineArray[2]), lineArray[3], lineArray[4]);
					bookList.add(book);
				}
			}
			System.out.println("");
		} catch (FileNotFoundException e) {
			System.out.print("Exception Encounter: src/BookList.txt file not found!");
			return;
		} finally {
			sc.close();
		}
	}
	
	private static void loadUserAccountList() {
		System.out.println("[Method]: loadUserAccountList");
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/UserAccountList.txt"));

			sc.useDelimiter(System.getProperty("line.separator"));

			while (sc.hasNext()) {
				String line = sc.next();
				System.out.println("Use Account: " + line);
				String[] lineArray = line.split("\\|");
				if (line.startsWith("#")) {
					// Skip
				} 
				else if (line.startsWith("U")) {
					UserAccount userAccount = new UserAccount(Integer.parseInt(lineArray[1]), lineArray[2], lineArray[3], lineArray[4]);
					userAccountList.add(userAccount);
				}				
				else if (line.startsWith("P")) {
					int userId = Integer.parseInt(lineArray[1]);
					UserAccount userAccount = getUserAccount(userId);
					userAccount.addGenre(lineArray[2]);
				}
			}
			System.out.println("");
		} catch (FileNotFoundException e) {
			System.out.print("Exception Encounter: src/UserAccountList.txt file not found!");
			return;
		} finally {
			sc.close();
		}
	}
	
	private static void loadRentalList() {
		System.out.println("[Method]: loadRentalList");
		Scanner sc = null;
		try {
			sc = new Scanner(new File("src/rentalBookList.txt"));

			sc.useDelimiter(System.getProperty("line.separator"));

			while (sc.hasNext()) {
				String line = sc.next();
				System.out.println("Use Account: " + line);
				String[] lineArray = line.split("\\|");				
				
				if (line.startsWith("#")) {
					// Skip
				} 
				else if (line.startsWith("R")) {
					int rentalId =  Integer.parseInt(lineArray[1]);
					int userId = Integer.parseInt(lineArray[3]);
					UserAccount userAccount = getUserAccount(userId);
					
					if(userAccount == null)
						System.out.print("Error Encounter in loading Rental list: UserId not found"+ userId);
					else {
						Rental rental = new Rental(rentalId, lineArray[2], userAccount);
						rentalList.add(rental);
					}
				}				
				else if (line.startsWith("B")) {
					int rentalId =  Integer.parseInt(lineArray[1]);
					Rental rental = getRentalRecord(rentalId);
					
					if(rental == null)
						System.out.print("Error Encounter in loading Rental list: rentalId not found"+ rentalId);
					else {

						int bookId = Integer.parseInt(lineArray[2]);
						Book book = getBook(bookId);
						if(book == null)
							System.out.print("Error Encounter in loading Rental list: bookId not found"+ bookId);
						else {
							rental.addBook(book);
						}
					}
				}
			}
			System.out.println("");
		} catch (FileNotFoundException e) {
			System.out.print("Exception Encounter: src/rentalBookList.txt file not found!");
			return;
		} finally {
			sc.close();
		}
	}	
	
	private static void addAdminAccount() {
		System.out.println("Please enter the below data item to create Admin Account");
		System.out.print("Admin ID: " );
		
		int adminId = Integer.parseInt(sc.nextLine());
		if(getAdminAccount(adminId) != null) {
			System.out.println("Error Encounter, adminId already exist!");
			return;
		}

		System.out.print("Admin Name: " );
		String adminName = sc.nextLine();
		System.out.print("Admin User Name: " );
		String adminUserName = sc.nextLine();
		System.out.print("Admin Password: " );
		String adminPassword = sc.nextLine();

		AdminAccount adminAccount = new AdminAccount(adminId, adminName, adminUserName, adminPassword);
		adminAccountList.add(adminAccount);
		System.out.println("Record Added Successfully!");			
	}

	private static void deleteAdminAccount() {
		System.out.print("What is the Admin ID to be deleted? ");
		int adminId = Integer.parseInt(scanner.next());
		AdminAccount adminAccount = getAdminAccount(adminId);
		if(adminAccount == null) {
			System.out.println("Admin ID Not Found!");
			return;
		}
		adminAccountList.remove(adminAccount);
		System.out.println("Record Deleted Successfully!");
			
	}

	private static void showAdminAccountList() {
		System.out.println("Sno\tAdmin Id\tAdmin Name\tAdmin User Name\t\tAdmin Password");
		int i = 0;
		for (AdminAccount adminAccount : adminAccountList) {
			System.out.println(++i + "\t"+ adminAccount.getAdminId()+"\t\t" + adminAccount.getAdminName()+"\t\t" + adminAccount.getAdminUserName()+"\t\t\t" + adminAccount.getAdminPassword());
		}
		System.out.println("adminAccountList.size: "+ adminAccountList.size());
		System.out.println("");
	}

	private static void addBook() {
		System.out.println("Please enter the below data item to create Book");
		
		System.out.print("Book ID: ");
		int bookId = Integer.parseInt(sc.nextLine());
		if(getBook(bookId) != null) {	
			System.out.println("Error Encounter, bookId already exist!");
			return;
		}
		System.out.print("Book Name: ");
		String bookName = sc.nextLine();
		System.out.print("Book Description: ");
		String bookDescription = sc.nextLine();
		System.out.print("Book Genre: " );
		String bookGenre = sc.nextLine();
		System.out.print("bookRentalPrice: $");
		double bookRentalPrice = Double.parseDouble(sc.next());		
		
		Book book = new Book(bookId, bookGenre, bookRentalPrice, bookName, bookDescription);
		bookList.add(book);
		System.out.println("Record Added Successfully!");
	}

	private static void deleteBook() {
		System.out.print("What is the book ID to be deleted? ");
		int bookId = Integer.parseInt(scanner.next());
		Book book = getBook(bookId);
		if(book == null) {
			System.out.println("Book ID Not Found!");
			return;
		}

		bookList.remove(book);
		System.out.println("Record Deleted Successfully!");		
	}

	private static void showBookList() {
		System.out.println("Book ID\t\tGenre\t\tRental Price\tBook Name\t\t\t\t\t\t\t\t\tDescription");
		
		for (Book item : bookList) {
			showBook(item);
		}
		System.out.println("bookList.size: "+ bookList.size());
		System.out.println("");
	}

	private static void showBook(Book book) {
		DecimalFormat df = new DecimalFormat("00.00");
		System.out.println(book.getBookId()+"\t\t" + book.getBookGenre() +"\t$"+ df.format(book.getBookRentalPrice()) +"\t\t\t"+ book.getBookName()  +"\t"+ book.getBookDescription() );
	}

	private static void getMostExpensiveBookRental() {
		Book mostExpensiveBookRental = null;

		for (Book item : bookList) {
			if (mostExpensiveBookRental == null)
				mostExpensiveBookRental = item;
			else if (mostExpensiveBookRental.getBookRentalPrice() < item.getBookRentalPrice()) {
				mostExpensiveBookRental = item;
			}
		}
		System.out.println("Book ID\t\tGenre\t\tRental Price\tBook Name\t\t\t\t\t\t\t\t\tDescription");
		showBook(mostExpensiveBookRental);
	}

	private static void getMostCheapestBookRental() {
		Book mostCheapestBookRental = null;

		for (Book item : bookList) {
			if (mostCheapestBookRental == null)
				mostCheapestBookRental = item;
			else if (mostCheapestBookRental.getBookRentalPrice() > item.getBookRentalPrice()) {
				mostCheapestBookRental = item;
			}
		}
		System.out.println("Book ID\t\tGenre\t\tRental Price\tBook Name\t\t\t\t\t\t\t\t\tDescription");
		showBook(mostCheapestBookRental);
	}

	private static void addUserAccount() {		
		System.out.println("Please enter the below data item to Add User Account");
		System.out.print("User ID: ");
		int userId = Integer.parseInt(sc.nextLine());
		
		UserAccount userAccount = getUserAccount(userId); 
		if(getUserAccount(userId) != null) {
			System.out.println("Error Encounter, userId already exist!");
			return;
		}
		System.out.print("User First Name: ");
		String userFirstName = sc.nextLine();
		System.out.print("User Last Name: ");
		String userLastName = sc.nextLine();
		System.out.print("User Create Date in dd/MM/yyyy format: " );
		String userCreateDate = sc.nextLine();
		
		if(userCreateDate.length() != 10) {
			System.out.println("Error Encounter, invald date!");
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	    	sdf.parse(userCreateDate); 
	    }
	    catch (ParseException e)   {
	        System.out.println(userCreateDate+" is Invalid Date format");
	        return;
	    }	    

		userAccount = new UserAccount(userId, userFirstName, userLastName, userCreateDate);
		
		userAccountList.add(userAccount);
		
		while (scanner.hasNextLine()) {
			System.out.print("Genre Preference (if no more genre preference enter 'N'): ");
			String genre = sc.nextLine();
			if("N".equalsIgnoreCase(genre) )
				break;
			
			userAccount.addGenre(genre);
		}

		System.out.println("Record Added Successfully!");
			
	}

	private static void deleteUserAccount() {
		
		System.out.print("What is the User ID to be deleted? ");
		int userId =Integer.parseInt(scanner.next());
		UserAccount userAccount = getUserAccount(userId);
		
		if(userAccount == null) {
			System.out.println("User ID Not Found!");
			return;
		}
		
		userAccountList.remove(userAccount);
		System.out.println("Record Deleted Successfully!");	
	}
	
	private static void showUserAccountList() {
		System.out.println("");
		System.out.println("User ID\t\tUser First Name\t\tUser Last Name\t\tUser Create Date");
		for (UserAccount item : userAccountList) {
			showUserAccount(item);
			System.out.println("");
		}
	}

	private static void showUserAccount(UserAccount userAccount) {
		System.out.println(userAccount.getUserId() + "\t\t" + userAccount.getUserFirstName() + "\t\t\t" + userAccount.getUserLastName() + "\t\t\t" + userAccount.getUserCreateDate());
		List<String> userGenrePreferencesList = userAccount.getUserGenrePreferencesList();
		for (String item : userGenrePreferencesList) {
			System.out.println("Genre Preferences: " + item);
		}
	}

	private static void listAllUserRentalRecords() {
		for (Rental rental : rentalList) {
			listUserRentalRecords(rental);
			System.out.println("");
		}
	}
	
	private static void listUserRentalRecords(Rental rental) {
		DecimalFormat df = new DecimalFormat("00.00");
			
		UserAccount userAccount = rental.getRentalUserAccount();			
		List<Book> rentalBookList = rental.getRentalBookList();
		System.out.println("Rental ID\tRental Date\tUser ID\t\tUser Name");
		System.out.println(rental.getRentalId() + "\t\t" + rental.getRentalDate()  + "\t" + userAccount.getUserId() + "\t\t" + userAccount.getUserFirstName());
		
		System.out.println("\tBook ID\t\tRental Price\tGenre\t\tName");
		for (Book book : rentalBookList) {
			System.out.println("\t"+book.getBookId()  + "\t\t$" +df.format(book.getBookRentalPrice()) + "\t\t\t" + book.getBookGenre() + "\t" + book.getBookName());
		}
		System.out.println("Total Rental Price: $"+ df.format(rental.getTotalRentalPrice()));
	}
	
	private static void topUserThatRentTheMostBook() {
		Rental result = null;
		for (Rental rental : rentalList) {
			if (result == null)
				result = rental;
			else if (rental.getRentalBookList().size() > result.getRentalBookList().size())
				result = rental;
		}
		listUserRentalRecords(result);
	}

	private static void showIndividualUserMatchedBooks() {
		for(UserAccount userAccount : userAccountList) {
			
			System.out.println("User: (" + userAccount.getUserId() + ") " + userAccount.getUserFirstName() + " " + userAccount.getUserLastName());
			List<String> userGenrePreferencesList = userAccount.getUserGenrePreferencesList();
			System.out.println("User Genre Preferences: ");
			int i=0;
			for (String item : userGenrePreferencesList) {
				System.out.println(++i +") "+item);
			}
			
			System.out.println("Below are the books that matches the user genre preference:");
			for (Book book : bookList) {
				for (String genre : userGenrePreferencesList) {
					if (genre.trim().equals(book.getBookGenre().trim())) {
						System.out.println(book.getBookName() + " Genre(" + book.getBookGenre().trim()+")");
					}
				}
			}
			System.out.println("");
		}
	}
	
	private static void addUserRentalRecords() {
//		System.out.println("Rental Records List:");
//		listAllUserRentalRecords();
//		System.out.println("Book List:");
//		showBookList();
		
		System.out.print("\nPlease provide the rental ID: ");
		int rentalId =  Integer.parseInt(scanner.next());
		Rental rental = getRentalRecord(rentalId);
		if(rental == null) {
			System.out.println("Error Encounter, rentalId Not Found!");
			addUserRentalRecords();
		}
		
		System.out.print("Please provide the Book ID to add to the rental records: ");
		int bookId =  Integer.parseInt(scanner.next());
		Book book = getBook(bookId);
		if(book == null) {;
			System.out.println("Error Encounter, BookId Not Found!");
			addUserRentalRecords();
		}
		if(rental.getBook(bookId) != null) {
			System.out.println("Error Encounter, BookId already exist in rental record!");
			addUserRentalRecords();
		}
		
		rental.addBook(book);
				
		System.out.println("Book added to rental record Successfully!");
//		System.out.println("Rental Records List:");
//		listUserRentalRecords(rental);
	}
	
	private static void removeUserRentalRecords() {
//		System.out.println("Rental Records List:");
//		listAllUserRentalRecords();

		System.out.print("\nPlease provide the rental ID: ");
		int rentalId =  Integer.parseInt(scanner.next());
		Rental rental = getRentalRecord(rentalId);
		if(rental == null) {
			System.out.println("Error Encounter, RentalId Not Found!");
			removeUserRentalRecords();
		}
		
		System.out.print("Please provide the Book ID to remove from the rental records: ");
		int bookId =  Integer.parseInt(scanner.next());
		Book book = getBook(bookId);
		if(book == null) {
			System.out.println("Error Encounter, Book Id Not Found!");
			removeUserRentalRecords();
		}
		rental.removeBook(book);
		System.out.println("Book removed from rental record Successfully!");
		
//		System.out.println("Rental Records List:");
//		listUserRentalRecords(rental);
	}
	
	private static Book getBook(int bookId) {
		for (Book book : bookList) {
			if(bookId == book.getBookId()) {
				return book;
			}
		}
		return null;
	}
	
	private static Rental getRentalRecord(int rentalId) {
		for (Rental rental : rentalList) {
			if(rentalId == rental.getRentalId()) {
				return rental;
			}
		}
		return null;
	}
	
	private static UserAccount getUserAccount(int userId) {
		for(UserAccount userAccount : userAccountList) {
			if(userId == userAccount.getUserId()) {
				return userAccount;
			}
		}
		return null;
	}
	
	private static AdminAccount getAdminAccount(int adminId) {
		for(AdminAccount adminAccount : adminAccountList) {
			if(adminId == adminAccount.getAdminId()) {
				return adminAccount;
			}
		}
		return null;
	}	
}
