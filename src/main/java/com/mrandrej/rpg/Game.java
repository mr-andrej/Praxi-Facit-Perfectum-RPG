package main.java.com.mrandrej.rpg;

import main.java.com.mrandrej.rpg.entity.Enemy;
import main.java.com.mrandrej.rpg.entity.Player;

import java.util.Scanner;

class Game {
    private Player player;
    private final Scanner scanner;
    private boolean isRunning;

    public Game() {
        scanner = new Scanner(System.in);
        isRunning = true;
    }

    public void start() {
        initGame();
        gameLoop();
    }

    private void initGame() {
        System.out.println("Welcome to Praxi Facit Perfectum the RPG!");
        System.out.print("Enter your character's name: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName, 100, 10, 5);

        System.out.println("\nWelcome, " + playerName + "! Your adventure begins...");
    }

    private void gameLoop() {
        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();
            processCommand(choice);
        }
    }

    private void printMenu() {
        System.out.println("\n=== " + player.getName() + "'s Adventure ===");
        System.out.println("1. Show Player Status");
        System.out.println("2. Explore");
        System.out.println("3. Rest");
        System.out.println("4. Quit");
        System.out.print("Choose your action: ");
    }

    private void processCommand(String choice) {
        switch (choice) {
            case "1" -> player.showStatus();
            case "2" -> explore();
            case "3" -> rest();
            case "4" -> {
                isRunning = false;
                System.out.println("Thanks for playing!");
            }
            default -> System.out.println("Invalid choice!");
        }
    }

    private void explore() {
        System.out.println("\nExploring...");
        if (Math.random() < 0.7) {  // 70% chance to encounter an enemy
            Enemy enemy = generateEnemy();
            startBattle(enemy);
        } else {
            System.out.println("You found nothing interesting...");
        }
    }

    private Enemy generateEnemy() {
        String[] names = {"Goblin", "Skeleton", "Wolf", "Bandit"};
        String name = names[(int) (Math.random() * names.length)];
        int health = 50 + (int) (Math.random() * 30);
        int attack = 5 + (int) (Math.random() * 5);
        int defense = 2 + (int) (Math.random() * 3);

        return new Enemy(name, health, attack, defense);
    }

    private void startBattle(Enemy enemy) {
        System.out.println("\nA " + enemy.getName() + " appears!");

        while (enemy.isAlive() && player.isAlive()) {
            System.out.println("\n--- Battle Status ---");
            System.out.println("Your HP: " + player.getHealth());
            System.out.println(enemy.getName() + "'s HP: " + enemy.getHealth());
            System.out.println("\n1. Attack");
            System.out.println("2. Run");
            System.out.print("What will you do? ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // Player attacks
                int damage = Math.max(0, player.getAttack() - enemy.getDefense());
                enemy.takeDamage(damage);
                System.out.println("\nYou deal " + damage + " damage to " + enemy.getName() + "!");

                // Enemy attacks if still alive
                if (enemy.isAlive()) {
                    damage = Math.max(0, enemy.getAttack() - player.getDefense());
                    player.takeDamage(damage);
                    System.out.println(enemy.getName() + " deals " + damage + " damage to you!");
                }
            } else if (choice.equals("2")) {
                if (Math.random() < 0.5) {  // 50% chance to run
                    System.out.println("You successfully ran away!");
                    break;
                } else {
                    System.out.println("Couldn't escape!");
                    // Enemy gets a free attack
                    int damage = Math.max(0, enemy.getAttack() - player.getDefense());
                    player.takeDamage(damage);
                    System.out.println(enemy.getName() + " deals " + damage + " damage to you!");
                }
            }
        }

        if (!enemy.isAlive()) {
            System.out.println("\nYou defeated the " + enemy.getName() + "!");
            player.gainExperience(10);  // Simple experience gain
        }
    }

    private void rest() {
        System.out.println("\nYou set up camp and rest...");
        player.heal(20);
        System.out.println("You recovered some HP!");
    }
}