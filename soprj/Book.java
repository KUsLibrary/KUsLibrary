package soprj;

import java.util.Date;

public class Book {
	private long bookCode; //도서코드
	private String name; //도서이름
	private String author; //작가명
	private String publisher; //출판사명
	
	
	Book(String name, String author, String publisher) {
		bookCode = (long) new Date().getTime(); //도서코드 받아오기 getTime()은 중복없이 항상증가
		this.name = name;
		this.author = author;
		this.publisher = publisher;
	}
	
	public long getBookCode() {
		return bookCode;
	}
	public String getName() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
}

