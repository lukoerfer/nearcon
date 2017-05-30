package de.inces.nearby.events;

import java.util.Calendar;
import de.inces.nearby.users.User;

public class Event {

    private User Creator;

    private EventCategory Category;

    private EventIcon Icon;

    private Calendar ExpireTime;

    private EventLocation Location;

    private int Exclusivity;

    private EventVisibility Visibility;

}
