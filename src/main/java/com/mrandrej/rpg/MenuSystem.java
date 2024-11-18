package main.java.com.mrandrej.rpg;

import main.java.com.mrandrej.rpg.entity.Character;
import main.java.com.mrandrej.rpg.entity.Enemy;
import main.java.com.mrandrej.rpg.entity.Player;

public class MenuSystem {
    // ASCII box drawing characters
    private static final String TOP_LEFT = "╔";
    private static final String TOP_RIGHT = "╗";
    private static final String BOTTOM_LEFT = "╚";
    private static final String BOTTOM_RIGHT = "╝";
    private static final String HORIZONTAL = "═";
    private static final String VERTICAL = "║";
    private static final String T_RIGHT = "╠";
    private static final String T_LEFT = "╣";

    public static void printBoxedHeader(String title) {
        int width = Math.max(40, title.length() + 4);
        String line = HORIZONTAL.repeat(width - 2);

        // Print top border
        System.out.println(TOP_LEFT + line + TOP_RIGHT);

        // Print title
        int padding = (width - title.length() - 2) / 2;
        String leftPad = " ".repeat(padding);
        String rightPad = " ".repeat(width - title.length() - 2 - padding);
        System.out.println(VERTICAL + leftPad + title + rightPad + VERTICAL);

        // Print bottom border with connection
        System.out.println(T_RIGHT + line + T_LEFT);
    }

    public static void printBoxedMenu(String[] options) {
        int width = 40;  // Standard width for menu

        for (int i = 0; i < options.length; i++) {
            String option = (i + 1) + ". " + options[i];
            String padding = " ".repeat(width - option.length() - 2);
            System.out.println(VERTICAL + " " + option + padding + VERTICAL);
        }

        // Print bottom border
        System.out.println(BOTTOM_LEFT + HORIZONTAL.repeat(width - 2) + BOTTOM_RIGHT);
    }

    public static void printBattleScreen(Player player, Enemy enemy) {
        int width = 40;
        String line = HORIZONTAL.repeat(width - 2);

        // Print top border with battle header
        System.out.println(TOP_LEFT + line + TOP_RIGHT);
        String title = "⚔ BATTLE ⚔";
        int titlePadding = (width - title.length() - 2) / 2;
        System.out.println(VERTICAL + " ".repeat(titlePadding) + title +
                " ".repeat(width - title.length() - 2 - titlePadding) + VERTICAL);
        System.out.println(T_RIGHT + line + T_LEFT);

        // Print player and enemy stats
        printActorStats(player, width);
        System.out.println(T_RIGHT + line + T_LEFT);
        printActorStats(enemy, width);

        // Bottom border
        System.out.println(BOTTOM_LEFT + line + BOTTOM_RIGHT);
    }

    private static void printActorStats(Character actor, int width) {
        String name = actor.getName();
        String health = String.format("HP: %d/%d", actor.getHealth(), actor.getMaxHealth());
        System.out.println(VERTICAL + " " + name + " ".repeat(width - name.length() - 2) + VERTICAL);
        System.out.println(VERTICAL + " " + health + " ".repeat(width - health.length() - 2) + VERTICAL);
    }

    public static void printGameLogo() {
        System.out.println("""
                ╔════════════════════════════════════════════════╗
                ║  _____               _    ______         _ _   ║
                ║ |  __ \\             (_)  |  ____|       (_) |  ║
                ║ | |__) | __ __ _ _  _ _  | |__ __ _  ___ _| |_ ║
                ║ |  ___/ '__/ _` | | | |  |  __/ _` |/ __| | __║
                ║ | |   | | | (_| | |_| |  | | | (_| | (__| | |_║
                ║ |_|   |_|  \\__,_|\\__, |  |_|  \\__,_|\\___|_|\\__║
                ║                    __/ |      Perfectum        ║
                ║                   |___/                        ║
                ╚════════════════════════════════════════════════╝
                """);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printPlayerStatus(Player player) {
        int width = 40;
        String line = HORIZONTAL.repeat(width - 2);

        System.out.println(TOP_LEFT + line + TOP_RIGHT);

        // Print stats
        printStatLine("Name", player.getName(), width);
        printStatLine("Health", player.getHealth() + "/" + player.getMaxHealth(), width);
        printStatLine("Attack", String.valueOf(player.getAttack()), width);
        printStatLine("Defense", String.valueOf(player.getDefense()), width);
        printStatLine("Experience", String.valueOf(player.getCurrentExperience()), width);

        System.out.println(BOTTOM_LEFT + line + BOTTOM_RIGHT);
    }

    private static void printStatLine(String label, String value, int width) {
        String line = String.format("%-12s: %s", label, value);
        String padding = " ".repeat(width - line.length() - 2);
        System.out.println(VERTICAL + " " + line + padding + VERTICAL);
    }

    public static void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }
}
