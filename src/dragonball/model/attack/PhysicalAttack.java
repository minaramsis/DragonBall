package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class PhysicalAttack extends Attack {
	public PhysicalAttack() {
		super("Physical Attack", 50);
	}

	int getAppliedDamage(BattleOpponent attacker) {
		int j;
		j=getDamage()+50;
		return j;
	}
}
