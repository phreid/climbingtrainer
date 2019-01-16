package com.adamson.miles.climbingtrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ViewDatesExercises extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout scrollLayoutChild;
    String dateString;
    String programName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dates_exercises);

        scrollView = (ScrollView)findViewById(R.id.scrollViewDatesExercises);
        scrollLayoutChild = (LinearLayout)findViewById(R.id.scrollViewDatesExercisesChild);
        dateString = getIntent().getStringExtra("dateString");
        programName = getIntent().getStringExtra("programName");
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        final ExerciseAndDate exerciseAndDate = db.selectFromProgramByDate(programName, dateString);

        // creates button for each exercise depending on the type given to the
        // activity's intent. Selects all if no type given
        for(int i = 0; i < exerciseAndDate.exerciseNames.length; i++){
            LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent),null, 0);

            Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
            button.setTextColor(getResources().getColor(R.color.colorBlack));
            final CheckBox checkBox = new CheckBox(getApplicationContext());

            checkBox.setBackground(getResources().getDrawable(R.drawable.gradient_blue));
            float pixels =  60 * getApplicationContext().getResources().getDisplayMetrics().density;

            LinearLayout.LayoutParams lpButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) pixels, 1.0f);
            LinearLayout.LayoutParams lpCheckbox = new LinearLayout.LayoutParams((int) pixels, (int) pixels);

            button.setLayoutParams(lpButton);
            checkBox.setLayoutParams(lpCheckbox);

            // Create the buttons text and onClickListener, which takes the user to the exercise
            // viewing activity with an intent extra with the exercise
            final String exerciseName = exerciseAndDate.exerciseNames[i];
            final String exerciseString = exerciseName + "\n(" + exerciseAndDate.exercises[i].time + ")";
            final Exercise exercise = db.selectExerciseByName(exerciseName);
            button.setText(exerciseString);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewDatesExercises.this, ViewExercise.class);
                    intent.putExtra("exercise", exercise);
                    startActivity(intent);
                }
            });
            if(!exerciseName.equals("Warm Up")) {
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            builder = new AlertDialog.Builder(ViewDatesExercises.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(ViewDatesExercises.this);
                        }
                        builder.setMessage(getResources().getString(R.string.replace_warning));
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ViewDatesExercises.this, ExerciseList.class);
                                intent.putExtra("exercise", exercise);
                                intent.putExtra("programName", programName);
                                dialog.dismiss();
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        return false;
                    }
                });
            }
            layoutHorizontal.addView(button);

            // Check from the database if this exercise is done, and set the checkbox accordingly
            checkBox.setChecked(db.isCompleted(dateString, exerciseName, programName));
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()){
                        new DatabaseHelper(getApplicationContext()).completeExercise(dateString, exerciseName, programName);
                    } else {
                        new DatabaseHelper(getApplicationContext()).uncompleteExercise(dateString, exerciseName, programName);
                    }
                }
            });

            layoutHorizontal.addView(checkBox);
            scrollLayoutChild.addView(layoutHorizontal);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_dates_exercises, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_help) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.help_view_dates_exercises), Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_home) {
            startActivity(new Intent(ViewDatesExercises.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
