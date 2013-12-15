package SC13Project.Milestone1.BookStore;

//Please do not change the name of the package or this class
public class BookInfo {

	/**
	 * title of the book
	 */
	private String title; 
	/**
	 * unique key of the book in stock
	 */
	private String resourceID;  
	/**
	 * ISBN13 encoding of the book
	 */
	private String ISBN13;  
	/**
	 * ISBN10 encoding of the book
	 */
	private String ISBN10; 
	/**
	 * Authors of the book
	 */
	private AuthorsInfo authors; 
	/**
	 * page number of the book
	 */
	private int pageNum; 
	/**
	 * publisher of the book
	 */
	private String publisher; 
	/**
	 * publishing date of the book
	 */
	private String publishdate;
	/**
	 * version of the book
	 */
	private String version; 
	/**
	 * price of the book
	 */
	private double price; 
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public String getISBN13() {
		return ISBN13;
	}

	public void setISBN13(String iSBN13) {
		ISBN13 = iSBN13;
	}

	public String getISBN10() {
		return ISBN10;
	}

	public void setISBN10(String iSBN10) {
		ISBN10 = iSBN10;
	}

	public AuthorsInfo getAuthors() {
		return authors;
	}

	public void setAuthors(AuthorsInfo authors) {
		this.authors = authors;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
