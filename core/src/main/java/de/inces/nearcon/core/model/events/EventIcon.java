package de.inces.nearcon.core.model.events;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
public class EventIcon {

    @Id
    @GeneratedValue
    @Getter
    private int id;

    @ManyToOne
    @JoinColumn
    @Getter
    private EventCategory category;

    @Getter
    private String resource;

    public EventIcon(EventCategory category, String resource) {
        this.category = category;
        this.resource = resource;
    }

}
