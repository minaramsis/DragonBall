package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;

public class PhysicalAttack extends Attack {
	public PhysicalAttack() {
		super("Physical Attack", 50);
	}

	public int getAppliedDamage(BattleOpponent attacker) {
		return getDamage() + ((Fighter)attacker).getPhysicalDamage();
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		super.onUse(attacker, defender, defenderBlocking);
		((Fighter)attacker).setKi(((Fighter)attacker).getKi()+1);
	}
}


