package de.inces.nearcon.map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.List;

import de.inces.nearcon.event.CreateEventActivity;
import de.inces.nearcon.R;
import de.inces.nearcon.events.Event;
import de.inces.nearcon.overview.OverviewActivity;
import de.inces.nearcon.service.DataService;

public class EventMapActivity extends AppCompatActivity {

    private DataService.MapBinder service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        EventMapFragment mapFragment = (EventMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_map);
        FloatingActionButton newEventButton = (FloatingActionButton) findViewById(R.id.fab_newEvent);
        newEventButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                openCreateEventActivity();
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_overview_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, OverviewActivity.class);
        startActivity(intent);
        return true;
    }

    public void openCreateEventActivity(){
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    private void connectService() {
        Intent serviceIntent = new Intent(this, DataService.class)
            .setAction(DataService.MAP_SERVICE);
        this.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            EventMapActivity.this.service = (DataService.MapBinder) service;
            EventMapActivity.this.update();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            EventMapActivity.this.service = null;
        }
    };

    private void update() {
        if (service != null) {
            List<Event> events = service.searchEvents();
        } else {
            this.connectService();
        }
    }
}
