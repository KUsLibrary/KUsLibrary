package tmp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Console {
	private Admin admin;
	private Scanner scanner;
	private String input;
	private AdminFunction af;
	private UserFunction uf;
	private LinkedList<User> userInfo;
	
	Console() {
		admin = new Admin();
		scanner = new Scanner(System.in);
		af = new AdminFunction(admin);
		//uf = new UserFunction(); uf = new UserFunction(user);
		userInfo = new LinkedList<>();
		readUserFile("./UserFile.txt");
	}
	
	/*
	 * 회원 정보 저장 방식
	 * 
	 * __USER__
	 * userName
	 * userID
	 * userPassword
	 * userNumber
	 * _BOOK_
	 * n (책 수)
	 * book1's_num book1's_day ( (책 제목)(공백)(대출일->20200101 같이 8자리) )
	 * book2's_num book2's_day
	 * ...
	 * ...
	 * bookn's_num bookn's_day
	 * __USER__
	 * userName
	 * ...
	 * ...
	 * 위와 같이 반복
	 * 
	 */
	
	@SuppressWarnings("resource")
	public void readUserFile(String file) {		// 프로그램 끝낼 때 파일 새로 저장하기!
		try {
			File userFile = new File(file);
			Scanner fileScan = new Scanner(userFile);
			while(fileScan.hasNextLine()) {
				if (fileScan.nextLine().equals("__USER__")) {
					String tempName = fileScan.nextLine();
					String tempID = fileScan.nextLine();
					String tempPW = fileScan.nextLine();
					String tempNum = fileScan.nextLine();
					userInfo.add(new User(tempName, tempID, tempPW, tempNum));		// LinkedList에 사용자 정보 한명씩 추가
					if (fileScan.nextLine().equals("_BOOK_")) {
						int count = 0;
						while(fileScan.hasNextLine()) {
							int bookCount = Integer.parseInt(fileScan.nextLine());
							String[] bookTemp = fileScan.nextLine().split(" ");
							if (bookTemp.length >= 2)
								userInfo.get(userInfo.size() - 1).addBook(bookTemp[0], bookTemp[1]);	// 사용자가 대출 중인 책 번호와 대출일 넘기기
							count++;
							if (count == bookCount) break;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		Boolean run = true;
		
		while(run) {
			System.out.println("1) 로그인 2) 회원가입 3) 종료");
			System.out.print("메뉴를 입력하세요 : ");
			input = scanner.next();
			scanner.nextLine();
			switch(input) {
				case "1": signIn();
					break;
				case "2": signUp();
					break;
				case "3": System.exit(0);
					break;
			}
		}
		
	}
	
	public void signIn() {
		
		while (true) {
			System.out.print("1) 관리자 로그인 2) 회원 로그인 (뒤로 가기 'q') : ");
			input = scanner.nextLine();
			if(input.equals("1") || input.equals("2") || input.equals("q")) break;
			else {
				System.out.println("올바른 번호를 입력하세요.");
			}
		}
		if(input.equals("1")) {
			
			//관리자 패스워드 입력받고 틀리면 예외처리 맞으면 관리자기능 run
			af.run();
		}
		else if(input.equals("2")) {
			while (true) {
				System.out.print("아이디를 입력하세요 : ");
				String inputID = scanner.nextLine();
				System.out.print("비밀번호를 입력하세요 : ");
				String inputPW = scanner.nextLine();
				User helloUser = loginUser(inputID, inputPW);
				if (helloUser == null) {
					System.out.println("로그인 실패");
					break;
				}
				else {
					uf = new UserFunction(helloUser);  // 해당 유저의 실행 창 수행
					break;
				}
			}
			//사용자 아이디, 비밀번호 입력받고 틀리면 예외처리 맞으면 유저기능 run
			
			
			//uf.run(); //유저기능 run에 아이디 비밀번호 분석해서 admin.uList에서 알맞은 인스턴스 가져와서 넣어줘야함 User인스턴스
			
		}
		else {
			//뒤로가기
		}
	}
	
	public User loginUser(String id, String pw) {
		
		/*
		회원 리스트 중간 점검
		for (User user : userInfo) {
			System.out.println(user.getID() + " " + user.getPW() + " " + user.getName());
		}
		*/
		
		for (User user : userInfo) {
			if (user.getID().equals(id)) {
				if (user.getPW().equals(pw)) {
					System.out.println(user.getName() + "님 로그인 성공");
					return user;
				}
			}
		}
		return null;
	}
	
	public void signUp() {
		boolean goMain = false;
		String name, id, pw, number;
		System.out.println("====== 회원 가입 ======");
		System.out.println("('q' 입력 시 초기 화면으로 이동합니다)");
		while (true) {
			System.out.print("이름 : ");
			name = scanner.nextLine();
			if (name.equals("q")) {
				goMain = true;
				break;
			}
			String name_ws = name.replaceAll(" ", "");
			if (name.length() < 3 || name.length() > 10)
				System.out.println("3~10자 사이로 입력");
			else if (name.length() != name_ws.length())
				System.out.println("공백 입력 불가");
			else if (checkForm(name, true) == false)
				System.out.println("한글, 영어만 입력");
			else break;
		}
		if (goMain == true) return;
		while (true) {
			System.out.print("아이디 : ");
			id = scanner.nextLine();
			if (id.equals("q")) {
				goMain = true;
				break;
			}
			String id_ws = id.replaceAll(" ", "");
			if (id.length() < 3 || id.length() > 10)
				System.out.println("3~10자 사이로 입력");
			else if (id.length() != id_ws.length())
				System.out.println("공백 입력 불가");
			else if (checkForm(id, false) == false)
				System.out.println("숫자, 영어만 입력");
			else break;
		}
		if (goMain == true) return;
		while (true) {
			System.out.print("비밀번호 : ");
			pw = scanner.nextLine();
			if (pw.equals("q")) {
				goMain = true;
				break;
			}
			String pw_ws = pw.replaceAll(" ", "");
			if (pw.length() < 3 || pw.length() > 10)
				System.out.println("3~10자 사이로 입력");
			else if (pw.length() != pw_ws.length())
				System.out.println("공백 입력 불가");
			else if (checkForm(pw, false) == false)
				System.out.println("숫자, 영어만 입력");
			else break;
		}
		if (goMain == true) return;
		while (true) {
			System.out.print("전화번호 (공백 없이 번호만) : ");
			number = scanner.nextLine();
			if (number.equals("q")) {
				goMain = true;
				break;
			}
			String number_ws = number.replaceAll(" ", "");
			if (number.length() < 9 || number.length() > 11)
				System.out.println("9~11자 사이로 입력");
			else if (number.length() != number_ws.length())
				System.out.println("공백 입력 불가");
			boolean numCheck = false; // 전화번호에 숫자만 있는지.
			for (int i=0; i<number.length(); i++) {
				int index = number.charAt(i);
				if (index >= 48 && index <= 57)
					numCheck = true;
			}
			if (numCheck == false)
				System.out.println("숫자만 입력");
			else break;
		}
		if (goMain == true) return;
		userInfo.add(new User(name, id, pw, number));
		System.out.println("회원 가입이 완료되었습니다.\n");
	}

	public boolean checkForm(String str, boolean name) {  // name == true면 이름 체크, name == false면 아이디와 비밀번호 체크
		boolean numCheck = false;
		boolean korCheck = false;
		boolean strangeCheck = false;
		
		for (int i=0; i<str.length(); i++) {
			int index = str.charAt(i);
			
			if (index >= 48 && index <= 57)
				numCheck = true;
			else if (index >= 65 && index <= 122); // 영어 입력
			else if (str.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
				korCheck = true;
			else
				strangeCheck = true;
		}
		
		if (name == true) {
			if (numCheck == true || strangeCheck == true)
				return false;
			else return true;
		}
		else {
			if (korCheck == true || strangeCheck == true)
				return false;
			else return true;
		}		
	}
}
