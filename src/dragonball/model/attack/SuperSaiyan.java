package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;

public class SuperSaiyan extends UltimateAttack {
	public SuperSaiyan() {
		super("Super Saiyan", 0);
	}
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		if(((Fighter)attacker).getKi() >= 3){
			((Saiyan)attacker).setTransformed(true);
			((Fighter)attacker).setKi(((Fighter)attacker).getKi()-3);
		}
	}
}
