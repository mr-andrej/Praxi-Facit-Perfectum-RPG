package main.java.com.mrandrej.rpg.map;

public class Location {
    private final LocationType type;
    private boolean isExplored;

    public Location(LocationType type) {
        this.type = type;
        this.isExplored = false;
    }

    public LocationType getType() {
        return type;
    }

    public String getSymbol() {
        return type.getSymbol();
    }

    public boolean getExplored() {
        return isExplored;
    }

    public void setExplored() {
        isExplored = true;
    }

    public String getDescription() {
        if (!isExplored) {
            return "You haven't explored this area yet.";
        }

        return type.getDescription();
    }
}

