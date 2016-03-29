package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;

public class SuperAttack extends Attack {
	public SuperAttack(String name, int damage) {
		super(name, damage);
	}

	@Override
	int getAppliedDamage(BattleOpponent attacker) {
		System.out.println(getDamage() + ((Fighter)attacker).getBlastDamage());
		return getDamage() + ((Fighter)attacker).getBlastDamage();
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		if(((Fighter)attacker).getKi() >= 1){
			super.onUse(attacker, defender, defenderBlocking);
			((Fighter)attacker).setKi(((Fighter)attacker).getKi()-1);
		}
	}
}
