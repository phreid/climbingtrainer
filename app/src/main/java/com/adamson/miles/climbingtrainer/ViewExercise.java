package com.adamson.miles.climbingtrainer;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExercise extends AppCompatActivity {

    TextView textViewName;
    TextView textViewDesc;
    TextView textViewRest;
    TextView textViewSets;
    TextView textViewReps;
    TextView textViewGrade;
    TextView textViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        textViewReps = (TextView)findViewById(R.id.textViewReps);
        textViewRest = (TextView)findViewById(R.id.textViewRest);
        textViewSets = (TextView)findViewById(R.id.textViewSets);
        textViewType = (TextView)findViewById(R.id.textViewType);
        textViewGrade = (TextView)findViewById(R.id.textViewGrade);
        textViewName = (TextView)findViewById(R.id.textViewName);
        textViewDesc = (TextView)findViewById(R.id.textViewDesc);

        Exercise exercise = (Exercise) getIntent().getSerializableExtra("exercise");

        String rest = getResources().getString(R.string.rest) + "\n"  + exercise.rest;
        textViewRest.setText(rest);
        textViewRest.setMovementMethod(new ScrollingMovementMethod());

        String sets = getResources().getString(R.string.sets) + " " + exercise.sets;
        textViewSets.setText(sets);

        String reps = getResources().getString(R.string.reps) + " " + exercise.reps;
        textViewReps.setText(reps);

        String diff = getResources().getString(R.string.diff) + "\n" + exercise.diff;
        textViewGrade.setText(diff);

        String typeTime = getResources().getString(R.string.type) + " " + exercise.type;
        typeTime += "\n" + getResources().getString(R.string.time) + " " + exercise.time;
        typeTime += "\n" + getResources().getString(R.string.equip) + " " + exercise.equip;
        textViewType.setText(typeTime);

        textViewName.setText(exercise.name);
        setTextViewBG(textViewName, exercise);

        textViewDesc.setText(exercise.desc);
        textViewDesc.setMovementMethod(new ScrollingMovementMethod());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_exercise, menu);
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

    void setTextViewBG(TextView textView, Exercise exercise) {
        Drawable[] drawables= new Drawable[]{
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_red),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_indigo),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_orange),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_blue),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_yellow),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_pink)
        };

        switch (exercise.type){
            case "Volume":
                textView.setBackground(drawables[0]);
                break;
            case "Strength":
                textView.setBackground(drawables[1]);
                break;
            case "Power":
                textView.setBackground(drawables[2]);
                break;
            case "Power Endurance":
                textView.setBackground(drawables[3]);
                break;
            case "Endurance":
                textView.setBackground(drawables[4]);
                break;
            case "Conditioning":
                textView.setBackground(drawables[5]);
                break;
        }
    }

}
