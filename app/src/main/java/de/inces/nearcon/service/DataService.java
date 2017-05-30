package de.inces.nearcon.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import de.inces.nearcon.conversations.Conversation;
import de.inces.nearcon.conversations.Message;
import de.inces.nearcon.events.Event;
import de.inces.nearcon.users.User;

public class DataService extends Service {

    public static final String MAP_SERVICE = "MAP";
    public static final String CREATE_EVENT_SERVICE = "CREATE_EVENT";
    public static final String OVERVIEW_SERVICE = "OVERVIEW";
    public static final String CONVERSATION_SERVICE = "CONVERSATION";

    private User currentUser;

    @Override
    public void onCreate() {
        this.currentUser = new User("test1");
    }

    public class MapBinder extends Binder {

        public List<Event> searchEvents() {
            return null;
        }

    }

    public class CreateEventBinder extends Binder {

        public void createEvent(Event event) {

        }

    }

    public class OverviewBinder extends Binder {

        public User getUser() {
            return DataService.this.currentUser;
        }

        public List<Event> getOwnEvents() {
            List<Event> events = new ArrayList<Event>();
            events.add(new Event());
            events.add(new Event());
            return events;
        }

        public List<Conversation> getActiveConversations() {
            return new ArrayList<Conversation>();
        }
    }

    public class ConversationBinder extends Binder {

        public List<Message> getMessages(Conversation conversation) {
            return null;
        }

    }

    private MapBinder mapBinder = new MapBinder();
    private CreateEventBinder createEventBinder = new CreateEventBinder();
    private OverviewBinder overviewBinder = new OverviewBinder();
    private ConversationBinder conversationBinder = new ConversationBinder();

    @Override
    public IBinder onBind(Intent intent) {
        switch (intent.getAction()) {
            case MAP_SERVICE:
                return this.mapBinder;
            case CREATE_EVENT_SERVICE:
                return this.createEventBinder;
            case OVERVIEW_SERVICE:
                return this.overviewBinder;
            case CONVERSATION_SERVICE:
                return this.conversationBinder;
            default:
                return null;
        }
    }
}
