package de.inces.nearcon.event;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import de.inces.nearcon.R;

public class CreateEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected Spinner spinnerCategory;
    protected EditText editDescription;
    protected Button btnSubmit;
    protected RadioButton btnYourLoc;
    protected RadioButton btnSelectedLoc;

    protected SeekBar seekVisibilityTime;
    protected SeekBar seekVisibilityRadius;
    protected TextView txtVisibilityTime;
    protected TextView txtVisibilityRadius;

    protected int iCategoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.initCategorySpinner();
        this.initDescritionText();
        this.initSubmitButton();
        this.initVisibilityTime();
        this.initVisibilityRadius();
        this.initLocationButtons();

        this.initDefaultValues();
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // Maybe needed if you want to get access to the array
        // Resources res = getResources();
        // String[] cat_array = res.getStringArray(R.array.array_categories);
        Spinner spin = (Spinner)parent;
        if(spin.getId() == R.id.spinner_category)
        {
            this.iCategoryID = pos;
            if(pos==0){
                //TODO Do something with position 1 of the array (sports atm)
            }
        }
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    protected void initCategorySpinner(){
        this.spinnerCategory = (Spinner) findViewById(R.id.spinner_category);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spinnerCategory.setAdapter(adapter);
        this.spinnerCategory.setOnItemSelectedListener(this);
    }

    protected void initDescritionText(){
        this.editDescription = (EditText) findViewById(R.id.edit_description);
    }

    protected void initVisibilityTime(){
        this.seekVisibilityTime = (SeekBar) findViewById(R.id.seek_visibilityTime);
        this.txtVisibilityTime = (TextView) findViewById(R.id.txt_visibilityTime);
        this.seekVisibilityTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Insert Text and put time in variable
                String visibilityTimeText = getString(R.string.str_visibility_time);
                switch (progress) {
                    case 0: visibilityTimeText = visibilityTimeText + " 15 minutes.";
                            break;
                    case 1: visibilityTimeText = visibilityTimeText + " 30 minutes..";
                            break;
                    case 2: visibilityTimeText = visibilityTimeText + " 45 minutes.";
                            break;
                    case 3: visibilityTimeText = visibilityTimeText + " 1 hour.";
                        break;
                    case 4: visibilityTimeText = visibilityTimeText + " 1.5 hours.";
                        break;
                    case 5: visibilityTimeText = visibilityTimeText + " 2 hours.";
                        break;
                    case 6: visibilityTimeText = visibilityTimeText + " 2.5 hours.";
                        break;
                    case 7: visibilityTimeText = visibilityTimeText + " 3 hours.";
                        break;
                    case 8: visibilityTimeText = visibilityTimeText + " 4 hours.";
                        break;
                    case 9: visibilityTimeText = visibilityTimeText + " 5 hours.";
                        break;
                    case 10: visibilityTimeText = visibilityTimeText + " 7 hours.";
                        break;
                    case 11: visibilityTimeText = visibilityTimeText + " 10 hours.";
                        break;
                    default: break;
                }

                txtVisibilityTime.setText(visibilityTimeText);

            }
        });
    }

    protected void initVisibilityRadius(){
        this.seekVisibilityRadius = (SeekBar) findViewById(R.id.seek_visibilityRadius);
        this.txtVisibilityRadius = (TextView) findViewById(R.id.txt_visibilityRadius);
        this.seekVisibilityRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Insert Text and put radius in variable
                String visibilityRadiusText = getString(R.string.str_visibility_radius);
                switch (progress) {
                    case 0: visibilityRadiusText = visibilityRadiusText + " 0.5 km.";
                        break;
                    case 1: visibilityRadiusText = visibilityRadiusText + " 1 km.";
                        break;
                    case 2: visibilityRadiusText = visibilityRadiusText + " 1.5 km.";
                        break;
                    case 3: visibilityRadiusText = visibilityRadiusText + " 2 km.";
                        break;
                    case 4: visibilityRadiusText = visibilityRadiusText + " 3 km.";
                        break;
                    case 5: visibilityRadiusText = visibilityRadiusText + " 4 km.";
                        break;
                    case 6: visibilityRadiusText = visibilityRadiusText + " 5 km.";
                        break;

                    default: break;
                }

                txtVisibilityRadius.setText(visibilityRadiusText);


            }
        });

        this.seekVisibilityRadius.setProgress(0);
    }


    private void initLocationButtons(){

        this.btnYourLoc = (RadioButton) findViewById(R.id.btn_yourLocation);

        //What to do, when the Button is clicked!!
        this.btnYourLoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    btnSelectedLoc.setChecked(false);
                } else {
                }
            }
        });

        this.btnSelectedLoc = (RadioButton) findViewById(R.id.btn_selectedLocation);

        //What to do, when the Button is clicked!!
        this.btnSelectedLoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    btnYourLoc.setChecked(false);
                } else {
                }
            }
        });

    }


    protected void initSubmitButton(){
        this.btnSubmit = (Button) findViewById(R.id.btn_submit);

        this.btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // What to do when submitting
                // Or show warning if you have to do something more

                //first get descriptiontext
                String descText = editDescription.getText().toString();

                //TODO make an if condition for "no test added"
                if (true){
                    Context context = getApplicationContext();
                    CharSequence text = "Please insert an event description.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }

    protected void initDefaultValues(){
        //Set Text for Visibility Radius
        //TODO set default value for it
        this.txtVisibilityRadius.setText(getString(R.string.str_visibility_radius) + " 0.5 km.");
        //Set Text for Visibility Time
        //TODO set default value for it
        this.txtVisibilityTime.setText(getString(R.string.str_visibility_time) + " 15 minutes.");

        //set default location
        //TODO set default value for it
        this.btnSelectedLoc.setChecked(true);

    }


}
