package de.inces.nearcon.events;

import java.util.Calendar;
import de.inces.nearcon.users.User;

public class Event {

    private User Creator;

    private EventCategory Category;

    private EventIcon Icon;

    private Calendar ExpireTime;

    private EventLocation Location;

    private int Exclusivity;

    private EventVisibility Visibility;

    public User getCreator() {
        return Creator;
    }

    public EventCategory getCategory() {
        return Category;
    }

    public EventIcon getIcon() {
        return Icon;
    }

    public Calendar getExpireTime() {
        return ExpireTime;
    }

    public EventLocation getLocation() {
        return Location;
    }

    public int getExclusivity() {
        return Exclusivity;
    }

    public EventVisibility getVisibility() {
        return Visibility;
    }
}
