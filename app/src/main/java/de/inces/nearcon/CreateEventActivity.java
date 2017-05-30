package de.inces.nearcon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected Spinner spinnerCategory;
    protected Spinner test_spinner;
    protected EditText edit_description;
    protected Button submit_button;

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
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        // Maybe needed if you want to get access to the array
        // Resources res = getResources();
        // String[] cat_array = res.getStringArray(R.array.categories_array);
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
                R.array.categories_array, android.R.layout.simple_spinner_item);
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
                R.array.categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.test_spinner.setAdapter(adapter);
        this.test_spinner.setOnItemSelectedListener(this);
    }

    protected void initDescritionText(){
        this.edit_description = (EditText) findViewById(R.id.edit_description);
    }

    protected void initSubmitButton(){
        this.submit_button = (Button) findViewById(R.id.submit_button);

        this.submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // What to do when submitting
                // Or show warning if you have to do something more

                //first get descriptiontext
                String descText = edit_description.getText().toString();

                //TODO make an if condition for
                if (true){
                    Context context = getApplicationContext();
                    CharSequence text = "Please insert an event description." + descText + "...";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });
    }


}
