package dragonball.model.battle;

import java.util.ArrayList;

import dragonball.model.character.fighter.Fighter;
import dragonball.model.game.Game;
import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private Game game;

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;

		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}
	
	ArrayList<Attack> getAssignedAttacks(){
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		
		Attack physicalAttack = new PhysicalAttack();
		attacks.add(physicalAttack);
		attacks.addAll(((Fighter) attacker).getSuperAttacks());
		attacks.addAll(((Fighter) attacker).getUltimateAttacks());
		
		return attacks;
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}
	
	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
