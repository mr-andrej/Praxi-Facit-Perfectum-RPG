package main.java.com.mrandrej.rpg.items;

public enum ItemType {
    SWORD("Sword", "A weapon to use against your enemies"),
    HELMET("Helmet", "A piece of equipment to protect your head"),
    ARMOR("Armor", "A piece of equipment to protect your body"),
    POTION("Potion","A brew that has various effects");

    private final String name;
    private final String description;

    ItemType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
