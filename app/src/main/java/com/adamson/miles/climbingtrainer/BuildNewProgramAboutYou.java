package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuildNewProgramAboutYou extends AppCompatActivity {

    Spinner spinnerGrades;
    Spinner spinnerCommitment;
    String[] gradeStrings;
    String[] commitmentStrings;
    String[] daysStrings;
    Button buttonContinue;

    ImageButton imageHelpGrades;
    ImageButton imageHelpCommitment;
    ImageButton imageHelpDaysSelect;

    CheckBox checkBoxMonday;
    CheckBox checkBoxTuesday;
    CheckBox checkBoxWednesday;
    CheckBox checkBoxThursday;
    CheckBox checkBoxFriday;
    CheckBox checkBoxSaturday;
    CheckBox checkBoxSunday;
    CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_about_you);

        initSpinners();
        initHelp();
        initCheckBoxes();
        buttonContinue = findViewById(R.id.buttonToEquipment);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selectionFail = true;
                if(spinnerGrades.getSelectedItemPosition()!=0){
                    if(spinnerCommitment.getSelectedItemPosition()!=0) {
                        if(checkBoxesClicked()) {
                            selectionFail = false;
                            if(commitmentStrings[spinnerCommitment.getSelectedItemPosition()].equals("Dedicated") && gradeStrings[spinnerGrades.getSelectedItemPosition()].equals("[5.10d or V0 and below]")){
                                Toast.makeText(getApplicationContext(), "You may not create a 'Dedicated' program for [5.10d or V0 and below], only 'Moderate'", Toast.LENGTH_SHORT).show();
                            } else {
                                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                                programBuilder.setCommitmentLevel(commitmentStrings[spinnerCommitment.getSelectedItemPosition()]);
                                programBuilder.setCurrentGrade(gradeStrings[spinnerGrades.getSelectedItemPosition()]);
                                programBuilder.setDaysOfWeek(boxesToArray());
                                startActivity(new Intent(BuildNewProgramAboutYou.this, BuildNewProgramRoot.class));
                            }
                        }
                    }
                }
                if(selectionFail) {
                    Toast.makeText(getApplicationContext(), "You must make a selection before continuing.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_build_new_program_about_you, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(BuildNewProgramAboutYou.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initSpinners(){

        gradeStrings = getResources().getStringArray(R.array.grades);
        spinnerGrades = (Spinner) findViewById(R.id.spinnerGrades);
        ArrayAdapter<String> gradeArray= new ArrayAdapter<>(
                BuildNewProgramAboutYou.this,
                R.layout.spinner_design,
                gradeStrings);
        gradeArray.setDropDownViewResource(R.layout.spinner_my_style);
        spinnerGrades.setAdapter(gradeArray);
        spinnerGrades.setSelection(0);
        if(ProgramBuilder.getInstance().getCurrentGrade()!=null){
            switch (ProgramBuilder.getInstance().getCurrentGrade()){
                case "[5.10d or V0 and below]":
                    spinnerGrades.setSelection(1);
                    break;
                case "[5.11a to 5.11d] or [V1 to V3]":
                    spinnerGrades.setSelection(2);
                    break;
                case "[5.12a to 5.12d] or [V4 to V6]":
                    spinnerGrades.setSelection(3);
                    break;
                case "[5.13a to 5.13d] or [V7 to V9]":
                    spinnerGrades.setSelection(4);
                    break;
                case "[5.14a or V10 and above]":
                    spinnerGrades.setSelection(5);
                    break;
            }
        } else {
            spinnerGrades.setSelection(0);
        }

        commitmentStrings = getResources().getStringArray(R.array.commitment_levels);
        spinnerCommitment = (Spinner) findViewById(R.id.spinnerCommitment);
        ArrayAdapter<String> commitmentArray= new ArrayAdapter<>(
                BuildNewProgramAboutYou.this,
                R.layout.spinner_design,
                commitmentStrings);
        gradeArray.setDropDownViewResource(R.layout.spinner_my_style);
        spinnerCommitment.setAdapter(commitmentArray);
        if(ProgramBuilder.getInstance().getCommitmentLevel()!=null){
            switch (ProgramBuilder.getInstance().getCommitmentLevel()){
                case "Moderate":
                    spinnerCommitment.setSelection(1);
                    break;
                case "Dedicated":
                    spinnerCommitment.setSelection(2);
                    break;
            }
        } else {
            spinnerCommitment.setSelection(0);
        }
    }

    void initHelp(){

        imageHelpGrades = (ImageButton) findViewById(R.id.imageHelpGrades);
        imageHelpGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Select the grade range you fit into best. This controls what drills are allowed to be put into your program.\n\nIf an exercise is too easy or difficult, you can tap and hold on its button while viewing it inside your program to replace it.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramAboutYou.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpCommitment = (ImageButton) findViewById(R.id.imageHelpCommitment);
        imageHelpCommitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Moderate will generate around an hour of planned activites per day. Select this if you want free time to climb, or if your sessions are shorter.\n\n" +
                        "Dedicated will generate completely planned days, all of which are three hours. You should not climb outside of the drills listed, as it could lead to overtraining.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramAboutYou.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpDaysSelect = (ImageButton) findViewById(R.id.imageHelpDaysSelect);
        imageHelpDaysSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Select all the days you want to climb. This also controls how many days per week of will be planned.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramAboutYou.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });
    }

    void initCheckBoxes(){
        checkBoxMonday = (CheckBox)findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = (CheckBox)findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = (CheckBox)findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = (CheckBox)findViewById(R.id.checkBoxThursday);
        checkBoxFriday = (CheckBox)findViewById(R.id.checkBoxFriday);
        checkBoxSaturday = (CheckBox)findViewById(R.id.checkBoxSaturday);
        checkBoxSunday = (CheckBox)findViewById(R.id.checkBoxSunday);

        checkBoxes = new CheckBox[]{
                checkBoxMonday,
                checkBoxTuesday,
                checkBoxWednesday,
                checkBoxThursday,
                checkBoxFriday,
                checkBoxSaturday,
                checkBoxSunday
        };

        daysStrings = getResources().getStringArray(R.array.days_of_week);
        for(int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i].setText(daysStrings[i]);
            if(ProgramBuilder.getInstance().getDaysOfWeek()!=null) {
                checkBoxes[i].setChecked(ProgramBuilder.getInstance().getDaysOfWeek()[i]);
            }
        }
    }

    // Returns true if any check box is checked,
    // false if NONE are
    boolean checkBoxesClicked(){
        for(int i = 0; i < checkBoxes.length; i++){
            if(checkBoxes[i].isChecked()){
                return true;
            }
        }
        return false;
    }

    // Step through the CheckBoxes array and create a boolean
    // array from whether they are checked
    boolean[] boxesToArray(){
        boolean[] array = new boolean[checkBoxes.length];
        for(int i = 0; i < checkBoxes.length; i++){
            if(checkBoxes[i].isChecked()){
                array[i] = true;
            }
        }
        return array;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCheckBoxes();
        initSpinners();
    }
}
