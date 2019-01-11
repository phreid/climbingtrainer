package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonBuildNewProgram;
    Button buttonLoadProgram;
    Button buttonTrainingTips;
    Button buttonAboutTheAuthor;
    Button buttonExercises;
    Button buttonTimerCounter;

    ImageButton imageBuildNewProgram;
    ImageButton imageLoadProgram;
    ImageButton imageTips;
    ImageButton imageAboutTheAuthor;
    ImageButton imageExercises;
    ImageButton imageTimerCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExerciseBuilder e = new ExerciseBuilder(this);

        buttonBuildNewProgram = findViewById(R.id.buttonBuildNewProgram);
        buttonLoadProgram = findViewById(R.id.buttonLoadProgram);
        buttonTrainingTips = findViewById(R.id.buttonTrainingTips);
        buttonAboutTheAuthor = findViewById(R.id.buttonAboutTheAuthor);
        buttonExercises = findViewById(R.id.buttonExercises);
        buttonTimerCounter = findViewById(R.id.buttonTimerCounter);

        imageBuildNewProgram = findViewById(R.id.imageBuildNewProgram);
        imageLoadProgram = findViewById(R.id.imageLoadProgram);
        imageTips = findViewById(R.id.imageTips);
        imageAboutTheAuthor = findViewById(R.id.imageAboutTheAuthor);
        imageExercises = findViewById(R.id.imageExercises);
        imageTimerCounter = findViewById(R.id.imageTimerCounter);

        buttonBuildNewProgram.setOnClickListener(navigateTo(BuildNewProgramRoot.class));
        buttonAboutTheAuthor.setOnClickListener(navigateTo(AboutTheAuthor.class));
        buttonTrainingTips.setOnClickListener(navigateTo(TrainingTips.class));
        buttonExercises.setOnClickListener(navigateTo(ExerciseList.class));
        buttonLoadProgram.setOnClickListener(navigateTo(LoadProgram.class));
        buttonTimerCounter.setOnClickListener(navigateTo(TimerCounter.class));

        imageBuildNewProgram.setOnClickListener(navigateTo(BuildNewProgramRoot.class));
        imageAboutTheAuthor.setOnClickListener(navigateTo(AboutTheAuthor.class));
        imageTips.setOnClickListener(navigateTo(TrainingTips.class));
        imageExercises.setOnClickListener(navigateTo(ExerciseList.class));
        imageLoadProgram.setOnClickListener(navigateTo(LoadProgram.class));
        imageTimerCounter.setOnClickListener(navigateTo(TimerCounter.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener navigateTo(Class<?> destination){
        final Intent intent = new Intent(MainActivity.this, destination);
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        };
    }

}
