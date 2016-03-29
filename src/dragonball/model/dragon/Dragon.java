package dragonball.model.dragon;

import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon {
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;

	public Dragon(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks,
			int senzuBeans, int abilityPoints) {
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	}
	
	public DragonWish[] getWishes(){
		Random rand = new Random();
		DragonWish[] wishes = new DragonWish[4];
		wishes[0] = new DragonWish(this, DragonWishType.ABILITY_POINTS, abilityPoints);
		wishes[1] = new DragonWish(this, DragonWishType.SENZU_BEANS, senzuBeans);
		wishes[2] = new DragonWish(this, DragonWishType.SUPER_ATTACK,
				superAttacks.get(rand.nextInt(superAttacks.size())));
		wishes[3] = new DragonWish(this, DragonWishType.ULTIMATE_ATTACK,
				ultimateAttacks.get(rand.nextInt(ultimateAttacks.size())));
		return wishes;
	}

	public String getName() {
		return name;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}
}
