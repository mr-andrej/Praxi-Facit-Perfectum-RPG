package main.java.com.mrandrej.rpg.entity;

import main.java.com.mrandrej.rpg.items.Item;

import java.util.ArrayList;

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

    // Inventory
    private int currentCarryLoad = 0;
    private int maxCarryCapacity = 50;
    private ArrayList<Item> inventory;

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

    public int getCurrentCarryLoad() {
        return currentCarryLoad;
    }

    public void setCurrentCarryLoad(int currentCarryLoad) {
        this.currentCarryLoad = currentCarryLoad;
    }

    public int getMaxCarryCapacity() {
        return maxCarryCapacity;
    }

    public void setMaxCarryCapacity(int maxCarryCapacity) {
        this.maxCarryCapacity = maxCarryCapacity;
    }

    public String addItemToInventory(Item item) {
        if (this.currentCarryLoad + item.getWeight() <= this.maxCarryCapacity) {
            this.inventory.add(item); // Add new item to inventory
            this.currentCarryLoad += item.getWeight(); // Update the current carry load

            return String.format("%s has been added to your inventory. [%s/%s]", item.getName(), currentCarryLoad, maxCarryCapacity);
        } else {
            return String.format("The item is too heavy (%s), you cannot carry it. [%s/%s]", item.getWeight(), currentCarryLoad, maxCarryCapacity);
        }
    }

    public String removeItemFromInventory(Item item) {
        if (this.inventory.contains(item)) {
            this.inventory.remove(item);
            this.currentCarryLoad -= item.getWeight();

            return String.format("%s has been removed from your inventory. [%s/%s]", item.getName(), currentCarryLoad, maxCarryCapacity);
        } else {
            return "Impossible to remove item as it is not in your inventory";
        }
    }

    public void checkCanLevelUp() {
        if (currentExperience >= requiredExperience) {
            currentLevel += 1;
            currentExperience = 0;
        }

        // TODO: Add some sort of banner that player has levelled up
    }
}
