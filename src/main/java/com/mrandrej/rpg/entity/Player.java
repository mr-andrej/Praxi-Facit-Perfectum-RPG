package main.java.com.mrandrej.rpg.entity;

// Player class
public class Player extends main.java.com.mrandrej.rpg.entity.Character {
    private int experience;
    private int level;

    public Player(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
        this.experience = 0;
        this.level = 1;
    }

    public void gainExperience(int exp) {
        experience += exp;
        checkLevelUp();
    }

    private void checkLevelUp() {
        int nextLevel = level * 20;  // Simple level scaling
        if (experience >= nextLevel) {
            level++;
            System.out.println("\nLevel Up! You are now level " + level + "!");
        }
    }

    public void showStatus() {
        System.out.println("\n=== " + getName() + "'s Status ===");
        System.out.println("Level: " + level);
        System.out.println("HP: " + getHealth());
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Experience: " + experience);
        System.out.println("Next level at: " + (level * 20) + " exp");
    }
}
