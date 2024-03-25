package edu.kh.warriorRPG.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import edu.kh.warriorRPG.model.dao.Warrior;
import edu.kh.warriorRPG.model.service.WarriorRPGService;
import edu.kh.warriorRPG.model.service.WarriorRPGServiceImpl;

public class WarriorRPGView {

	private BufferedReader br = null;
	private WarriorRPGService service = null;
	private GamePlayView gamePlayView = null;
	
	public WarriorRPGView() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			service = new WarriorRPGServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void RPGGame() {
		int input = 0;
		
		do {
			try {
				input = mainMenu();
				
				switch(input) {
				case 1: gameStart(); break;
				case 2: createWarrior(); break;
				case 3: showWarrior(); break;
				case 4: deleteWarrior(); break;
				case 0: System.out.println("프로그램을 종료합니다.") ;break;
				default: System.out.println("잘못 입력하셨습니다."); break;
				}
				System.out.println("\n================================\n");
			} catch (NumberFormatException e) {
				System.out.println("### 숫자만 입력해 주세요.");
				input = -1; // 첫 반복에 종료되는 걸 방지
			} catch (IOException e) {
				System.out.println("### 입/출력 관련 예외 발생 ###");
				e.printStackTrace(); // 예외추적
			} catch (Exception e) { // 기타 예외 모두 처리
				e.printStackTrace();
			}
		}while(input != 0);
	}
	
	// 프로그램 메인 메뉴 
	public int mainMenu() throws NumberFormatException, IOException{
		System.out.println("========== Main Menu ==========");
		
		System.out.println("1. 게임 시작");
		System.out.println("2. 캐릭터 생성");
		System.out.println("3. 캐릭터 정보");
		System.out.println("4. 캐릭터 삭제");
		System.out.println("0. 프로그램 종료");
		
		System.out.print("메뉴 선택 : ");
		int input = Integer.parseInt(br.readLine());
		System.out.println();
		
		return input;
	}
	
	// 1. 게임 시작
	public void gameStart() throws Exception  {
		System.out.println("========== Game Start!!! ==========\n");
		
		if(service.warriorList() == null) {
			System.out.println("*** 생성된 전사가 없습니다 ***");
			System.out.print("당신의 첫 전사를 생성하시겠습니까?(y/n) : ");
			String input = br.readLine().toLowerCase();
			if(input.equals("y")) {
				System.out.println();
				createWarrior();
				System.out.println();
			} else {
				System.out.println("\n*** 생성 취소 ***");
				return;
			}
		}
		
		System.out.println("*** 모험을 떠날 전사를 선택해주세요 ***");
		System.out.println(service.warriorList());
		
		System.out.print("\n캐릭터 선택(번호 입력) : ");
		int index = Integer.parseInt(br.readLine());
		
		if(index == 0) return;
		
		Warrior warrior = service.selectWarrior(index);
		
		gamePlayView = new GamePlayView(warrior);
		
		gamePlayView.play();
	}
	
	
	// 2. 전사 생성
	public void createWarrior() throws Exception {
		System.out.println("========== 캐릭터 생성 ==========");
		
		System.out.print("이름 입력 : ");
		String name = br.readLine();
		
		String warrior = service.createWarrior(name);
		
		if(warrior == null) {
			System.out.println("동일한 이름의 전사가 이미 생성되어있습니다. 이름을 바꿔주세요.");
		} else {
			System.out.printf("[전사] '%s'이(가) 생성되었습니다.\n", warrior);
		}
	}
	
	// 3. 캐릭터 조회
	public void showWarrior() throws Exception {
		System.out.println("========== 캐릭터 정보 ==========\n");
		
		if(service.warriorList() == null) {
			System.out.println("*** 생성된 전사가 없음 ***");
			return;
		}
		
		System.out.println(service.warriorList());
		
		System.out.print("\n캐릭터 상세보기(번호 입력) : ");
		int index = Integer.parseInt(br.readLine());
		
		if(index == 0) return;
		
		Warrior warrior = service.detailWarrior(index);
		
		if(warrior == null) {
			System.out.println("### 입력한 인덱스 번호가 존재하지 않습니다 ###");
			return;
		}
		System.out.println(warrior);
	}
	
	// 4. 캐릭터 삭제
	public void deleteWarrior() throws Exception {
		System.out.println("========== 캐릭터 정보 ==========\n");
		
		if(service.warriorList() == null) {
			System.out.println("*** 생성된 전사가 없음 ***");
			return;
		}
		
		System.out.println(service.warriorList());
		
		System.out.print("\n삭제할 캐릭터 번호 입력 : ");
		int index = Integer.parseInt(br.readLine());
		
		if(index == 0) return;
		
		String result = service.deleteWarrior(index);
		
		if(result == null) { 
			System.out.println("### 입력한 인덱스 번호가 존재하지 않습니다 ###");
		} else {
			System.out.printf("[%s] 가 삭제되었습니다.\n", result);
		}
	}
	
	
}