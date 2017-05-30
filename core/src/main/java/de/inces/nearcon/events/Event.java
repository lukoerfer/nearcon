package de.inces.nearcon.events;

import java.util.Calendar;
import de.inces.nearcon.users.User;

public class Event {

    private int Id;
    private User Creator;
    private EventCategory Category;
    private EventIcon Icon;
    private String Description;
    private Calendar ExpireTime;
    private EventLocation Location;
    private int Exclusivity;
    private EventVisibility Visibility;

    public int getId() {
        return this.Id;
    }

    public User getCreator() {
        return this.Creator;
    }

    public EventCategory getCategory() {
        return this.Category;
    }

    public EventIcon getIcon() {
        return this.Icon;
    }

    public String getDescription() {
        return this.Description;
    }

    public Calendar getExpireTime() {
        return this.ExpireTime;
    }

    public EventLocation getLocation() {
        return this.Location;
    }

    public int getExclusivity() {
        return this.Exclusivity;
    }

    public EventVisibility getVisibility() {
        return this.Visibility;
    }
}
