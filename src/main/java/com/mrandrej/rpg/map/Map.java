package main.java.com.mrandrej.rpg.map;

import java.util.Random;

public class Map {
    private Location[][] worldMap;
    private int currentX;
    private int currentY;
    private static final int MAP_SIZE = 10;
    private Random random;

    public Map() {
        worldMap = new Location[MAP_SIZE][MAP_SIZE];
        random = new Random();
        generateWorld();
        // Start in the middle of the map
        currentX = MAP_SIZE / 2;
        currentY = MAP_SIZE / 2;
    }

    private void generateWorld() {
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                // Randomly assign location types, with PLAINS being most common
                double rand = random.nextDouble();
                LocationType type;

                if (rand < 0.4) {
                    type = LocationType.FOREST;
                } else if (rand < 0.6) {
                    type = LocationType.PLAINS;
                } else if (rand < 0.75) {
                    type = LocationType.MOUNTAINS;
                } else if (rand < 0.85) {
                    type = LocationType.RUINS;
                } else {
                    type = LocationType.VILLAGE;
                }

                worldMap[y][x] = new Location(type);
            }
        }
    }

    public String getMapDisplay() {
        StringBuilder display = new StringBuilder();

        // Add top border
        display.append("╔");
        display.append("═".repeat(MAP_SIZE * 2 + 1));
        display.append("╗\n");

        for (int y = 0; y < MAP_SIZE; y++) {
            display.append("║ ");
            for (int x = 0; x < MAP_SIZE; x++) {
                if (x == currentX && y == currentY) {
                    display.append("@ "); // Player position
                } else {
                    display.append(worldMap[y][x].getSymbol()).append(" ");
                }
            }
            display.append("║\n");
        }

        // Add bottom border
        display.append("╚");
        display.append("═".repeat(MAP_SIZE * 2 + 1));
        display.append("╝\n");

        // Add legend
        display.append("\nLegend:\n");
        display.append("@ - You are here\n");
        for (LocationType type : LocationType.values()) {
            display.append(type.getSymbol()).append(" - ").append(type.getDescription()).append("\n");
        }

        return display.toString();
    }

    public boolean canMove(Direction direction) {
        int newX = currentX + direction.getDx();
        int newY = currentY + direction.getDy();
        return newX >= 0 && newX < MAP_SIZE && newY >= 0 && newY < MAP_SIZE;
    }

    public void move(Direction direction) {
        if (canMove(direction)) {
            currentX += direction.getDx();
            currentY += direction.getDy();
        }
    }

    public Location getCurrentLocation() {
        return worldMap[currentY][currentX];
    }

    public void setCurrentLocationExplored() {
        worldMap[currentY][currentX].setExplored();
    }

    public String getCurrentLocationInfo() {
        Location current = getCurrentLocation();

        return String.format("Current location: %s\n",
                current.getDescription());
    }
}

