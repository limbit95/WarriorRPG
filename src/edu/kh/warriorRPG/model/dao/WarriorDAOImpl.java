package edu.kh.warriorRPG.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class WarriorDAOImpl implements WarriorDAO {

	private final String SAVE_FILE = "/io_test/Warrior.dat";
	
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	private List<Warrior> warriorList = null;
	
	public WarriorDAOImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(SAVE_FILE);
		
		if(file.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));
				warriorList = (ArrayList<Warrior>)ois.readObject();
			} finally {
				if(ois != null) ois.close();
			}
		} else {
			File directory = new File("/io_test");
			
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			warriorList = new ArrayList<Warrior>();
			
			try {
				oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
				oos.writeObject(warriorList);
			} finally {
				if(oos != null) oos.close();
			}
			
			System.out.println("*** Warrior.dat 파일 생성 완료 ***");
		}
	}

	@Override
	public void saveFile() throws Exception {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
			oos.writeObject(warriorList); 
		} finally {
			if(oos != null) oos.close();
		}
	}
	
	
	
	/**
	 * 전사 생성
	 */
	@Override
	public String createWarrior(String name) throws Exception{
		for(Warrior w : warriorList) {
			if(w.getName().equals(name)) {
				return null;
			}
		}
		warriorList.add(new Warrior(name));
		saveFile();
		return warriorList.get(warriorList.size()-1).getName();
	}

	/**
	 * 캐릭터 리스트 불러오기
	 */
	@Override
	public List<Warrior> warriorList() {
		if(warriorList.isEmpty()){
			return null;
		} 
		return warriorList;
	}

	/**
	 * 캐릭터 상세보기
	 */
	@Override
	public Warrior detailWarrior(int index) {
		if((index) < 0 || (index) >= warriorList.size()) {
			return null;
		}
		return warriorList.get(index);
	}

	/**
	 * 캐릭터 삭제
	 */
	@Override
	public Warrior deleteWarrior(int index) throws Exception{
		if((index) < 0 || (index) >= warriorList.size()) {
			return null;
		}
		
		Warrior warrior = warriorList.remove(index);
		
		saveFile();
		
		return warrior;
	}

	/**
	 * 캐릭터 선택
	 */
	@Override
	public Warrior selectWarrior(int index) {
		return warriorList.get(index-1);
	}

	@Override
	public Warrior save(Warrior warrior) throws Exception {
		Warrior savedWarrior = null;
		
		for(int i = 0; i < warriorList.size(); i++) {
			if(warriorList.get(i).getName().equals(warrior.getName())) {
				warriorList.set(i, warrior);
				saveFile();
				savedWarrior = warrior;
			}
		}
		return savedWarrior;
	}

	
	
	
	
	
	
	
	
}