package de.inces.nearcon.app.event;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import de.inces.nearcon.R;
import de.inces.nearcon.app.data.EventCategoryProvider;
import de.inces.nearcon.app.data.EventIconProvider;
import de.inces.nearcon.core.model.events.EventCategory;
import de.inces.nearcon.app.service.DataService;
import de.inces.nearcon.data.EventCategoryProvider;
import de.inces.nearcon.data.EventIconProvider;
import de.inces.nearcon.events.Event;
import de.inces.nearcon.events.EventCategory;
import de.inces.nearcon.events.EventIcon;
import de.inces.nearcon.events.EventLocation;
import de.inces.nearcon.map.EventMapActivity;
import de.inces.nearcon.backend.DataService;
import de.inces.nearcon.users.User;

public class CreateEventActivity extends AppCompatActivity {

    private DataService.CreateEventBinder service;

    private EventCategoryProvider categoryProvider;
    private EventIconProvider iconProvider;

    private ArrayAdapter<EventCategory> categories;
    private IconAdapter icons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_create_event);
        this.categoryProvider = new EventCategoryProvider();
        this.iconProvider = new EventIconProvider();
        // Initialize settings views
        this.initializeIcons();
        this.initializeCategories();
        this.initializeActiveTime();
        this.initializeVisibilityRadius();
        this.initializeSubmit();
        // Connect service
        this.connectService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(this.serviceConnection);
    }

    private void connectService() {
        Intent serviceIntent = new Intent(this, DataService.class)
            .setAction(DataService.CREATE_EVENT_SERVICE);
        this.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CreateEventActivity.this.service = (DataService.CreateEventBinder) service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            CreateEventActivity.this.service = null;
        }
    };

    private void initializeIcons() {
        icons = new IconAdapter(this);
        GridView gridIcons = (GridView) findViewById(R.id.grid_icons);
        gridIcons.setAdapter(icons);
        gridIcons.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.setSelection(position);
                selectedIconID = position;
            }

        });
    };

    private int selectedIconID;

    private void initializeCategories() {
        categories = new ArrayAdapter<EventCategory>(this,
            android.R.layout.simple_spinner_item, this.categoryProvider.getCategories());
        categories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        spinnerCategory.setAdapter(categories);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EventCategory category = categories.getItem(position);
                icons.clear();
                icons.addAll(iconProvider.getIconsByCategory(category.getId()));
                ((GridView) findViewById(R.id.grid_icons)).setSelection(0);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        spinnerCategory.setSelection(0);
    }

    private void initializeActiveTime(){
        SeekBar seekActiveTime = (SeekBar) findViewById(R.id.seek_visibilityTime);
        final TextView txtActiveTime = (TextView) findViewById(R.id.txt_visibilityTime);
        seekActiveTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private String startText = "The event will be active for ";
            private String[] timeTexts = new String[] { "15", "30", "45", "60", "1.5", "2", "2.5", "3", "4", "5", "7" };
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String unitText = progress <= 3 ? " minutes." : " hours.";
                String text = startText + timeTexts[progress] + unitText;
                txtActiveTime.setText(text);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
        });
        seekActiveTime.setProgress(5);
    }

    private void initializeVisibilityRadius(){
        SeekBar seekVisibilityRadius = (SeekBar) findViewById(R.id.seek_visibilityRadius);
        final TextView txtVisibilityRadius = (TextView) findViewById(R.id.txt_visibilityRadius);
        seekVisibilityRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private String startText = "The event well be shown to users in a ";
            private String[] radiusTexts = new String[] { "0.5", "1", "1.5", "2", "3", "4", "5" };
            private String endText = " km radius.";
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String text = startText + radiusTexts[progress] + endText;
                txtVisibilityRadius.setText(text);
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
        });
        seekVisibilityRadius.setProgress(4);
    }

    private void initializeSubmit() {
        final Button btnSubmit = (Button) findViewById(R.id.btn_submit);
        final GridView gridIcons = (GridView) findViewById(R.id.grid_icons);
        final TextView editDescription = (EditText) findViewById(R.id.edit_description);
        editDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSubmit.setEnabled(s.length() > 0);
            }
            @Override
            public void afterTextChanged(Editable s) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.createEvent(new Event(new User("user1"), icons.getItem(selectedIconID),
                    editDescription.getText().toString(), new EventLocation(50.768259, 6.090921, 0.0)));
                CreateEventActivity.this.onBackPressed();
            }
        });
    }

}
