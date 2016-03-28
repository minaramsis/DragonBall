package dragonball.model.dragon;

import java.util.EventObject;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class DragonWish extends EventObject{
	
	private DragonWishType type;
	private int senzuBeans;
	private int abilityPoints;
	private SuperAttack superAttack;
	private UltimateAttack ultimateAttack;
	
	DragonWish(Dragon dragon, DragonWishType type){
		super(dragon);
	}
	
	DragonWish(Dragon dragon, DragonWishType type, int senzuBeansOrAbilityPoints){
		this(dragon,type);
	}
	
	DragonWish(Dragon dragon, DragonWishType type, SuperAttack superAttack){
		this(dragon,type);
	}
	
	DragonWish(Dragon dragon, DragonWishType type, UltimateAttack ultimateAttack){
		this(dragon,type);
	}
	
	public DragonWishType getType() {
		return type;
	}
	public int getSenzuBeans() {
		return senzuBeans;
	}
	public int getAbilityPoints() {
		return abilityPoints;
	}
	public SuperAttack getSuperAttack() {
		return superAttack;
	}
	public UltimateAttack getUltimateAttack() {
		return ultimateAttack;
	}

}
