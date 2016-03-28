package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;

public class SuperAttack extends Attack {
	public SuperAttack(String name, int damage) {
		super(name, damage);
	}

	@Override
	int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}
}
