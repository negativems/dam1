package models;

public class Movement {

	private final String name;
	private final PokemonType type;
	private final int maxPP, actualPP, damage, changeAttack, changeDef, changeSpAttack, changeSpDef, changeSpeed;
	private final Status applyStatus;
	
	public Movement(String name, PokemonType type, int maxPP, int actualPP, int damage, int changeAttack,
			int changeDef, int changeSpAttack, int changeSpDef, int changeSpeed, Status applyStatus) {
		this.name = name;
		this.type = type;
		this.maxPP = maxPP;
		this.actualPP = actualPP;
		this.damage = damage;
		this.changeAttack = changeAttack;
		this.changeDef = changeDef;
		this.changeSpAttack = changeSpAttack;
		this.changeSpDef = changeSpDef;
		this.changeSpeed = changeSpeed;
		this.applyStatus = applyStatus;
	}
	
	
	
}
