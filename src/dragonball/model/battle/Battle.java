package dragonball.model.battle;

import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.game.Game;
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
		battleListener = new Game();

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
	
	public ArrayList<Attack> getAssignedAttacks(){
		ArrayList<Attack> attacks = new ArrayList<Attack>();
		
		Attack physicalAttack = new PhysicalAttack();
		//TODO add physical damage
		attacks.add(physicalAttack);
		attacks.addAll(((Fighter) attacker).getSuperAttacks());
		attacks.addAll(((Fighter) attacker).getUltimateAttacks());
		
		return attacks;
	}
	
	public void attack(Attack attack){
		attack.onUse(attacker, getDefender(), attacker.equals(me) ? foeBlocking : meBlocking);
		BattleEvent battleEvent = new BattleEvent(this, BattleEventType.ATTACK, attack);
		battleListener.onBattleEvent(battleEvent);
		endTurn();
	}
	
	public void block(){
		if(attacker.equals(me)){
			meBlocking = true;
		}
		else if(attacker.equals(foe)){
			foeBlocking =  true;
		}
		BattleEvent battleEvent = new BattleEvent(this, BattleEventType.BLOCK);
		battleListener.onBattleEvent(battleEvent);
		endTurn();
	}
	
	public void use(Player player, Collectible collectible){
		if(collectible == Collectible.SENZU_BEAN){
			if(player.getSenzuBeans() >= 1){
				player.getActiveFighter().setHealthPoints(999999);
				player.getActiveFighter().setStamina(999999);
				player.setSenzuBeans(player.getSenzuBeans()-1);
				BattleEvent battleEvent = new BattleEvent(this, BattleEventType.USE, collectible);
				battleListener.onBattleEvent(battleEvent);
			}
		}
	}
	
	public BattleOpponent getDefender(){
		if(attacker.equals(me)){
			return foe;
		}else{
			return me;
		}
	}
	
	public void play(){
		attacker = foe;
		ArrayList<Attack> attacks = getAssignedAttacks();
		Random rand = new Random();
		int choice  = rand.nextInt(attacks.size()+1);
		if(choice == attacks.size()){
			block();
		}else{
			attack(attacks.get(choice));
		}
	}
	
	public void start(){
		BattleEvent battleEvent = new BattleEvent(this, BattleEventType.STARTED);
		battleListener.onBattleEvent(battleEvent);
	}
	
	public void endTurn(){
		if(((Fighter)getDefender()).getHealthPoints() <= 0){
			BattleEvent battleEvent = new BattleEvent(this,BattleEventType.ENDED, attacker);
			battleListener.onBattleEvent(battleEvent);
		}else{
			if(attacker.equals(me)){
				foeBlocking = false;
			}else{
				meBlocking = false;
			}
			switchTurn();
			attacker.onAttackerTurn();
			getDefender().onDefenderTurn();
			BattleEvent battleEvent = new BattleEvent(this,BattleEventType.NEW_TURN);
			battleListener.onBattleEvent(battleEvent);
		}
	}
	
	public void switchTurn(){
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
