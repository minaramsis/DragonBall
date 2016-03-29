package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;

public abstract class Attack {
	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}
	
	abstract int getAppliedDamage(BattleOpponent attacker);
	
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		int damage;
		int initialDamage;
		damage = getAppliedDamage(attacker);
		initialDamage = damage;
		if(attacker instanceof Saiyan){
			damage = (int) (damage * 1.25);
		}
		if(defenderBlocking){
			damage = damage - ((Fighter) defender).getStamina()*100;
			if(damage < 0){
				int staminaRemaining = (int)Math.ceil(-(damage)/100);
				damage = 0;
				((Fighter)defender).setStamina(staminaRemaining);
			}else{
				((Fighter)defender).setStamina(((Fighter)defender).getStamina()-((initialDamage - damage)/100));
			}
		}
		((Fighter)defender).setHealthPoints(((Fighter)defender).getHealthPoints()-damage);
	}
	
	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}
}
