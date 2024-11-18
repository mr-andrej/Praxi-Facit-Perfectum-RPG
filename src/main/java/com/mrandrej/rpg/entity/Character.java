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
    private final int maxLevel = 50; // Max. level 50 by default
    private int currentExperience = 0;
    private int requiredExperience = 100; // Could apply some math to this to increase it at a variable rate

    // SPECIAL - Default 5 [1 - 10]
    private int strength = 5;
    private int perception = 5;
    private int endurance = 5;
    private int charisma = 5;
    private int intelligence = 5;
    private int agility = 5;
    private int luck = 5;

    // Other
    private int carryCapacity = 50;


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
        int baseAttack = attack;
        int realAttack = baseAttack + (baseAttack * (strength / 4));

        return realAttack;
    }

    public int getDefense() {
        int baseDefense = defense;
        int realDefense = baseDefense + (baseDefense * (endurance / 4));

        return realDefense;
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

    public void checkCanLevelUp() {
        if (currentExperience >= requiredExperience) {
            currentLevel += 1;
            currentExperience = 0;
        }

        // TODO: Add some sort of banner that player has levelled up
    }
}
