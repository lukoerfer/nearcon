package de.inces.nearcon.users;

public class User {

    private String Id;
    private double Score;

    public User(String id) {
        this.Id = id;
        this.Score = 100;
    }

    public String getId() {
        return Id;
    }

    public double getScore() {
        return Score;
    }
}
