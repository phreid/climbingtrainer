package com.adamson.miles.climbingtrainer;


import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TimerCounter extends AppCompatActivity {

    Exercise exercise;
    TextView textViewRepGoal;
    TextView textViewSetGoal;
    TextView textViewReps;
    TextView textViewSets;
    TextView textViewDescription;

    ChronometerMilli chronometerMilli;

    Button buttonStart;
    Button buttonPause;
    Button buttonReset;
    Button buttonRep;
    Button buttonSet;

    int reps = 0;
    int sets = 0;

    long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_counter);

        // Don't let their phone sleep while using the timer
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        exercise = (Exercise) getIntent().getSerializableExtra("exercise");

        textViewRepGoal = findViewById(R.id.textViewRepGoal);
        textViewSetGoal = findViewById(R.id.textViewSetGoal);
        textViewReps = findViewById(R.id.textViewRepCurrent);
        textViewSets = findViewById(R.id.textViewSetCurrent);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescription.setMovementMethod(new ScrollingMovementMethod());

        if(exercise != null) {
            textViewRepGoal.setText(textViewRepGoal.getText().toString() + " " + exercise.reps);
            textViewSetGoal.setText(textViewSetGoal.getText().toString() + " " + exercise.sets);
            textViewDescription.setText(exercise.desc);
        } else {
            textViewRepGoal.setText(getResources().getString(R.string.na));
            textViewSetGoal.setText(getResources().getString(R.string.na));
            textViewDescription.setText(getResources().getString(R.string.empty_description));
        }

        buttonStart = findViewById(R.id.buttonStart);
        buttonPause = findViewById(R.id.buttonPause);
        buttonReset = findViewById(R.id.buttonReset);
        buttonRep = findViewById(R.id.buttonRep);
        buttonSet = findViewById(R.id.buttonSet);

        chronometerMilli = (ChronometerMilli)findViewById(R.id.textViewTime);

        buttonRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reps++;
                textViewReps.setText(Integer.toString(reps));
            }
        });

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sets++;
                reps = 0;
                textViewReps.setText(Integer.toString(reps));
                textViewSets.setText(Integer.toString(sets));
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerMilli.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometerMilli.start();
                buttonStart.setEnabled(false);
                buttonPause.setEnabled(true);
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeWhenStopped = chronometerMilli.getBase() - SystemClock.elapsedRealtime();
                buttonStart.setEnabled(true);
                chronometerMilli.stop();
                buttonPause.setEnabled(false);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerMilli.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                buttonStart.setEnabled(true);
                chronometerMilli.stop();
                chronometerMilli.setText(getResources().getString(R.string.zero_time));
            }
        });

    }
}
