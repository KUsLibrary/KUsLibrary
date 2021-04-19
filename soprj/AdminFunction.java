package tmp;
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
			System.out.print("1) 회원관리 2) 도서관리 3) 연체현황 4) 로그아웃 : ");
			input = scanner.next();
			switch(input) {
			case "1":
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

	public void userManage() { //회원관리
		
	}
	
	public void bookManage() { //도서관리
		Boolean run = true;
		String input;
		while(run) {
			System.out.print("1) 도서등록 2) 도서삭제 : ");
			input = scanner.next();
			if(input.equals("1")) {
				System.out.print("등록할 도서의 이름을 입력하세요 : ");
				String name = scanner.next();
				System.out.print("등록할 도서의 저자를 입력하세요 : ");
				String author = scanner.next();
				System.out.print("등록할 도서의 출판사를 입력하세요 : ");
				String publisher = scanner.next();
				Book book = new Book(name, author, publisher);
				admin.addBook(book);
				System.out.println("도서가 등록되었습니다.");
				System.out.println("도서 이름 : " + name);
				System.out.println("저자명 : " + author);
				System.out.println("출판사 : " + publisher);
				System.out.println("도서코드 : " + book.getBookCode());
			}
			else if(input.equals("2")) {
				try {
					System.out.print("삭제할 도서의 코드를 입력하세요 : ");
					long code = scanner.nextLong();
					int count = 0;
					for(int i=0; i < admin.getBookList().size(); i++ ) {
						if(code == admin.getBookList().get(i).getBookCode()) {
							count++;
							System.out.println("=============도서삭제=============");
							System.out.println("도서 이름 : " + admin.getBookList().get(i).getName());
							System.out.println("저자명 : " + admin.getBookList().get(i).getAuthor());
							System.out.println("출판사 : " + admin.getBookList().get(i).getPublisher());
							System.out.println("도서코드 : " + admin.getBookList().get(i).getBookCode());
							System.out.print("삭제 하시겠습니까? y/n");
							String ip = scanner.next();
							if(ip.equals("y")) {
								admin.removeBook(i);
								System.out.println("도서를 삭제했습니다.");					
							}					
							else if(ip.equals("n")) {				
								System.out.println("도서를 삭제하지 않습니다.");		
							}					
							else {							
								System.out.println("똑바로 입력하세요.");					
							}				
						}			
					}			
					if(count == 0) {				
						System.out.println("도서코드가 존재하지 않습니다.");		
					}
				} catch(InputMismatchException e) {
					// TODO Auto-generated catch block
					System.out.println("유효하지 않은 값입니다. 다시 값을 입력해주세요");
				}
			}
			else if(input.equals("q")){
				run = false;
			}
			else {
				System.out.println("똑바로 입력하세요.");
			}
		}
	}
	
	public void overdue() { //연체목록
		for(int i=0; i<admin.getUserList().size(); i++) {
			for(String key : admin.getUserList().get(i).getBookMap().keySet()) {
				if(admin.getUserList().get(i).getBookMap().get(key) == "s") { //User의 Hash맵에 저장된 날로부터 일주일이 지났다면 출력
					
				}
			}
		}
	}
}
