package de.inces.nearcon.core.model.events;

import javax.persistence.Embeddable;

import lombok.Getter;

@Embeddable
public class EventLocation {

    @Getter
    private double latitude;
    @Getter
    private double longitude;
    @Getter
    private double radius;

    public EventLocation(double lat, double lng, double radius) {
        this.latitude = lat;
        this.longitude = lng;
        this.radius = radius;
    }

}
