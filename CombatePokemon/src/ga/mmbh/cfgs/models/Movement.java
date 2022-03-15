package models;

public class Movement {

	private String name;
	private PokemonType type;
	private int maxPP, actualPP, damage, changeAttack, changeDefense, changeSpAttack, changeSpDef, changeSpeed;
	private State state;
	private double probability;
	
	public Movement(String name, PokemonType type, int maxPP, int damage, State state, double probability) {
		this.name = name;
		this.type = type;
		this.maxPP = maxPP;
		this.actualPP = maxPP;
		this.damage = damage;
		this.state = state;
		this.probability = probability;
	}


	// Getters & Setters	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PokemonType getType() {
		return type;
	}

	public void setType(PokemonType type) {
		this.type = type;
	}

	public int getMaxPP() {
		return maxPP;
	}

	public void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}

	public int getActualPP() {
		return actualPP;
	}

	public void setActualPP(int actualPP) {
		this.actualPP = actualPP;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getChangeAttack() {
		return changeAttack;
	}

	public void setChangeAttack(int changeAttack) {
		this.changeAttack = changeAttack;
	}

	public int getChangeDefense() {
		return changeDefense;
	}

	public void setChangeDefense(int changeDefense) {
		this.changeDefense = changeDefense;
	}

	public int getChangeSpAttack() {
		return changeSpAttack;
	}

	public void setChangeSpAttack(int changeSpAttack) {
		this.changeSpAttack = changeSpAttack;
	}

	public int getChangeSpDef() {
		return changeSpDef;
	}

	public void setChangeSpDef(int changeSpDef) {
		this.changeSpDef = changeSpDef;
	}

	public int getChangeSpeed() {
		return changeSpeed;
	}

	public void setChangeSpeed(int changeSpeed) {
		this.changeSpeed = changeSpeed;
	}

	public State getState() {
		return state;
	}

	public boolean hasState() {
		return state != null && state != State.NONE;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public double getProbability() {
		return this.probability;
	}
	
}
