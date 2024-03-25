package edu.kh.warriorRPG.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import edu.kh.warriorRPG.model.dao.Potion;
import edu.kh.warriorRPG.model.dao.Warrior;
import edu.kh.warriorRPG.model.dao.WarriorDAO;
import edu.kh.warriorRPG.model.dao.WarriorDAOImpl;

public class WarriorRPGServiceImpl implements WarriorRPGService{

	private WarriorDAO wdao = null;
	
	public WarriorRPGServiceImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		wdao = new WarriorDAOImpl();
	}

	/**
	 * 전사 생성
	 */
	@Override
	public String createWarrior(String name) throws Exception {
		return wdao.createWarrior(name);
	}

	
	/**
	 * 캐릭터 리스트 불러오기
	 */
	@Override
	public String warriorList() {
		List<Warrior> warriorList = wdao.warriorList();
		
		StringBuilder sb = new StringBuilder();
		
		if(warriorList == null) {
			return null;
		}
		
		for(int i = 0; i < warriorList.size(); i++) {
			sb.append(String.format("%s. 이름 : %s / 레벨 : %d / 경험치 : %d\n", 
					(i+1), warriorList.get(i).getName(), warriorList.get(i).getLevel(), warriorList.get(i).getExp() ));
		}
		
		sb.append("\n0. 이전 메뉴로 돌아가기");
		
		return sb.toString();
	}

	/**
	 * 캐릭터 상세정보
	 */
	@Override
	public Warrior detailWarrior(int index) {
		Warrior warrior = wdao.detailWarrior(index-1);
		
		if(warrior == null) {
			return null;
		}
		
		return warrior;		
	}

	/**
	 * 캐릭터 삭제
	 */
	@Override
	public String deleteWarrior(int index) throws Exception {
		Warrior warrior = wdao.deleteWarrior(index-1);
		
		if(warrior == null) return null;
		
		return warrior.getName();
	}

	/**
	 * 캐릭터 선택
	 */
	@Override
	public Warrior selectWarrior(int index) {
		return wdao.selectWarrior(index);
	}

	/**
	 * 게임 저장
	 */
	@Override
	public Warrior save(Warrior warrior) throws Exception {
		Warrior savedWarrior = wdao.save(warrior);
		
		if(savedWarrior == null) return null; 
		
		
		return savedWarrior;
	}

	
	
	
	
}