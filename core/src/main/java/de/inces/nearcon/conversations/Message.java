package de.inces.nearcon.conversations;

import java.util.Calendar;

import de.inces.nearcon.users.User;

public class Message {

    private Calendar Timestamp;

    private User Sender;

    private String Content;

    public Calendar getTimestamp() {
        return this.Timestamp;
    }

    public User getSender() {
        return this.Sender;
    }

    public String getContent() {
        return this.Content;
    }
}