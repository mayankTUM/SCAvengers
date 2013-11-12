package SC13Project.Milestone1.BookStore;

import java.util.List;

//Please do not change the name of the package or this interface
public interface BookCategoryWS {

	/**
	 * Get the names of all the books
	 * @return the list of names for all the books
	 */
	 public List<String> getAllBooKNames();
	 
	 /**
	  * Get the ISBN10 codings of all the books
	  * @return the list of ISBN10 codings for all the books
	  */
	 public List<String> getAllBookISBN10();
	 
	 /**
	  * Get the ISBN13 codings of all the books
	  * @return the list of ISBN13 codings for all the books
      */
	 public List<String> getAllBookISBN13();
	 
	 /**
	  * Get all the books of a title
	  * @param title the title of the books
	  * @return the list of book info with given title
	  */
	 public List<BookInfo> getBooksByTitle(String title);
	 
	 /**
	  * Get all the books of an author
	  * @param author the author of the books
	  * @return the list of books written by the given author
	  * 
	  */
	 public List<BookInfo> getBooksByAuthor(String author);
	 
	 /**
	  * Get a book info by ISBN10
	  * @param isbn10 the ISBN10 coding of the book
	  * @return the book with the given ISBN10 coding
	  * 
	  */
	 public BookInfo getBookInfobyISBN10(String isbn10);
	 
	 /**
	  * Get a book info by ISBN13
	  * @param isbn13 the ISBN13 coding of the book
	  * @return the book with the given ISBN13 coding
	  * 
	  */
	 public BookInfo getBookInfobyISBN13(String isbn13);
	 
}
