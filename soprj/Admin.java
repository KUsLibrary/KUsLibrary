package soprj;
import java.util.ArrayList;

public class Admin {
	private final String pw = "admin";
	private ArrayList<User> uList;
	private ArrayList<Book> bList;
	
	Admin() {
		uList = new ArrayList<User>();
		bList = new ArrayList<Book>();
	}
	
	public void addUser(User user) { //���� ȸ�����Խ� ���� �߰�
		System.out.println(user.getName() + "���� ȸ�������� �Ϸ�Ǿ����ϴ�.");
		uList.add(user);
		System.out.println("����Ʈ�� �߰��Ϸ�: " + uList.get(uList.size() - 1).getName()); //����Ʈ�� �� �߰��ƴ��� Ȯ�ο�
	}
	public void addBook(Book book) { //���� ��Ͻ� ���� �߰�
		bList.add(book);
	}
	public void removeUser() { //ȸ�� ����
		
	}
	public void removeBook() { //���� ����
		
	}
	public ArrayList<User> getUserList() {
		return uList;
	}
	
}
