package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;

public class UltimateAttack extends Attack {
	public UltimateAttack(String name, int damage) {
		super(name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return getDamage() + ((Fighter)attacker).getBlastDamage();
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		if(((Fighter)attacker).getKi() >= 3){
			super.onUse(attacker, defender, defenderBlocking);
			((Fighter)attacker).setKi(((Fighter)attacker).getKi()-3);
		}
	}
}
