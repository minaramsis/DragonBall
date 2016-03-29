package dragonball.model.player;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.game.Game;

public class Player {
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;
	
	private PlayerListener playerListener;

	public Player(String name) {
		this(name, new ArrayList<PlayableFighter>(), new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>(), 0,
				0, null, 0);
	}

	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans, int dragonBalls, PlayableFighter activeFighter,
			int exploredMaps) {
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}
	
	//Milestone 2
	
	int getMaxFighterLevel(){
		int maxLvl = 0;
		for(PlayableFighter fighter: fighters){
			if(fighter.getLevel() > maxLvl){
				maxLvl = fighter.getLevel();
			}
		}
		return maxLvl;
	}
	
	public void callDragon(){
		playerListener.onDragonCalled();
	}
	
	public void chooseWish(DragonWish wish){
		//TODO grant wishes? ap
		switch(wish.getType()){
			case ABILITY_POINTS:
				getActiveFighter().setAbilityPoints(this.getActiveFighter().getAbilityPoints() + wish.getAbilityPoints());
				break;
			case SENZU_BEANS:
				setSenzuBeans(getSenzuBeans() + wish.getSenzuBeans());
				break;
			case SUPER_ATTACK:
				superAttacks.add(wish.getSuperAttack());
				break;
			case ULTIMATE_ATTACK:
				ultimateAttacks.add(wish.getUltimateAttack());
				break;
		}
		playerListener.onWishChosen(wish);
	}
	
	public void createFighter(char race, String name){
		PlayableFighter pf;
		switch(race){
			case 'E':
				pf = new Earthling(name);
				fighters.add(pf);
				break;
			case 'S':
				pf = new Saiyan(name);
				fighters.add(pf);
				break;
			case 'N':
				pf = new Namekian(name);
				fighters.add(pf);
				break;
			case 'F':
				pf = new Frieza(name);
				fighters.add(pf);
				break;
			case 'M':
				pf = new Majin(name);
				fighters.add(pf);
				break;
		}
		if(fighters.size() == 1){
			activeFighter = fighters.get(0);
		}
	}
	
	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute){
		switch(fighterAttribute){
			case 'H':
				fighter.setMaxHealthPoints(fighter.getMaxHealthPoints() + 50);
				break;
			case 'B':
				fighter.setBlastDamage(fighter.getBlastDamage() + 50);
				break;
			case 'P':
				fighter.setPhysicalDamage(fighter.getPhysicalDamage() + 50);
				break;
			case 'K':
				fighter.setMaxKi(fighter.getMaxKi()+1);
				break;
			case 'S':
				fighter.setMaxStamina(fighter.getMaxStamina() + 1);
				break;
		}
	}
	
	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack, SuperAttack oldAttack){
		if(oldAttack == null && fighter.getSuperAttacks().size() < 4){
			fighter.getSuperAttacks().add(newAttack);
		}else if(oldAttack != null){
			fighter.getSuperAttacks().remove(oldAttack);
			fighter.getSuperAttacks().add(newAttack);
		}
	}
	
	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack, UltimateAttack oldAttack){
		if(oldAttack == null && fighter.getSuperAttacks().size() < 2){
			fighter.getUltimateAttacks().add(newAttack);
		}else if(oldAttack != null){
			fighter.getUltimateAttacks().remove(oldAttack);
			fighter.getUltimateAttacks().add(newAttack);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public PlayerListener getPlayerListener() {
		return playerListener;
	}

	public void setPlayerListener(PlayerListener playerListener) {
		this.playerListener = playerListener;
	}
	
	
}
