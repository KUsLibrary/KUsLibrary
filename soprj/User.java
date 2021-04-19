package tmp;

import java.util.HashMap;

public class User {
	private String name;
	private String id;
	private String pw;
	private String number;
	private HashMap<String, String> bookMap; // hashmap으로 대출 정보 저장할 예정
	
	User(String name, String id, String pw, String number) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.number = number;
		this.bookMap = new HashMap<String, String>();
	}
	
	public void addBook(String bookNum, String bookDay) {
		bookMap.put(bookNum, bookDay);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getID() {
		return id;
	}
	
	public String getPW() {
		return pw;
	}
	
	public String getNumber() {
		return number;
	}
	
	public HashMap<String, String> getBookMap() {
		return bookMap;
	}
}
