package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public abstract class Attack {
	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}
	
	abstract int getAppliedDamage(BattleOpponent attacker);
	
	void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking){
		
	}
	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}
}
