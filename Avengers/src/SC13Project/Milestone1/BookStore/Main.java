package SC13Project.Milestone1.BookStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String args[])
	{
		List<String> list = new ArrayList<String>();
        BookStoreImpl bookStoreImpl = new BookStoreImpl();
        list = bookStoreImpl.getAllBooKNames();
        Iterator<String> i = list.iterator();
		while(i.hasNext())
		{
			System.out.println("Title is " + i.next());
		}
	}
}
