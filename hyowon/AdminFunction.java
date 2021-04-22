package middle_Project;

import java.util.InputMismatchException;
import java.util.Scanner;



public class AdminFunction {
	
	private Admin admin;
	private Scanner scanner;
	
	
	AdminFunction(Admin admin) {
		this.admin = admin;
		scanner = new Scanner(System.in);
	}

	public void run() {
		Boolean run = true;
		String input;
		while(run) {
			System.out.print("1) ȸ������ 2) �������� 3) ��ü��Ȳ 4) �α׾ƿ� : ");
			input = scanner.next();
			switch(input) {
			case "1":
				userManage();
				break;
			case "2":
				bookManage();
				break;
			case "3":
				break;
			case "4":
				run = false;
				break;
			default:
				break;
			}
		}
	}

	public void userManage() { // ȸ������
		Boolean run = true;
		String input;

		while (run) {
			System.out.print("1) ȸ�� �˻� 2) ȸ�� ���� :");
			input = scanner.next();

			if (input.equals("1")) {
				System.out.print("�˻��� ȸ���� �̸��� �Է��ϼ��� : ");
				String name = scanner.nextLine();

				int count = 0;
				for (int i = 0; i < admin.getUserList().size(); i++) {
					if (name.trim().length() != 0) {
						if (admin.getUserList().get(i).getName().contains(name)) {
							count++;
							System.out.println("=============�ش� ȸ�� ���=============");
							System.out.println("�̸� : " + admin.getUserList().get(i).getName());
							System.out.println("���̵� : " + admin.getUserList().get(i).getID());
							System.out.println("��ȭ��ȣ : " + admin.getUserList().get(i).getNumber());

						}

					}
				}
				if (count == 0) {
					System.out.println("ȸ�� ������ �����ϴ�.");
				}
				break;
			}

			else if (input.equals("2")) {
				System.out.print("������ ȸ���� �̸��� �Է��ϼ��� : ");
				String name = scanner.nextLine();
				System.out.print("������ ȸ���� ���̵� �Է��ϼ��� : ");
				String ID = scanner.nextLine();
				int count = 0;

				for (int i = 0; i < admin.getUserList().size(); i++) {
					if (admin.getUserList().get(i).getName().contains(name)) {
						if (admin.getUserList().get(i).getID().contains(ID)) {
							count++;
							System.out.println("============ȸ������=============");
							System.out.println("ȸ�� �̸� : " + admin.getUserList().get(i).getName());
							System.out.println("ȸ�� ���̵� :" + admin.getUserList().get(i).getID());
							System.out.println("ȸ�� ��ȭ��ȣ : " + admin.getUserList().get(i).getNumber());
							System.out.print("���� �Ͻðڽ��ϱ�? y/n :");
							String answer = scanner.next();
							if (answer.equals("y")) {
								admin.removeUser(i);
								;
								System.out.println("ȸ���� �����߽��ϴ�.");
							} else if (answer.equals("n")) {
								System.out.println("ȸ���� �������� �ʽ��ϴ�.");
							}
						}
					}

				}
				if (count == 0) {
					System.out.println("������ ȸ���� �������� �ʽ��ϴ�.");
				}
				break;
			}
			//ȿ��, �߸��Է�������, q �ڷΰ��� �߰�
		}

	}

	public void bookManage() { // ��������
		Boolean run = true;
		String input;
		while (run) {
			System.out.print("1) ������� 2) �������� : ");
			input = scanner.next();
			if (input.equals("1")) {
				System.out.print("�ڵ�: ");
				String bookcode = scanner.next();
				System.out.print("����� ������ �̸��� �Է��ϼ��� : ");
				String name = scanner.next();
				System.out.print("����� ������ ���ڸ� �Է��ϼ��� : ");
				String author = scanner.next();
				System.out.print("����� ������ ���ǻ縦 �Է��ϼ��� : ");
				String publisher = scanner.next();
				Book book = new Book(bookcode, name, author, publisher);
				admin.addBook(book);
				System.out.println("������ ��ϵǾ����ϴ�.");
				System.out.println("���� �̸� : " + name);
				System.out.println("���ڸ� : " + author);
				System.out.println("���ǻ� : " + publisher);
				System.out.println("�����ڵ� : " + book.getBookCode());
				break;// ��������
			} else if (input.equals("2")) {
				try {
					System.out.print("������ ������ �ڵ带 �Է��ϼ��� : ");
					String code = scanner.nextLine();
					int count = 0;
					for (int i = 0; i < admin.getBookList().size(); i++) {
						if (code == admin.getBookList().get(i).getBookCode()) {
							count++;
							System.out.println("=============��������=============");
							System.out.println("���� �̸� : " + admin.getBookList().get(i).getName());
							System.out.println("���ڸ� : " + admin.getBookList().get(i).getAuthor());
							System.out.println("���ǻ� : " + admin.getBookList().get(i).getPublisher());
							System.out.println("�����ڵ� : " + admin.getBookList().get(i).getBookCode());
							System.out.print("���� �Ͻðڽ��ϱ�? y/n");
							String ip = scanner.next();
							if (ip.equals("y")) {
								admin.removeBook(i);
								admin.getBookList().get(i).minusStock();// ���� ���� --(����)
								System.out.println("������ �����߽��ϴ�.");
							} else if (ip.equals("n")) {
								System.out.println("������ �������� �ʽ��ϴ�.");
							} else {
								System.out.println("�ȹٷ� �Է��ϼ���.");
							}
						}
					}
					if (count == 0) {
						System.out.println("�����ڵ尡 �������� �ʽ��ϴ�.");
					}
				} catch (InputMismatchException e) {
					// TODO Auto-generated catch block
					System.out.println("��ȿ���� ���� ���Դϴ�. �ٽ� ���� �Է����ּ���");
				}
				break;// ��������
			} else if (input.equals("q")) {
				run = false;
			} else {
				System.out.println("�ȹٷ� �Է��ϼ���.");
			}
		}
	}

	public void overdue() { // ��ü���
		for (int i = 0; i < admin.getUserList().size(); i++) {
			for (String key : admin.getUserList().get(i).getBookMap().keySet()) {
				if (admin.getUserList().get(i).getBookMap().get(key) == "s") { // User�� Hash�ʿ� ����� ���κ��� �������� �����ٸ� ���

				}
			}
		}
	}
}
