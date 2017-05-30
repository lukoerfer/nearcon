package de.inces.nearby.conversations;

import java.util.Calendar;

import de.inces.nearby.users.User;

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
