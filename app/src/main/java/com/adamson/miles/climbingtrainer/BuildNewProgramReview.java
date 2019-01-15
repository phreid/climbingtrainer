package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildNewProgramReview extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    Button button;

    ProgramBuilder pB;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_review);
        pB = ProgramBuilder.getInstance();
        // Builds the training days and creates a table for the program
        pB.buildDatesInProgram(getApplicationContext());

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax((int)pB.getProgramLength());
        progressBar.setScaleY(1.5f);
        button = findViewById(R.id.buttonGo);
        button.setClickable(false);

        new Thread(new Runnable() {
            public void run() {
                while(!pB.isDone()) {
                    progressStatus = pB.buildTrainingDays(getApplicationContext());
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                if(pB.isDone()){
                    //DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    // Write out entire program to text file?
                    /*
                    if(false) {
                        ExerciseAndDate exerciseAndDate = db.selectProgram(pB.getProgramName());
                        try {
                            File file = new File(Environment.getExternalStorageDirectory() + "/test.txt");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            FileWriter writer = new FileWriter(file);
                            for (int i = 0; i < exerciseAndDate.exercises.length; i++) {
                                String content = "";
                                if (!exerciseAndDate.exerciseNames[i].equals("Warm Up")) {
                                    content += exerciseAndDate.dateStrings[i] + "\n";
                                    content += exerciseAndDate.exerciseNames[i] + ", " + exerciseAndDate.exercises[i].time + " Minutes\n";
                                    content += exerciseAndDate.exercises[i].desc + "\n\n";
                                    content += exerciseAndDate.exercises[i].rest + "\n\n";
                                    content += "Reps: " + exerciseAndDate.exercises[i].reps + ". Sets: " + exerciseAndDate.exercises[i].sets + "\n\n";
                                }
                                writer.append(content);
                                writer.flush();
                            }
                            writer.close();
                        } catch (IOException e) {
                        }
                    }
                    */

                    pB.destroyInstance();
                    Intent intent = new Intent(BuildNewProgramReview.this, LoadProgram.class);
                    intent.putExtra("overrideBack", true);
                    startActivity(intent);
                }
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_review, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // Do not allow the user to go back before the program creation is finished
    }



}
