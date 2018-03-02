package de.inces.nearcon.core.model.conversations;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.inces.nearcon.core.model.events.Event;
import de.inces.nearcon.core.model.auth.User;
import lombok.Getter;

@Entity
public class Conversation {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    private Event topic;

    @Getter
    private User requestor;

    @Getter
    private List<Message> messages;

}
