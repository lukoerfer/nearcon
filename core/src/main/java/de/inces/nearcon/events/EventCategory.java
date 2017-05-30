package de.inces.nearcon.events;

public class EventCategory {

    private int Id;
    private String Name;

    public EventCategory(int id, String name) {
        this.Id = id;
        this.Name = name;
    }

    public int getId() {
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
