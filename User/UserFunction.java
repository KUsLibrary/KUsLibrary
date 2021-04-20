package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;

public class UserFunction {
	private Scanner scanner;
	private String name; //이름
	private String id; //아이디
	private String number; //전화번호

	private ArrayList<Book> blist;
	private User user;
	private HashMap<String, String> bookMap;
	
	public UserFunction(User user) {
	      // TODO Auto-generated constructor stub
	      this.name = user.getName();
	      this.id = user.getID();
	      this.number = user.getNumber();
	      
	   }

	
	public UserFunction() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
		
		
	}
	
	
	public User run(User user, ArrayList<Book> Blist) {
		Boolean run = true;
		
		this.user = user;
		
		this.user.setName(user.getName());
	    this.user.setID(user.getID()); 
	    this.user.setNumber(user.getNumber()); 
	    this.user.setPW(user.getPW());
	    
		blist=Blist;
		
		this.user.setBookMap(user.getBookMap()); //User객체 복사
		
		while(run) {
			System.out.println("====== 회원 메뉴 ======");
			System.out.println("1) 도서검색 및 대출 2) 도서반납 3) 마이페이지 4) 로그아웃");
			System.out.print("메뉴를 입력하세요 : ");
			String input2 = scanner.next();
			scanner.nextLine();
			switch(input2) {
				case "1": 	searchBook();
					break;
				case "2": 	returnBook();
					break;
				case "3": 	mypage();
					break;
				case "4":	
					System.out.println("회원메뉴를 종료합니다. 시작메뉴로 이동합니다.");
					System.out.println();
					break;
			}
			if(input2.equals("4"))
				break;
		}
		return this.user;//중요

	}
	
	
	
	public void searchBook() {
		A : while(true) {		// 대출 메뉴에서  break문 추가 필요 (가은)
		System.out.println("====== 도서검색 및 대출 ======");
		System.out.println("1)도서검색  2)도서대출  (뒤로가기 'q')");
	    System.out.print("메뉴를 입력하세요 : ");
	    String input = scanner.nextLine();

	    if(input.equals("q")) {
	    	break;
	    }
	    switch(input) {
	    case "1":
	    	B : while(true) {
	    	System.out.println("====== 도서검색 ======");
	    	System.out.println("뒤로 가기 :'q'/ 다시 입력 : 'r'");
	    	System.out.print("도서 이름 : ");
			String bname=scanner.nextLine();
			if(bname.equals("q") || bname.equals("Q")) {
				continue A;
			}else if(bname.equals("r") || bname.equals("R")) {
				continue B;
			}
			System.out.print("저자명 : ");
			String bauthor=scanner.nextLine();
			if(bauthor.equals("q") || bauthor.equals("Q")) {
				continue A;
			}else if(bauthor.equals("r") || bauthor.equals("R")) {
				continue B;
			}
			System.out.print("출판사 : ");
			String bpublisher=scanner.nextLine();
			if(bpublisher.equals("q") || bpublisher.equals("Q")) {
				continue A;
			}else if(bpublisher.equals("r") || bpublisher.equals("R")) {
				continue B;
			}
			int count1=0, count2=0, count3=0, count4=0;
			
			for(int i=0;i<blist.size();i++) {
				//책 정보의 공백 제거
				String cname= blist.get(i).getName().replaceAll(" ", "");
				String cauthor= blist.get(i).getAuthor().replaceAll(" ", "");
				String cpublisher= blist.get(i).getPublisher().replaceAll(" ", "");
				
				if(bname.trim().length() != 0) {
					if(blist.get(i).getName().contains(bname) || cname.contains(bname)) {
						count1++;
						System.out.println("--------------");
						System.out.println("도서 이름 : " + blist.get(i).getName());
						System.out.println("저자명 : " + blist.get(i).getAuthor());
						System.out.println("출판사 : " + blist.get(i).getPublisher());
						System.out.println("도서코드 : "+blist.get(i).getBookCode());				
					}
				}
				else if(bauthor.trim().length() != 0) {
					if(blist.get(i).getAuthor().contains(bauthor) || cauthor.contains(bauthor)){
						count2++;
						System.out.println("--------------");
						System.out.println("도서 이름 : " + blist.get(i).getName());
						System.out.println("저자명 : " + blist.get(i).getAuthor());
						System.out.println("출판사 : " + blist.get(i).getPublisher());
						System.out.println("도서코드 : "+blist.get(i).getBookCode());
					}
					
				}
				else if(bpublisher.trim().length() != 0) {
					if(blist.get(i).getPublisher().contains(bpublisher) || cpublisher.contains(bpublisher)){
						count3++;
						System.out.println("--------------");
						System.out.println("도서 이름 : " + blist.get(i).getName());
						System.out.println("저자명 : " + blist.get(i).getAuthor());
						System.out.println("출판사 : " + blist.get(i).getPublisher());
						System.out.println("도서코드 : "+blist.get(i).getBookCode());
					}
				}else {
					count4++;
					System.out.println("--------------");
					System.out.println("도서 이름 : " + blist.get(i).getName());
					System.out.println("저자명 : " + blist.get(i).getAuthor());
					System.out.println("출판사 : " + blist.get(i).getPublisher());
					System.out.println("도서코드 : "+blist.get(i).getBookCode());
				}
				
			}
			
			if(count1==0 && count2==0 && count3==0 && count4==0) {
				System.out.println("책 정보가 없습니다. ");
			}
	    	
			break;
	    	}
	    break;
	    case "2":
	    	System.out.println("====== 도서 대출 ======");
	    	System.out.print("도서코드를 입력해주세요: ");
	        
	         String code  = scanner.nextLine();
	         Integer count = 0;
	         blist.get(0).plusStock();
	         //blist.get(1).plusStock();
	         for(int i=0; i<blist.size(); i++) {
	            String BookCode = blist.get(i).getBookCode();//책 객체에 저장된 도서코드
	            if(code.equals(BookCode)) {
	               count++;// 책 개수 카운트
	               if (blist.get(i).getStock() > 0) {
	            	   //User객체에 책 저장하고 Blist에서 책 하나 뺌
	            	   user.addBook(blist.get(i).getBookCode(), "0420");
	            	   blist.get(i).minusStock();//재고 하나 뺴기
						System.out.println("도서코드: " + BookCode + " | 제목: " + blist.get(i).getName() + " 이(가) 대출되었습니다.");
						System.out.println();
						break;
						//입력한 코드와 일치하며 재고가 있는 책을 대출함.
					} else {
						System.out.print("책 재고가 없습니다. 예약하시겠습니까? => ");
						//입력한 코드와 일치하지만 재고가 없는 책은 예약 여부를 물음.
						while (true) {
							String ans = scanner.nextLine();
							
							if (ans.equals("Y")) {
								// 예약하는 체크요소 필요
								System.out.println("예약 성공");
								break;
							} else if (ans.equals("N")) {
								System.out.println("예약 취소");
								break;
							} else {
								System.out.print("(Y/N)으로 재입력 바랍니다 => ");
							}
						}
					}
				}
			}
			if (count == 0) {
				System.out.println("일치하는 책이 없습니다. 메뉴로 나갑니다.");
				// 입력한 도서코드와 일치하는 책이 없는 경우
			}

			break; // 스위치문 탈출
		default:
			System.out.println("1, 2, 'q' 중  입력하세요 ");
			break;
		}
	    break;
	}

}
	
	
	public void returnBook() {
		try {
			System.out.print("반납할 도서의 코드를 입력하세요 : ");
			String bcode = scanner.nextLine();
			
			
			
		} catch(InputMismatchException e) {
			// TODO Auto-generated catch block
			System.out.println("유효하지 않은 값입니다. 다시 값을 입력해주세요");
		}
	}
	
	public void mypage() {
		 System.out.println("========회원정보를 출력합니다========");
	      System.out.println("이름: "+this.name);
	      System.out.println("아이디 "+this.id);
	      System.out.println("전화번호: "+this.number);
	      System.out.println("==========대출현황===========");
	      for (Entry<String, String> entry : bookMap.entrySet()) {
	    	    System.out.println("[도서코드]:" + entry.getKey() + " [대출일자]:" + entry.getValue());
	    	}
	      System.out.println();
	   }

	
	
}
