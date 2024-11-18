package main.java.com.mrandrej.rpg;

import main.java.com.mrandrej.rpg.entity.Enemy;
import main.java.com.mrandrej.rpg.entity.Player;

import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scanner;
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
        MenuSystem.clearScreen();
        MenuSystem.printGameLogo();
        System.out.print("\nEnter your character's name: ");
        String playerName = scanner.nextLine();
        player = new Player(playerName, 100, 10, 5);

        MenuSystem.clearScreen();
        System.out.println("\nWelcome, " + playerName + "! Your adventure begins...");
        MenuSystem.pressEnterToContinue();
    }

    private void gameLoop() {
        while (isRunning) {
            printMenu();
            String choice = scanner.nextLine();
            processCommand(choice);
        }
    }

    private void printMenu() {
        MenuSystem.clearScreen();
        MenuSystem.printBoxedHeader("MAIN MENU");
        String[] options = {
                "Show Player Status",
                "Explore",
                "Rest",
                "Quit"
        };
        MenuSystem.printBoxedMenu(options);
        System.out.print("\nChoose your action: ");
    }

    private void processCommand(String choice) {
        switch (choice) {
            case "1" -> {
                MenuSystem.clearScreen();
                MenuSystem.printPlayerStatus(player);
                MenuSystem.pressEnterToContinue();
            }
            case "2" -> explore();
            case "3" -> rest();
            case "4" -> {
                isRunning = false;
                System.out.println("Thanks for playing!");
            }
            default -> {
                System.out.println("Invalid choice!");
                MenuSystem.pressEnterToContinue();
            }
        }
    }

    private void explore() {
        MenuSystem.clearScreen();
        System.out.println("\nExploring...");
        final double randomEventToken = Math.random();

        if (randomEventToken < 0.7) {  // 70% chance to encounter an enemy
            Enemy enemy = generateEnemy();
            startBattle(enemy);
        } else if (randomEventToken > 0.7 && randomEventToken < 0.9) {
            System.out.println("You found something interesting.");
            // TODO: Get resources
        } else {
            System.out.println("You found nothing interesting...");
            MenuSystem.pressEnterToContinue();
        }
    }

    private Enemy generateEnemy() {
        String[] names = {"Scavenger", "Thug", "Forager", "Bandit"};
        String name = names[(int) (Math.random() * names.length)];
        int health = 50 + (int) (Math.random() * 30);
        int attack = 5 + (int) (Math.random() * 5);
        int defense = 2 + (int) (Math.random() * 3);

        return new Enemy(name, health, attack, defense);
    }

    private void startBattle(Enemy enemy) {
        while (enemy.isAlive() && player.isAlive()) {
            MenuSystem.clearScreen();
            MenuSystem.printBattleScreen(player, enemy);

            String[] options = {
                    "Attack",
                    "Run"
            };
            MenuSystem.printBoxedMenu(options);
            System.out.print("\nWhat will you do? ");

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
                MenuSystem.pressEnterToContinue();
            } else if (choice.equals("2")) {
                if (Math.random() < 0.5) {  // 50% chance to run
                    System.out.println("You successfully ran away!");
                    MenuSystem.pressEnterToContinue();
                    break;
                } else {
                    System.out.println("Couldn't escape!");
                    // Enemy gets a free attack
                    int damage = Math.max(0, enemy.getAttack() - player.getDefense());
                    player.takeDamage(damage);
                    System.out.println(enemy.getName() + " deals " + damage + " damage to you!");
                    MenuSystem.pressEnterToContinue();
                }
            }
        }

        if (!enemy.isAlive()) {
            System.out.println("\nYou defeated the " + enemy.getName() + "!");
            player.gainExperience(10);  // Simple experience gain
            MenuSystem.pressEnterToContinue();
        }
    }

    private void rest() {
        MenuSystem.clearScreen();
        System.out.println("\nYou set up camp and rest...");
        player.heal(20);
        System.out.println("You recovered some HP!");
        MenuSystem.pressEnterToContinue();
    }
}