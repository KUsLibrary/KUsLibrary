package User;

import java.util.Date;

public class Book {
	private String bookCode;
	private String name;
	private String author;
	private String publisher;
	
	private Integer stock =0 ;//이성종 (책의 재고 확인 변수)
	
	Book(String bookCode, String name, String author, String publisher) {
		this.bookCode = bookCode;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
	}
	
	public String getBookCode() {
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
	
	
	
	
	public void minusStock() {//이성종
		if (this.stock > 0) {
			this.stock--;// 책의 재고 마이너스함
		}else {
			System.out.println("더이상 삭제 불가능(Book객체 자체)");
		}
	}
	
	public void plusStock() {//이성종
		this.stock ++;//책의 재고 플러스함
	}
	
	public Integer getStock() {//이성종
		return this.stock;//책의 재고를 반환
	}
}

