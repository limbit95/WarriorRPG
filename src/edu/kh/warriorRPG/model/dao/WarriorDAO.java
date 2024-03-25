package edu.kh.warriorRPG.model.dao;

import java.util.List;

public interface WarriorDAO {

	/** 전사 생성
	 * @param name
	 * @return
	 */
	String createWarrior(String name) throws Exception;

	/** 생성된 캐릭터 리스트
	 * @return
	 */
	List<Warrior> warriorList();

	void saveFile() throws Exception;

	/** 캐릭터 상세정보
	 * @param index
	 * @return
	 */
	Warrior detailWarrior(int index);

	/** 캐릭터 삭제
	 * @param index
	 * @return
	 * @throws Exception
	 */
	Warrior deleteWarrior(int index) throws Exception;

	/** 선택한 전사 객체
	 * @param index
	 * @return
	 */
	Warrior selectWarrior(int index);

	/** 게임 저장
	 * @param warrior
	 */
	Warrior save(Warrior warrior) throws Exception;


	
	
}