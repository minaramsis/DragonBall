package dragonball.model.battle;

import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;
import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener battleListener;

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
		
		if(me instanceof Saiyan){
			((Saiyan) me).setTransformed(false);
		}
		
		if(foe instanceof Saiyan){
			((Saiyan) foe).setTransformed(false);
		}
	}
	
	ArrayList<Attack> getAssignedAttacks(){
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		
		Attack physicalAttack = new PhysicalAttack();
		//TODO add physical damage
		attacks.add(physicalAttack);
		attacks.addAll(((Fighter) attacker).getSuperAttacks());
		attacks.addAll(((Fighter) attacker).getUltimateAttacks());
		
		return attacks;
	}
	
	void attack(Attack attack){
		//TODO to be implemented
	}
	
	void block(){
		if(attacker.equals(me)){
			meBlocking = true;
		}
		else if(attacker.equals(foe)){
			foeBlocking =  true;
		}
		BattleEvent battleEvent = new BattleEvent(this, BattleEventType.BLOCK);
		battleListener.onBattleEvent(battleEvent);
	}
	
	void use(Player player, Collectible collectible){
		if(collectible == Collectible.SENZU_BEAN){
			if(player.getCollectibles().contains(collectible)){
				player.getActiveFighter().setHealthPoints(999999);
				player.getActiveFighter().setStamina(999999);
				player.getCollectibles().remove(collectible);
				BattleEvent battleEvent = new BattleEvent(this, BattleEventType.USE, collectible);
				battleListener.onBattleEvent(battleEvent);
			}
		}
	}
	
	BattleOpponent getDefender(){
		if(attacker.equals(me)){
			return foe;
		}else{
			return me;
		}
	}
	
	void play(){
		attacker = foe;
		ArrayList<Attack> attacks = getAssignedAttacks();
		Random rand = new Random();
		int choice  = rand.nextInt(attacks.size()+1);
		if(choice == attacks.size()){
			block();
		}else{
			//TODO method to be implemented
			attack(attacks.get(choice));
		}
	}
	
	void start(){
		//TODO to be implemented (notify listener that battle about to begin)
	}
	
	void endTurn(){
		//TODO to be implemented
	}
	
	void switchTurn(){
		attacker = getDefender();
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

	public BattleListener getBattleListener() {
		return battleListener;
	}

	public void setBattleListener(BattleListener battleListener) {
		this.battleListener = battleListener;
	}
	
	
}
