package edu.kh.warriorRPG.model.dao;

import java.io.Serializable;

public class Weapon implements Serializable {
	
	private String name; // 이름 
	private String kind; // 타입 - 검, 도끼 등
	private int attack; // 공격력
	private int avaliableLevel; // 사용 가능한 레벨
	private int price; // 가격
	
	public Weapon() {}

	public Weapon(String name, String kind, int attack, int avaliableLevel, int price) {
		super();
		this.name = name + " " + kind;
		this.kind = kind;
		this.attack = attack;
		this.avaliableLevel = avaliableLevel;
		this.price = price;
	}

	
	
	

	@Override
	public String toString() {
		return name + "[공격력+" + attack + "]" + " / " + avaliableLevel + "레벨 이상 사용 가능" + " / " + price + "원";
	}

	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getAvaliableLevel() {
		return avaliableLevel;
	}
	public void setAvaliableLevel(int avaliableLevel) {
		this.avaliableLevel = avaliableLevel;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}