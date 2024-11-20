package main.java.com.mrandrej.rpg.items;

public abstract class Item {
    private String name;
    private int weight;
    private ItemType type;

    public Item (String name, int weight, ItemType type) {
        this.name = name;
        this.weight = weight;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public ItemType getType() {
        return this.type;
    }
}
