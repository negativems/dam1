package models;

import java.util.ArrayList;
import java.util.List;

import utils.Storage;

public class Pokemon {

	private int id, attack, defense, specialAttack, specialDefense, speed, maxHealth, health, level;
	private String name;
	private PokemonType type, type2;
	private State state;
	private List<Movement> movements = new ArrayList<>();
	
	public Pokemon(int id, int attack, int defense, int specialAttack, int specialDefense, int speed, int maxHealth, int level, String name, PokemonType type, PokemonType type2) {
		
		this.id = id;
		this.attack = attack;
		this.defense = defense;
		this.specialAttack = specialAttack;
		this.specialDefense = specialDefense;
		this.speed = speed;
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.level = level;
		this.name = name;
		this.type = type;
		this.type2 = type2;
		
		for (int i = 0; i < Storage.MOVEMENTS.size(); i++) {
			Movement movement = Storage.MOVEMENTS.get(i);
			if (type == movement.getType() || type2 == movement.getType()) {
				movements.add(movement);
			}
		}
	}

	public double receiveAttack(Movement movement) {
		int power = movement.getDamage();
		int damage = (((((2 * level) / 5) + 2) * power * (attack / defense)) / 50) + 2;
		
		this.health -= damage;
		return damage;
	}
	
	// Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(int specialAttack) {
		this.specialAttack = specialAttack;
	}

	public int getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(int specialDefense) {
		this.specialDefense = specialDefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

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

	public PokemonType getType2() {
		return type2;
	}

	public void setType2(PokemonType type2) {
		this.type2 = type2;
	}

	public State getState() {
		return state;
	}
	
	public boolean hasState() {
		return state != null;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	
	
}
