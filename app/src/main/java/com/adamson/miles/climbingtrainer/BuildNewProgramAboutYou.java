package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

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

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        initSpinners();
        initHelp();
        initCheckBoxes();
        buttonContinue = (Button)findViewById(R.id.buttonToEquipment);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean passed = false;
                if(spinnerGrades.getSelectedItemPosition()!=0){
                    if(spinnerCommitment.getSelectedItemPosition()!=0) {
                        if(checkBoxesClicked()) {
                            passed = true;
                            ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                            programBuilder.setCommitmentLevel(commitmentStrings[spinnerCommitment.getSelectedItemPosition()]);
                            programBuilder.setCurrentGrade(gradeStrings[spinnerGrades.getSelectedItemPosition()]);
                            programBuilder.setDaysOfWeek(boxesToArray());
                            startActivity(new Intent(BuildNewProgramAboutYou.this, BuildNewProgramRoot.class));
                        }
                    }
                }
                if (!passed) {
                    String text = "You must make a selection before continuing.";
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramAboutYou.this);
                    builder.setMessage(text).setCancelable(true).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_about_you, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
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
        gradeArray.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGrades.setAdapter(gradeArray);
        spinnerGrades.setSelection(0);

        commitmentStrings = getResources().getStringArray(R.array.commitment_levels);
        spinnerCommitment = (Spinner) findViewById(R.id.spinnerCommitment);
        ArrayAdapter<String> commitmentArray= new ArrayAdapter<>(
                BuildNewProgramAboutYou.this,
                R.layout.spinner_design,
                commitmentStrings);
        gradeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommitment.setAdapter(commitmentArray);
        spinnerCommitment.setSelection(0);
    }

    void initHelp(){

        imageHelpGrades = (ImageButton) findViewById(R.id.imageHelpGrades);
        imageHelpGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Select the grade range you fit into best. This controls what drills are allowed to be put into your program.\n\nFor example, you must select at least [5.11a to 5.11d] or [V1 to V3] for hangboard drills to be selected.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramAboutYou.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpCommitment = (ImageButton) findViewById(R.id.imageHelpCommitment);
        imageHelpCommitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Casual will generate 45 minutes of planned activites per day. Select this if you still wish to have lots of free time at the gym.\n\n" +
                        "Moderate will generate 1.5 hours of planned activites per day. You will have some free time.\n\n" +
                        "Dedicated will generate completely planned days. You should not climb outside of the drills listed, as it could lead to overtraining.";
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
        }
    }

    // Returns true if any check box is checked,
    // false if none are
    boolean checkBoxesClicked(){
        for(int i = 0; i < checkBoxes.length; i++){
            if(checkBoxes[i].isChecked()){
                return true;
            }
        }
        return false;
    }

    boolean[] boxesToArray(){
        boolean[] array = new boolean[checkBoxes.length];
        for(int i = 0; i < checkBoxes.length; i++){
            if(checkBoxes[i].isChecked()){
                array[i] = true;
            }
        }
        return array;
    }
}
