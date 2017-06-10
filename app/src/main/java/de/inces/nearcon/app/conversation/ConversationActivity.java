package de.inces.nearcon.app.conversation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import de.inces.nearcon.core.model.conversations.Message;
import de.inces.nearcon.R;
import de.inces.nearcon.app.service.DataService;
import de.inces.nearcon.core.model.users.User;

public class ConversationActivity extends AppCompatActivity {

    private DataService.ConversationBinder service;
    private MessageAdapter messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_conversation);
        this.initializeMessages();
        this.initializeSendUnit();
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
        this.unbindService(serviceConnection);
    }

    private void initializeMessages() {
        this.messages = new MessageAdapter(this, new User("test1"));
        ((ListView) this.findViewById(R.id.list_messages)).setAdapter(this.messages);
    }

    private void initializeSendUnit() {
        final EditText editMessage = (EditText) findViewById(R.id.edit_message);
        final Button btnSend = (Button) findViewById(R.id.btn_send);
        editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSend.setEnabled(s.length() > 0);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString();
                messages.add(new Message(new User("test1"), message));
                editMessage.setText("");
            }
        });
    }

    private void connectService() {
        Intent serviceIntent = new Intent(this, DataService.class)
            .setAction(DataService.CONVERSATION_SERVICE);
        this.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ConversationActivity.this.service = (DataService.ConversationBinder) service;
            ConversationActivity.this.update();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            ConversationActivity.this.service = null;
        }
    };

    private void update() {
        if (service != null) {
            this.messages.clear();
            this.messages.addAll(service.getMessages(null));
        } else {
            this.connectService();
        }
    }

}
