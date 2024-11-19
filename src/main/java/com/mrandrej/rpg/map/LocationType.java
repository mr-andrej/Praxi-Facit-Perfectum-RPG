package main.java.com.mrandrej.rpg.map;

public enum LocationType {
    PLAINS(".", "Open Plains", "A vast expanse of grassland stretches before you."),
    FOREST("♣", "Dense Forest", "Tall trees surround you, their leaves rustling in the wind."),
    MOUNTAINS("▲", "Mountains", "Steep rocky cliffs and misty peaks loom overhead."),
    RUINS("□", "Ancient Ruins", "Crumbling stone structures hint at a forgotten civilization."),
    VILLAGE("♦", "Village", "A small settlement with simple wooden buildings.");

    private final String symbol;
    private final String name;
    private final String description;

    LocationType(String symbol, String name, String description) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
