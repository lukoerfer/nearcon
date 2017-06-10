package de.inces.nearcon.app.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.inces.nearcon.core.model.events.EventIcon;

public class EventIconProvider {

    private List<EventIcon> icons = new ArrayList<EventIcon>();

    public EventIconProvider() {
        icons.add(new EventIcon(1, "basketball"));
        icons.add(new EventIcon(1, "bike"));
        icons.add(new EventIcon(1, "gymbro"));
        icons.add(new EventIcon(1, "running"));
        icons.add(new EventIcon(1, "soccer"));
        icons.add(new EventIcon(1, "swimming"));
        icons.add(new EventIcon(1, "tennis"));
        icons.add(new EventIcon(2, "beer"));
        icons.add(new EventIcon(2, "cocktail"));
        icons.add(new EventIcon(2, "wine"));
        icons.add(new EventIcon(2, "dancing"));
        icons.add(new EventIcon(2, "partyhat"));
        icons.add(new EventIcon(3, "cooking"));
        icons.add(new EventIcon(3, "mensa"));
        icons.add(new EventIcon(3, "pizza"));
        icons.add(new EventIcon(3, "tea"));
        icons.add(new EventIcon(3, "movie"));
        icons.add(new EventIcon(3, "heart"));
        icons.add(new EventIcon(3, "sunbathe"));
        icons.add(new EventIcon(3, "zoo"));
        icons.add(new EventIcon(3, "games"));
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

    public EventIcon getRandomIcon() {
        Random r = new Random();
        return icons.get(r.nextInt(icons.size()));
    }

}
