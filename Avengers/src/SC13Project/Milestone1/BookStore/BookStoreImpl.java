package SC13Project.Milestone1.BookStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class BookStoreImpl implements BookCategoryWS {

	Document dom;
	DocumentBuilderFactory dbf = null;
	DocumentBuilder db = null;
	Element doc = null;
	@Override
	public List<String> getAllBooKNames() {
		createDOM();
		List<String> bookNames = new ArrayList<String>();
		NodeList nl = doc.getElementsByTagName("title");
		int len = nl.getLength();
		System.out.println("Length is "+ len);
		int iter = 0;
		while(iter!=len)
		{
			bookNames.add(nl.item(iter).getTextContent());
			iter++;
		}
		return bookNames;
	}

	@Override
	public List<String> getAllBookISBN10() {
		createDOM();
		List<String> bookISBN10 = new ArrayList<String>();
		NodeList nl = doc.getElementsByTagName("ISBN10");
		int len = nl.getLength();
		int iter = 0;
		while(iter!=len)
		{
			bookISBN10.add(nl.item(iter).getTextContent());
			iter++;
		}
		return bookISBN10;
	}

	@Override
	public List<String> getAllBookISBN13() {
		createDOM();
		List<String> bookISBN13 = new ArrayList<String>();
		NodeList nl = doc.getElementsByTagName("ISBN13");
		int len = nl.getLength();
		int iter = 0;
		while(iter!=len)
		{
			bookISBN13.add(nl.item(iter).getTextContent());
			iter++;
		}
		return bookISBN13;
	}

	@Override
	public List<BookInfo> getBooksByTitle(String title) {
		List<BookInfo> list = new ArrayList<BookInfo>();
		createDOM();
		NodeList nl = doc.getElementsByTagName("book");
		int len = nl.getLength();
		int iter = 0;
		while(iter!=len)
		{
			Node t1 = nl.item(iter);
			if (t1.getNodeType() == Node.ELEMENT_NODE)
			{
				Element eElement = (Element) t1;
				if(eElement.getElementsByTagName("title").item(0).getTextContent().equals(title)) 
					{
						BookInfo bookInfo = new BookInfo();
						bookInfo.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
						bookInfo.setResourceID(eElement.getElementsByTagName("resourceID").item(0).getTextContent());
						bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
						bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
						ArrayList<String> authorlist = new ArrayList<String>();
						Node authors = eElement.getElementsByTagName("authors").item(0);
						Element aElement = (Element) authors;
						NodeList authorNames = aElement.getElementsByTagName("author");
						int size = authorNames.getLength();
						int count = 0;
						while(size!=count)
						{
							authorlist.add(authorNames.item(count).getTextContent());
							count++;
						}
						AuthorsInfo authorsInfo = new AuthorsInfo();
						authorsInfo.setAuthorlist(authorlist);
						bookInfo.setAuthors(authorsInfo);
						bookInfo.setPageNum(Integer.parseInt(eElement.getElementsByTagName("pageNum").item(0).getTextContent()));
						bookInfo.setPublisher(eElement.getElementsByTagName("publisher").item(0).getTextContent());
						bookInfo.setPublishdate(eElement.getElementsByTagName("publishdate").item(0).getTextContent());
						bookInfo.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
						bookInfo.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
						list.add(bookInfo);
					}
			}
			iter++;
		}
		return list;
	}

		@Override
		public List<BookInfo> getBooksByAuthor(String author) {
			List<BookInfo> list = new ArrayList<BookInfo>();
			createDOM();
			NodeList nl = doc.getElementsByTagName("book");
			int len = nl.getLength();
			int iter = 0;
			while(iter!=len)
			{
				Node t1 = nl.item(iter);
				if (t1.getNodeType() == Node.ELEMENT_NODE)
				{
					Element eElement = (Element) t1;
					Node authors = eElement.getElementsByTagName("authors").item(0);
					Element aElement = (Element) authors;
					ArrayList<String> authorlist = new ArrayList<String>();
					NodeList authorNames = aElement.getElementsByTagName("author");
					int size = authorNames.getLength();
					int count = 0;
					while(size!=count)
					{
						authorlist.add(authorNames.item(count).getTextContent());
						count++;
					}
					if(authorlist.contains(author))
					{
						AuthorsInfo authorsInfo = new AuthorsInfo();
						BookInfo bookInfo = new BookInfo();
						bookInfo.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
						bookInfo.setResourceID(eElement.getElementsByTagName("resourceID").item(0).getTextContent());
						bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
						bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
						authorsInfo.setAuthorlist(authorlist);
						bookInfo.setAuthors(authorsInfo);
						bookInfo.setPageNum(Integer.parseInt(eElement.getElementsByTagName("pageNum").item(0).getTextContent()));
						bookInfo.setPublisher(eElement.getElementsByTagName("publisher").item(0).getTextContent());
						bookInfo.setPublishdate(eElement.getElementsByTagName("publishdate").item(0).getTextContent());
						bookInfo.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
						bookInfo.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
						list.add(bookInfo);
					}
				}	
				iter++;	
			}
			return list;
		}

		@Override
		public BookInfo getBookInfobyISBN10(String isbn10) {
			createDOM();
			NodeList nl = doc.getElementsByTagName("ISBN10");
			BookInfo bookInfo = new BookInfo();
			int len = nl.getLength();
			int iter = 0;
			boolean flag=false;
			Node parent = null;
			while(iter!=len)
			{
				Node isbn10id = nl.item(iter); 
				if(isbn10id.getTextContent().equals(isbn10))
				{
					parent = isbn10id.getParentNode();
					flag = true;
				}
				iter++;
			}
			if(flag)
			{
				Element eElement = (Element) parent;
				bookInfo.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
				bookInfo.setResourceID(eElement.getElementsByTagName("resourceID").item(0).getTextContent());
				bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
				bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
				ArrayList<String> authorlist = new ArrayList<String>();
				Node authors = eElement.getElementsByTagName("authors").item(0);
				Element aElement = (Element) authors;
				NodeList authorNames = aElement.getElementsByTagName("author");
				int size = authorNames.getLength();
				int count = 0;
				while(size!=count)
				{
					authorlist.add(authorNames.item(count).getTextContent());
					count++;
				}
				AuthorsInfo authorsInfo = new AuthorsInfo();
				authorsInfo.setAuthorlist(authorlist);
				bookInfo.setAuthors(authorsInfo);
				bookInfo.setPageNum(Integer.parseInt(eElement.getElementsByTagName("pageNum").item(0).getTextContent()));
				bookInfo.setPublisher(eElement.getElementsByTagName("publisher").item(0).getTextContent());
				bookInfo.setPublishdate(eElement.getElementsByTagName("publishdate").item(0).getTextContent());
				bookInfo.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
				bookInfo.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));
			}
			return bookInfo;
		}

		@Override
		public BookInfo getBookInfobyISBN13(String isbn13) {
			createDOM();
			NodeList nl = doc.getElementsByTagName("ISBN13");
			BookInfo bookInfo = new BookInfo();
			int len = nl.getLength();
			int iter = 0;
			boolean flag=false;
			Node parent = null;
			while(iter!=len)
			{
				Node isbn13id = nl.item(iter); 
				if(isbn13id.getTextContent().equals(isbn13))
				{
					parent = isbn13id.getParentNode();
					flag = true;
				}
				iter++;
			}
			if(flag)
			{
				Element eElement = (Element) parent;
				bookInfo.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
				bookInfo.setResourceID(eElement.getElementsByTagName("resourceID").item(0).getTextContent());
				bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
				bookInfo.setISBN13(eElement.getElementsByTagName("ISBN13").item(0).getTextContent());
				ArrayList<String> authorlist = new ArrayList<String>();
				Node authors = eElement.getElementsByTagName("authors").item(0);
				Element aElement = (Element) authors;
				NodeList authorNames = aElement.getElementsByTagName("author");
				int size = authorNames.getLength();
				int count = 0;
				while(size!=count)
				{
					authorlist.add(authorNames.item(count).getTextContent());
					count++;
				}
				AuthorsInfo authorsInfo = new AuthorsInfo();
				authorsInfo.setAuthorlist(authorlist);
				bookInfo.setAuthors(authorsInfo);
				bookInfo.setPageNum(Integer.parseInt(eElement.getElementsByTagName("pageNum").item(0).getTextContent()));
				bookInfo.setPublisher(eElement.getElementsByTagName("publisher").item(0).getTextContent());
				bookInfo.setPublishdate(eElement.getElementsByTagName("publishdate").item(0).getTextContent());
				bookInfo.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
				bookInfo.setPrice(Float.parseFloat(eElement.getElementsByTagName("price").item(0).getTextContent()));

			}
			return bookInfo;
		}

		private void createDOM()
		{
			String xml = "/home/mayank/ServiceComputing/Avengers/src/SC13Project/Milestone1/BookStore/bookDB.xml";
			// Make an  instance of the DocumentBuilderFactory
			dbf = DocumentBuilderFactory.newInstance();
			try {
				db =  dbf.newDocumentBuilder();
				dom = db.parse(xml);
				doc = dom.getDocumentElement();
			} catch (ParserConfigurationException pce) {
				System.out.println(pce.getMessage());
			} catch (SAXException se) {
				System.out.println(se.getMessage());
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());
			}

		}

	}
