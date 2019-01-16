package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddExercise extends AppCompatActivity {

    EditText editTextName;
    EditText editTextDesc;
    EditText editTextRest;
    EditText editTextSets;
    EditText editTextReps;

    Spinner spinnerTypes;
    Spinner spinnerTimes;

    String[] types;
    String[] grades;
    String[] times;

    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        types = getResources().getStringArray(R.array.types_strings);
        grades = getResources().getStringArray(R.array.grades);
        times = getResources().getStringArray(R.array.time_strings);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Choose a type...");
        for(int i = 0; i < types.length; i++){
            if(i != types.length - 1) {
                strings.add(types[i]);
            }
        }
        types = strings.toArray(new String[types.length]);

        strings = new ArrayList<>();
        strings.add("Choose a time...");
        for(int i = 0; i < times.length; i++){
            strings.add(times[i]);
        }
        times = strings.toArray(new String[times.length+1]);

        editTextName = findViewById(R.id.editTextName);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextRest = findViewById(R.id.editTextRest);
        editTextSets = findViewById(R.id.editTextSets);
        editTextReps = findViewById(R.id.editTextReps);

        spinnerTypes = findViewById(R.id.spinnerTypeSelect);
        ArrayAdapter<String> typesArray = new ArrayAdapter<>(
                AddExercise.this,
                R.layout.spinner_design,
                types);
        typesArray.setDropDownViewResource(R.layout.spinner_my_style);
        spinnerTypes.setAdapter(typesArray);
        spinnerTypes.setSelection(0);

        spinnerTimes = findViewById(R.id.spinnerTimeSelect);
        ArrayAdapter<String> timesArray = new ArrayAdapter<>(
                AddExercise.this,
                R.layout.spinner_design,
                times);
        timesArray.setDropDownViewResource(R.layout.spinner_my_style);
        spinnerTimes.setAdapter(timesArray);
        spinnerTimes.setSelection(0);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more fields is blank.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextDesc.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more fields is blank.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextRest.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more fields is blank.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextSets.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more fields is blank.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextReps.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more fields is blank.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinnerTimes.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "One or more selections left to do.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinnerTypes.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "One or more selections left to do.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!sanitized(editTextName)){
                    Toast.makeText(getApplicationContext(), "The name of the exercise must 25 characters or less, with letters and spaces only.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(hasNewLines(editTextSets)){
                    Toast.makeText(getApplicationContext(), "The sets must be a single line of text (no new line character, aka enter button)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(hasNewLines(editTextReps)){
                    Toast.makeText(getApplicationContext(), "The reps must be a single line of text (no new line character, aka enter button)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(hasNewLines(editTextRest)){
                    Toast.makeText(getApplicationContext(), "The rest must be a single line of text (no new line character, aka enter button)", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(tooLong(10, editTextSets)){
                    Toast.makeText(getApplicationContext(), "The sets can be only 10 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(tooLong(10, editTextReps)){
                    Toast.makeText(getApplicationContext(), "The reps can be only 10 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Exercise e = new Exercise();
                e.setSets(editTextSets.getText().toString())
                        .setName("USER - " + editTextName.getText().toString())
                        .setTime(spinnerTimes.getSelectedItem().toString())
                        .setType(spinnerTypes.getSelectedItem().toString())
                        .setDiff("Any")
                        .setReps(editTextReps.getText().toString())
                        .setDesc(editTextDesc.getText().toString())
                        .setEquip("None")
                        .setRest(editTextRest.getText().toString());

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                if (db.insertExercise(e)) {
                    Intent intent = new Intent(AddExercise.this, ViewExercise.class);
                    intent.putExtra("exercise", e);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to add exercise.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(AddExercise.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // returns true if all characters are letters
    public boolean sanitized(EditText editText){
        char[] chars = editText.getText().toString().toCharArray();

        if(hasNewLines(editText)){
            return false;
        }

        if(chars[0] == '\n' || chars[0] == ' ' || chars[0] == '\r'){
            return false;
        }

        // Must contain only letters and spaces
        for (char c : chars) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        if(chars.length > 25){
            return false;
        }
        return true;
    }

    // returns true if all characters are letters
    public boolean hasNewLines(EditText editText){
        char[] chars = editText.getText().toString().toCharArray();

        // Must contain only letters and spaces
        for (char c : chars) {
            if (c == '\n' || c == '\r') {
                return true;
            }
        }
        return false;
    }

    public boolean tooLong(int length, EditText editText){
        char[] chars = editText.getText().toString().toCharArray();
        if(chars.length > length){
            return true;
        }
        return false;
    }
}
