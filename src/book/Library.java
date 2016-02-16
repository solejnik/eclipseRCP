package book;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Book> books = new ArrayList<Book>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void add(Book book){
		books.add(book);
	}
	
	public void remove(Book book){
		books.remove(book);
	}
}
