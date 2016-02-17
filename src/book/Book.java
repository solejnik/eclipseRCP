package book;

public class Book {
	private Long id;
	private String title;
	private Author author;

	public Book(String title) {
		super();
		this.title = title;
	}

	public Book(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Book(Long id, String title,Author author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	public Book(String title,Author author) {
		this.title = title;
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthors(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + "]";
	}

}
