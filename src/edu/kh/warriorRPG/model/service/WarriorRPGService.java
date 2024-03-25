package edu.kh.warriorRPG.model.service;

import java.util.List;

import edu.kh.warriorRPG.model.dao.Potion;
import edu.kh.warriorRPG.model.dao.Warrior;

public interface WarriorRPGService {

	/** 전사 생성 
	 * @param name
	 */
	String createWarrior(String name) throws Exception;

	/**
	 * 캐릭터 리스트
	 */
	String warriorList();

	/** 캐릭터 상세정보
	 * @param input
	 */
	Warrior detailWarrior(int index);

	/** 캐릭터 삭제
	 * @param index
	 * @return
	 * @throws Exception
	 */
	String deleteWarrior(int index) throws Exception;

	/** 선택한 전사 객체
	 * @return
	 */
	Warrior selectWarrior(int index);

	/** 게임 저장
	 * @param warrior
	 */
	Warrior save(Warrior warrior) throws Exception;

	
	
	
}