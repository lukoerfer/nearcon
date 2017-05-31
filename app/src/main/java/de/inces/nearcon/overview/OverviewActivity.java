package de.inces.nearcon.overview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import de.inces.nearcon.R;
import de.inces.nearcon.conversation.ConversationActivity;
import de.inces.nearcon.conversations.Conversation;
import de.inces.nearcon.events.Event;
import de.inces.nearcon.service.DataService;

public class OverviewActivity extends AppCompatActivity {

    private DataService.OverviewBinder service;

    private EventAdapter events;
    private ConversationAdapter conversations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_overview);
        // Initialize adapters
        this.initializeEventList();
        this.initializeConversationList();
        // Bind data service
        this.connectService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(this.serviceConnection);
    }

    private void initializeEventList() {
        this.events = new EventAdapter(this);
        ListView listEvents = (ListView) this.findViewById(R.id.list_events);
        listEvents.setEmptyView(this.findViewById(R.id.txt_no_events));
        listEvents.setAdapter(this.events);
        listEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = events.getItem(position);
                return true;
            }
        });
    }

    private void initializeConversationList() {
        this.conversations = new ConversationAdapter(this);
        ListView listConversations = (ListView) this.findViewById(R.id.list_conversations);
        listConversations.setEmptyView(this.findViewById(R.id.txt_no_conversations));
        listConversations.setAdapter(this.conversations);
        listConversations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conversation conversation = conversations.getItem(position);
                Intent conversationIntent = new Intent(OverviewActivity.this, ConversationActivity.class);
                OverviewActivity.this.startActivity(conversationIntent);
            }
        });
    }

    private void connectService() {
        Intent serviceIntent = new Intent(this, DataService.class)
            .setAction(DataService.OVERVIEW_SERVICE);
        this.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            OverviewActivity.this.service = (DataService.OverviewBinder) service;
            OverviewActivity.this.update();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            OverviewActivity.this.service = null;
        }
    };

    private void update() {
        if (this.service != null) {
            this.events.clear();
            this.events.addAll(this.service.getOwnEvents());
            this.conversations.clear();
            this.conversations.addAll(this.service.getActiveConversations());
        } else {
            this.connectService();
        }
    }

}
