package model;

public class Hall {
    private int hallId;
    private String name;
    private int capacity;

    public Hall (int hallId, String name, int capacity) {
        this.hallId = hallId;
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Hall Name: " + name + "\nHall Capacity: " + capacity + "\n\n";
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
}