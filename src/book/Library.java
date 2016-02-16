package book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	public void sortById(){
		Collections.sort(books,new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
	}
	
	public void sortByTitle(){
		Collections.sort(books,new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getTitle().compareToIgnoreCase(o2.getTitle());
			}
		});
	}
}
