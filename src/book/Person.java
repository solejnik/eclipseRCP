package book;

public class Person {
	private String first;
	private String last;
	private String title;
	private String email;
	
	public Person() {
	}

	public Person(String string, String string2) {
		first = string;
		last = string2;
	}

	public String getFirst() {
		return first;
	}
	
	public String getFirstName() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
