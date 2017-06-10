package de.inces.nearcon.app.data;

import java.util.ArrayList;
import java.util.List;

import de.inces.nearcon.core.model.events.EventCategory;

public class EventCategoryProvider {

    private List<EventCategory> categories = new ArrayList<EventCategory>();

    public EventCategoryProvider() {
        categories.add(new EventCategory(1, "Run and Fun"));
        categories.add(new EventCategory(2, "Party Hard"));
        categories.add(new EventCategory(3, "Meet and Eat"));
    }

    public List<EventCategory> getCategories() {
        return categories;
    }

}
