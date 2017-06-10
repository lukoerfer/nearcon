package de.inces.nearcon.core.model.events;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
public class EventCategory {

    @Id
    @GeneratedValue
    @Getter
    private int id;

    @Getter
    private String name;

    @OneToMany(mappedBy = "category")
    @Getter
    private List<EventIcon> icons;

    public EventCategory(String name) {
        this.name = name;
    }
}
