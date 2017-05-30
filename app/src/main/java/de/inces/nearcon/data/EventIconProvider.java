package de.inces.nearcon.data;

import java.util.ArrayList;
import java.util.List;

import de.inces.nearcon.events.EventCategory;
import de.inces.nearcon.events.EventIcon;

public class EventIconProvider {

    private List<EventIcon> icons = new ArrayList<EventIcon>();

    public EventIconProvider() {
        icons.add(new EventIcon(1, "basketball"));
        icons.add(new EventIcon(1, "bike"));
        icons.add(new EventIcon(1, "gymbro"));
        icons.add(new EventIcon(1, "running"));
        icons.add(new EventIcon(1, "soccer"));
        icons.add(new EventIcon(1, "swimming"));
        icons.add(new EventIcon(2, "beer"));
        icons.add(new EventIcon(2, "cocktail"));
    }

    public List<EventIcon> getIconsByCategory(int categoryId) {
        List<EventIcon> filtered = new ArrayList<EventIcon>();
        for(EventIcon icon : icons) {
            if (icon.getCategoryId() == categoryId) {
                filtered.add(icon);
            }
        }
        return filtered;
    }

}
