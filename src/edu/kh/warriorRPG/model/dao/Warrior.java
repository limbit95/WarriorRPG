package edu.kh.warriorRPG.model.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Warrior implements Serializable {

	private String name; // 이름
	private int level; // 레벨
	private int exp; // 경험치
	private int max_hp; // 최대 체력
	private int hp; // 체력
	private int attack; // 공격력
	private Weapon attackEquip; // 장착 무기
	private int stat; // 스탯포인트
	private Map<Potion, Integer> potion; // 물약 주머니
	private int gold; // 돈
	private boolean isAlive; // 사망여부
	private List<Weapon> weaponList; // 보유 무기 리스트
	
	public Warrior() {}
	
	public Warrior(String name) {
		super();
		this.name = name;
		this.level = 1;
		this.exp = 0;
		this.max_hp = 100;
		this.hp = max_hp;
		this.attack = 5;
		this.attackEquip = new Weapon("맨", "주먹", 3, 1, 0);
		this.stat = 0;
		this.potion = new TreeMap<Potion, Integer>();
		{
			potion.put(new Potion("하급 물약", 5, 20), 3);
		}
		this.gold = 10;
		this.isAlive = true;
		this.weaponList = new ArrayList<Weapon>();
	}

	// 골드 차감 메서드
	public void minusGold(int price, Object object) {
		if(gold < price) {
			System.out.println("\n### 골드가 부족합니다 ###");
			return;
		}
		
		
		
		if(object.getClass() == Weapon.class) {
			Weapon weapon = (Weapon)object;
			
			if(this.weaponList.contains(weapon)) {
				System.out.println("\n이미 구매한 무기입니다.");
				return;
			}
			
			gold -= price;
			
			System.out.println("\n*** " + weapon.getName() + "을 구매하셨습니다 ***");
			weaponList.add(weapon);
			System.out.println("\n*** 무기 인벤토리에 [" + weapon.getName() + "] 이(가) 추가 되었습니다 ***");
			
			return;
		}
		
		if(object.getClass() == Potion.class) {
			Potion potion = (Potion)object;
			
			gold -= price;
			
			System.out.println("\n*** " + potion.getName() + "을 구매하셨습니다 ***");
			this.potion.put(potion, this.potion.get(potion) + 1);
			
			return;
		}
	}
	
	
	// 공격 메서드
	public void attack(Slime slime) {
		System.out.println("### " + name + " 공격 ###");
		System.out.println(slime.getName() + "에게 " + attackEquip.getName() + " " + attackEquip.getKind()
				+ "을 휘둘러 [" + (attack + attackEquip.getAttack()) + "]만큼의 데미지를 주었다!");
	}
	
	
	// 받은 피해 처리 메서드
	public void damaged(Slime slime) {
		if(hp <= slime.getAttack()) {
			hp = 0;
			exp -= 10;
			if(exp < 0) {
				exp = 0;
			}
			isAlive = false;
			System.out.println("[" + slime.getAttack() + "]만큼의 데미지를 입었습니다.");
			System.out.println(name + "의 체력 : [" + hp + "]\n");
			return;
		}
		
		hp -= slime.getAttack();
		System.out.println("[" + slime.getAttack() + "]만큼의 데미지를 입었습니다.");
		System.out.println(name + "의 체력 : [" + hp + "]\n");
		return;
	}
	

	
	// 해당 캐릭터의 상태 검사 후
	// - 경험치가 100 이상이면 캐릭터.경험치 -= 100, 캐릭터.레벨 += 1, 캐릭터.스탯 +1
	public void statusUp() {
		if(exp >= 100) {
			exp -= 100;
			level += 1;
			stat += 1;
			System.out.println("축하합니다! 1레벨업 되셨습니다!!!\n");
		} 
	}
	
	
	
	// 영구 체력 증가 
	public void maxHpUp() {
		if(stat > 0) {
			System.out.println("체력이 [10] 증가하였습니다.");
			max_hp += 10;
			stat--;
		} else {
			System.out.println("스탯 포인트가 부족합니다.");
		}
	}
	// 영구 공격력 증가
	public void attackUp() {
		if(stat > 0) {
			System.out.println("공격력이 [3] 증가하였습니다.");
			attack += 3;
			stat--;
		} else {
			System.out.println("스탯 포인트가 부족합니다.");
		}
	}
	
	// 사용한 포션 치유량만큼 캐릭터 hp 회복
	public void healHp(Potion usePotion) {
		
		if(potion.get(usePotion) == 0) {
			System.out.println("\n### 해당 포션이 부족합니다 ###");
			return;
		}
		
		if(hp == max_hp) {
			System.out.println("\n체력이 최대치입니다.");
			return;
		}
		
		if(hp+usePotion.getHeal() >= max_hp) {
			hp = max_hp;
			System.out.println("\n*** 최대 체력까지 회복되었습니다 ***");
			potion.put(usePotion, potion.get(usePotion)-1);
			return;
		}
		
		hp += usePotion.getHeal();
		System.out.println("\n*** 체력이 [" + usePotion.getHeal() + "] 회복되었습니다 ***");
		potion.put(usePotion, potion.get(usePotion)-1);
	}
	

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("\n*** " +  name + "의 상태창 ***")).append("\n");
		sb.append(String.format("레벨 : " +  level));
		sb.append(String.format(" │ 경험치 : " +  exp  + "/100")).append("\n");
		sb.append(String.format("체력 : " +  hp + "/" + max_hp)).append("\n");
		sb.append(String.format("공격력 : " + attack  + "[" + attackEquip.getName() + "+" + (attackEquip.getAttack()) + "]")).append("\n");
//		sb.append(String.format("장비(무기) : " +  attackEquip.getName())).append("\n");
		sb.append(String.format("스탯포인트 : " +  stat)).append("\n");
		sb.append(String.format("골드 : " +  gold)).append("\n");
//		sb.append(String.format("아이템(물약) : " +  potion)).append("\n");
		sb.append(String.format("생존여부 : " + (isAlive ? "생존" : "사망"))).append("\n");
		
		return sb.toString();
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMax_hp() {
		return max_hp;
	}
	public void setMax_hp(int max_hp) {
		this.max_hp = max_hp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public Weapon getAttackEquip() {
		return attackEquip;
	}
	public void setAttackEquip(Weapon attackEquip) {
		this.attackEquip = attackEquip;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public Map<Potion, Integer> getPotion() {
		return potion;
	}
	public void setPotion(Map<Potion, Integer> potion) {
		this.potion = potion;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public List<Weapon> getWeaponList() {
		return weaponList;
	}
	public void setWeaponList(List<Weapon> weaponList) {
		this.weaponList = weaponList;
	}

}