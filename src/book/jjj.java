package book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jjj {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		Book book = new Book("ksiazeczka", new Author("anna", "wanna"));
		Book book1 = new Book("takitytul", new Author("piotr", "wal"));
		List<Book> list = new ArrayList<Book>(Arrays.asList(book1,book));
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonInString);
	}
}
