package User;
import java.util.ArrayList;

public class Admin {
	private final String pw = "admin";
	private ArrayList<User> uList;
	private ArrayList<Book> bList;
	
	Admin() {
		uList = new ArrayList<User>();
		bList = new ArrayList<Book>();
	}
	
	public void addUser(User user) { //유저 회원가입시 유저 추가
		System.out.println(user.getName() + "님의 회원가입이 완료되었습니다.");
		uList.add(user);
		System.out.println("리스트에 추가완료: " + uList.get(uList.size() - 1).getName()); //리스트에 잘 추가됐는지 확인용
	}
	
	public void setUser(User user) {
		int index = 0;
		for (User tempUser : uList) {
			if (tempUser.getID().equals(user.getID())) {
				uList.set(index, user);		
				// 위와 같이 user 갱신했을 때, 정상적으로 동작하는지 점검 필요.
				// 만약 정상적으로 동작하지 않는다면 get 함수 사용해야 할 것 같음
				break;
			}
			index++;
		}
	}
	
	public void addBook(Book book) { //도서 등록시 도서 추가
		bList.add(book);
		book.plusStock();//도서 객체의 재고++
	}
	
	public void removeUser(int num) { //회원 삭제
		uList.remove(num);
	}
	public void removeBook(int num) { //도서 삭제
		bList.remove(num);
		
	}
	public ArrayList<User> getUserList() { //userList접근용
		return uList;
	}
	public ArrayList<Book> getBookList() { //bookList접근용
		return bList;
	}
}
