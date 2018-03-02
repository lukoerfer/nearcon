package de.inces.nearcon.core.model.events;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.inces.nearcon.core.model.auth.User;
import lombok.Getter;

@Entity
public class Event {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    private User creator;

    @Getter
    private EventCategory category;

    @Getter
    private EventIcon icon;

    @Getter
    private String description;

    @Getter
    private Calendar expireDate;

    @Embedded
    @Column
    @Getter
    private EventLocation location;

    @Getter
    private int exclusivity;

    @Getter
    private EventVisibility visibility;

    public Event(User creator, EventIcon icon, String description, EventLocation location) {
        this.creator = creator;
        this.icon = icon;
        this.description = description;
        this.location = location;
    }

}
