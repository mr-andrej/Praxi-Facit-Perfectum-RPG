package main.java.com.mrandrej.rpg;

import main.java.com.mrandrej.rpg.entity.Enemy;
import main.java.com.mrandrej.rpg.entity.Player;
import main.java.com.mrandrej.rpg.map.Direction;
import main.java.com.mrandrej.rpg.map.Map;
import main.java.com.mrandrej.rpg.map.Location;

import java.util.Scanner;

public class Game {
    private Player player;
    private final Scanner scanner;
    private Map worldMap;
    private boolean isRunning;

    public Game() {
        scanner = new Scanner(System.in);
        isRunning = true;
        worldMap = new Map();
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
            processMainMenuCommand(choice);
        }
    }

    private void printMenu() {
        MenuSystem.clearScreen();
        MenuSystem.printBoxedHeader("MAIN MENU");
        String[] options = {
                "Show Player Status",
                "View Map",
                "Travel",
                "Explore Current Area",
                "Trader", // TODO: Access to the trader should only be possible at the location type of village
                "Rest",
                "Quit"
        };
        MenuSystem.printBoxedMenu(options);
        System.out.print("\nChoose your action: ");
    }

    private void printMenu(String title, String[] options) {
        MenuSystem.clearScreen();
        MenuSystem.printBoxedHeader(title.toUpperCase());
        MenuSystem.printBoxedMenu(options);
        System.out.print("\nChoose your action: ");
    }

    private void processMainMenuCommand(String choice) {
        switch (choice) {
            case "1" -> {
                MenuSystem.clearScreen();
                MenuSystem.printPlayerStatus(player);
                MenuSystem.pressEnterToContinue();
            }
            case "2" -> viewMap();
            case "3" -> travel();
            case "4" -> explore();
            case "5" -> trader();
            case "6" -> rest();
            case "7" -> {
                isRunning = false;
                System.out.println("Thanks for playing!");
            }
            default -> {
                System.out.println("Invalid choice!");
                MenuSystem.pressEnterToContinue();
            }
        }
    }

    private void processTraderCommand(String choice) {
        switch (choice) {
            case "1" -> buyFromTrader();
            case "2" -> sellToTrader();
            default -> {
                System.out.println("Invalid choice!");
                MenuSystem.pressEnterToContinue();
            }
        }
    }

    private void buyFromTrader() { // TODO: Finish the logic for this
        MenuSystem.clearScreen();
        System.out.println("\nYou approach the trader");

        String[] options = {
                "Dull Sword",
                "Peasant Work Clothes",
                "Leather Cap"
        };
        printMenu("Buying from the Trader", options);
    }

    private void sellToTrader() {

    }

    private void explore() {
        MenuSystem.clearScreen();
        System.out.println("\nExploring...");

        final double randomEventToken = Math.random();

        // Updating current location as explored
        worldMap.setCurrentLocationExplored();

        // Random event decision

        if (randomEventToken < 0.7) {
            Enemy enemy = generateEnemy();
            startBattle(enemy);
        } else if (randomEventToken > 0.7 && randomEventToken < 0.9) {
            System.out.println("You found something interesting.");
            MenuSystem.pressEnterToContinue();


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

    private void viewMap() {
        MenuSystem.clearScreen();
        System.out.println(worldMap.getMapDisplay());
        System.out.println("\n" + worldMap.getCurrentLocationInfo());
        MenuSystem.pressEnterToContinue();
    }

    private void travel() {
        MenuSystem.clearScreen();
        System.out.println(worldMap.getMapDisplay());
        System.out.println("\nWhere would you like to travel?");
        String[] options = {
                "North",
                "South",
                "East",
                "West",
                "Cancel"
        };
        MenuSystem.printBoxedMenu(options);

        String choice = scanner.nextLine();
        Direction direction = null;

        switch (choice) {
            case "1" -> direction = Direction.NORTH;
            case "2" -> direction = Direction.SOUTH;
            case "3" -> direction = Direction.EAST;
            case "4" -> direction = Direction.WEST;
            case "5" -> {
                return;
            }
        }

        if (direction != null) {
            if (worldMap.canMove(direction)) {
                worldMap.move(direction);
                System.out.println("\nYou travel to a new area...");
                System.out.println(worldMap.getCurrentLocationInfo());
            } else {
                System.out.println("\nYou cannot travel in that direction!");
            }
            MenuSystem.pressEnterToContinue();
        }
    }

    private void trader() {
        MenuSystem.clearScreen();
        System.out.println("\nYou approach the trader");

        String[] options = {
                "Buy",
                "Sell",
        };
        printMenu("Trader", options);
    }
}