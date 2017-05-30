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

}
