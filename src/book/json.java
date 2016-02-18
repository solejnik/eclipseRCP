package book;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
@Component
public class json {
	private ClassLoader classloader;
	private InputStream is;
	private String input;
	public static void main(String[] args) {
		TitleProvider tp = new TitleProvider();
		System.out.println(tp.getTitle());;
	}
	public TitleProvider(){
		classloader = Thread.currentThread().getContextClassLoader();
		is = classloader.getResourceAsStream("title.txt");
		try {
			input = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getTitle()  {
		return input;
	}

}