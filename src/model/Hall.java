package model;
import model.enums.HallType;

public class Hall {
    private int hallId;
    private String name;
    private int capacity;
    private HallType type;

    public Hall (int hallId, String name, int capacity, HallType type) {
        this.hallId = hallId;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hall ID: " + hallId  + "\nHall Name: " + name + "\nHall Capacity: " + capacity + "\n\n";
    }
    public int getHallId() {
        return hallId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public HallType getType(){
        return type;
    }
}