package book;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private Long id;
	private String title;
	private List<Integer> authors = new ArrayList<Integer>();

	public Book(Long id, String title, List<Integer> authors) {
		super();
		this.id = id;
		this.title = title;
		this.authors = authors;
	}
	
	public Book(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Book(String title) {
		super();
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Integer> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Integer> authors) {
		this.authors = authors;
	}

}
