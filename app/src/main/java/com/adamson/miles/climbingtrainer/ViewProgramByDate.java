package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ViewProgramByDate extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout scrollLayoutChild;
    String programName;
    String weekName;
    boolean onPauseCalled = false;
    boolean hideCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_program_by_date);

        hideCompleted = getIntent().getBooleanExtra("hideCompleted", false);
        scrollView = findViewById(R.id.scrollViewDates);
        scrollLayoutChild = findViewById(R.id.scrollViewDatesChild);
        programName = getIntent().getStringExtra("programName");
        weekName = getIntent().getStringExtra("weekName");

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        ExerciseAndDate exerciseAndDate = db.selectFromProgramByWeek(programName, weekName);

        // creates button for each exercise depending on the type given to the
        // activity's intent. Selects all if no type given
        for(int i = 0; i < exerciseAndDate.uniqueDates.length; i++) {


            // Check if this days exercises are all done. If so, check the checkbox
            final String dateString = exerciseAndDate.uniqueDates[i];
            ExerciseAndDate e = db.selectFromProgramByDate(programName, dateString);
            boolean doneAll = true;
            for (int j = 0; j < e.exercises.length; j++) {
                if (!db.isCompleted(dateString, e.exerciseNames[j], programName)) {
                    doneAll = false;
                }
            }

            // If hideCompleted is true and this day is done, do not create a layout for the day
            if (hideCompleted && doneAll) {

            } else {
                LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent), null, 0);

                Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
                final CheckBox checkBox = new CheckBox(getApplicationContext());
                checkBox.setClickable(false);

                checkBox.setBackground(getApplicationContext().getDrawable(R.drawable.gradient_blue));
                float pixels = 40 * getApplicationContext().getResources().getDisplayMetrics().density;

                LinearLayout.LayoutParams lpButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) pixels, 1.0f);
                LinearLayout.LayoutParams lpCheckbox = new LinearLayout.LayoutParams((int) pixels, (int) pixels);

                button.setLayoutParams(lpButton);
                checkBox.setLayoutParams(lpCheckbox);

                String s = exerciseAndDate.uniqueDatesDayOfWeek[i] + ", " + exerciseAndDate.uniqueDates[i] + ". " + exerciseAndDate.uniqueDatesType[i];
                button.setText(s);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ViewProgramByDate.this, ViewDatesExercises.class);
                        intent.putExtra("programName", programName);
                        intent.putExtra("dateString", dateString);
                        startActivity(intent);
                    }
                });

                if (doneAll) {
                    checkBox.setChecked(true);
                }

                // Add the views to the layout
                layoutHorizontal.addView(button);
                layoutHorizontal.addView(checkBox);
                scrollLayoutChild.addView(layoutHorizontal);
            }


        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_program_by_date, menu);
        return true;
    }

    // Re-load activity to refresh check boxes every time activity opens
    // if the activity has been paused before
    @Override
    public void onResume(){
        super.onResume();
        if(onPauseCalled) {
            finish();
            startActivity(getIntent());
        }
    }

    // Used for re-loading activity
    @Override
    public void onPause(){
        super.onPause();
        onPauseCalled = true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_hideCompleted) {
            if(hideCompleted){
                getIntent().putExtra("hideCompleted", false);
                finish();
                startActivity(getIntent());
            } else {
                getIntent().putExtra("hideCompleted", true);
                finish();
                startActivity(getIntent());
            }
            return true;
        }

        if (id == R.id.action_home) {
            startActivity(new Intent(ViewProgramByDate.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Only allow back button to navigate to load program screen if overrideBack set
    @Override
    public void onBackPressed() {
        if(getIntent().getBooleanExtra("overrideBack", false)){
            Intent intent = new Intent(ViewProgramByDate.this, LoadProgram.class);
            intent.putExtra("overrideBack", true);
            startActivity(intent);
        } else {
            ViewProgramByDate.super.onBackPressed();
        }
    }

}
