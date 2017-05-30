package de.inces.nearcon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected Spinner spinnerCategory;
    protected Spinner test_spinner;
    protected EditText edit_description;
    protected Button submit_button;

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
        this.initTestSpinner();
        this.initDescritionText();
        this.initSubmitButton();
        this.initVisibilityTime();
        this.initVisibilityRadius();
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // Maybe needed if you want to get access to the array
        // Resources res = getResources();
        // String[] cat_array = res.getStringArray(R.array.array_categories);
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;
        TextView t=(TextView)findViewById(R.id.textviewtest);
        if(spin.getId() == R.id.category_spinner)
        {
            this.iCategoryID = pos;
            if(pos==0){
                //TODO Do something with position 1 of the array (sports atm)
            }

        }
        if(spin2.getId() == R.id.test_spinner)
        {

        }
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    protected void initCategorySpinner(){
        this.spinnerCategory = (Spinner) findViewById(R.id.category_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spinnerCategory.setAdapter(adapter);
        this.spinnerCategory.setOnItemSelectedListener(this);
    }

    protected void initTestSpinner(){
        this.test_spinner = (Spinner) findViewById(R.id.test_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.test_spinner.setAdapter(adapter);
        this.test_spinner.setOnItemSelectedListener(this);
    }

    protected void initDescritionText(){
        this.edit_description = (EditText) findViewById(R.id.edit_description);
    }

    protected void initVisibilityTime(){
        this.seekVisibilityTime = (SeekBar) findViewById(R.id.seek_visibilityTime);
        this.txtVisibilityTime = (TextView) findViewById(R.id.txt_visibilityTime);
        this.txtVisibilityTime.setText(getString(R.string.str_visibility_time) + "30 minutes.");
        //TODO set default!!!!
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
                    case 0: visibilityTimeText = visibilityTimeText + "30 minutes.";
                            break;
                    case 1: visibilityTimeText = visibilityTimeText + "1 hour.";
                            break;
                    case 2: visibilityTimeText = visibilityTimeText + "2 hours.";
                            break;
                    case 3: visibilityTimeText = visibilityTimeText + "3 hours.";
                        break;
                    case 4: visibilityTimeText = visibilityTimeText + "5 hours.";
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
        this.txtVisibilityRadius.setText(getString(R.string.str_visibility_radius) + "0.5 km.");
        //TODO set default!!!!
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
                    case 0: visibilityRadiusText = visibilityRadiusText + "0.5 km.";
                        break;
                    case 1: visibilityRadiusText = visibilityRadiusText + "1 km.";
                        break;
                    case 2: visibilityRadiusText = visibilityRadiusText + "2 km.";
                        break;
                    case 3: visibilityRadiusText = visibilityRadiusText + "5 km.";
                        break;

                    default: break;
                }

                txtVisibilityRadius.setText(visibilityRadiusText);


            }
        });

        this.seekVisibilityRadius.setProgress(0);
    }


    protected void initSubmitButton(){
        this.submit_button = (Button) findViewById(R.id.btn_submit);

        this.submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // What to do when submitting
                // Or show warning if you have to do something more

                //first get descriptiontext
                String descText = edit_description.getText().toString();

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

}
