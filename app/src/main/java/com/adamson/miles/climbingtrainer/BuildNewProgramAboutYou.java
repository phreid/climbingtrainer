package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuildNewProgramAboutYou extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    Spinner spinnerGrades;
    Spinner spinnerDurations;
    Spinner spinnerSessionsPerWeek;
    Spinner spinnerCommitment;
    String[] gradeStrings;
    String[] durationStrings;
    String[] sessionStrings;
    String[] commitmentStrings;
    Button buttonContinue;

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
        buttonContinue = (Button)findViewById(R.id.buttonToEquipment);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean passed = false;
                if(spinnerGrades.getSelectedItemPosition()!=0){
                    if(spinnerDurations.getSelectedItemPosition()!=0){
                        if(spinnerSessionsPerWeek.getSelectedItemPosition()!=0){
                            if(spinnerCommitment.getSelectedItemPosition()!=0) {
                                passed = true;
                                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                                programBuilder.setCommitmentLevel(commitmentStrings[spinnerCommitment.getSelectedItemPosition()]);
                                programBuilder.setCurrentGrade(gradeStrings[spinnerGrades.getSelectedItemPosition()]);
                                programBuilder.setSessionDuration(durationStrings[spinnerDurations.getSelectedItemPosition()]);
                                programBuilder.setSessionsPerWeek(sessionStrings[spinnerSessionsPerWeek.getSelectedItemPosition()]);
                                startActivity(new Intent(BuildNewProgramAboutYou.this, BuildNewProgramEquipment.class));
                            }
                        }
                    }
                }
                if (!passed) {
                    Help.helpAlert(R.id.buttonToEquipment, BuildNewProgramAboutYou.this);
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

        durationStrings = getResources().getStringArray(R.array.session_durations);
        spinnerDurations = (Spinner) findViewById(R.id.spinnerDurations);
        ArrayAdapter<String> durationsArray= new ArrayAdapter<>(
                BuildNewProgramAboutYou.this,
                R.layout.spinner_design,
                durationStrings);
        gradeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDurations.setAdapter(durationsArray);
        spinnerDurations.setSelection(0);

        sessionStrings = getResources().getStringArray(R.array.sessions_per_week);
        spinnerSessionsPerWeek = (Spinner) findViewById(R.id.spinnerSessionsPerWeek);
        ArrayAdapter<String> sessionsArray= new ArrayAdapter<>(
                BuildNewProgramAboutYou.this,
                R.layout.spinner_design,
                sessionStrings);
        gradeArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSessionsPerWeek.setAdapter(sessionsArray);
        spinnerSessionsPerWeek.setSelection(0);

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

}
