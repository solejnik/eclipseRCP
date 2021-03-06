package book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {
	public static void main(String[] args) {
		Library library = new Library();
		library.add(new Book("raz"));
		library.add(new Book("dwa"));
		library.add(new Book("trzy"));
		for(Book book:library.searchBook("a")){
			System.out.println(book);
		}
	}
	private List<Book> books = new ArrayList<Book>();
	
	public Library() {
	}
	
	public Library(List<Book> books) {
		super();
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void add(Book book) {
		book.setId(nextId());
		books.add(book);
	}

	public void remove(Book book) {
		books.remove(book);
	}

	public void sortById() {
		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
	}

	public void sortByTitle() {
		Collections.sort(books, new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				return o1.getTitle().compareToIgnoreCase(o2.getTitle());
			}
		});
	}

	public Long nextId() {
		long maxId = 0;
		for (Book book : books) {
			maxId = Math.max(maxId, book.getId());
		}
		return maxId + 1;
	}
	
	public List<Book> searchBook(String titleCriteria){
		List<Book> foundBooks = new ArrayList<Book>();
		for(Book book:books){
			if(book.getTitle().toLowerCase().contains(titleCriteria.toLowerCase())){
				foundBooks.add(book);
			}
		}
		return foundBooks;
	}

}
