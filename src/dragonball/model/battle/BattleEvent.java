package dragonball.model.battle;

import java.util.EventObject;

import dragonball.model.attack.Attack;
import dragonball.model.cell.Collectible;

public class BattleEvent extends EventObject{
	
	private BattleEventType type;
	private BattleOpponent currentOpponent;
	private BattleOpponent winner;
	private Attack attack;
	private Collectible collectible;
	
	public BattleEvent(Battle battle, BattleEventType type){
		super(battle);
	}
	
	public BattleEvent(Battle battle, BattleEventType type, BattleOpponent winner){
		this(battle,type);
	}
	
	public BattleEvent(Battle battle, BattleEventType type, Attack attack){
		this(battle,type);
	}
	


	public BattleEvent(Battle battle, BattleEventType type, Collectible collectible){
		this(battle,type);
	}
	
	public BattleEventType getType() {
		return type;
	}

	public BattleOpponent getCurrentOpponent() {
		return currentOpponent;
	}

	public BattleOpponent getWinner() {
		return winner;
	}

	public Attack getAttack() {
		return attack;
	}

	public Collectible getCollectible() {
		return collectible;
	}
	
}
