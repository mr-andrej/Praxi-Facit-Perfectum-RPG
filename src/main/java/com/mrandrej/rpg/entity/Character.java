package main.java.com.mrandrej.rpg.entity;

// Character class (parent class for Player and Enemy)
public abstract class Character {
    // Customization
    private String name;
    // Combat
    private int health;
    private int maxHealth;
    private int attack;
    private int defense;
    // Levelling
    private int currentLevel = 1;
    private int maxLevel = 50; // Max. level 50 by default
    private int currentExperience = 0;
    private int requiredExperience = 100; // Could apply some math to this to increase it at a variable rate
    // Characteristics
    private int intelligence;
    private int strength;
    private int endurance;


    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }

    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount);
    }

    public boolean isAlive() {
        return health > 0;
    }
}
