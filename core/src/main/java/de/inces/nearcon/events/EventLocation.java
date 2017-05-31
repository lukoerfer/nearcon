package de.inces.nearcon.events;


public class EventLocation {

    private double Latitude;
    private double Longitude;
    private double Radius;

    public EventLocation(double lat, double lng, double radius) {
        this.Latitude = lat;
        this.Longitude = lng;
        this.Radius = radius;
    }

    public double getLatitude() {
        return this.Latitude;
    }

    public double getLongitude() {
        return this.Longitude;
    }

    public double getRadius() {
        return this.Radius;
    }
}
