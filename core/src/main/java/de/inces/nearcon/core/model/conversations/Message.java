package de.inces.nearcon.core.model.conversations;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.inces.nearcon.core.model.auth.User;
import lombok.Getter;

@Entity
public class Message {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    private Conversation conversation;

    @Getter
    private User sender;

    @Getter
    private Calendar timestamp;

    @Getter
    private String content;

    public Message(User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

}
