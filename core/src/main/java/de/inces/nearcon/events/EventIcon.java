package de.inces.nearcon.events;

public class EventIcon {

    private int CategoryId;
    private String Id;

    public EventIcon(int categoryId, String id) {
        this.CategoryId = categoryId;
        this.Id = id;
    }

    public int getCategoryId() {
        return this.CategoryId;
    }

    public String getId() {
        return this.Id;
    }


}
