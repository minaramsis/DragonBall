package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;

public class MaximumCharge extends SuperAttack {
	public MaximumCharge() {
		super("Maximum Charge", 0);
	}
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		((Fighter)attacker).setKi(((Fighter)attacker).getKi()+3);
	}
}
