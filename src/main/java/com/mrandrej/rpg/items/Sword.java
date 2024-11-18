package main.java.com.mrandrej.rpg.items;

public class Sword {
    private String name;
    private String weight;
    private String type; // TODO: Maybe this should be abstracted away

    public Sword (String name, String weight, String type) {
        this.name = name;
        this.weight = weight;
        this.type = type != null ?  type : "weapon";
    }
}