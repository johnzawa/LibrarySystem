package model;

public class Hall {
    private int hallId;
    private String name;
    private int capacity;
    private String type;

    public Hall (int hallId, String name, int capacity, String type) {
        this.hallId = hallId;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
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

    public String getType() {
        return type;
    }
}