package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildNewProgramReview extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;

    static TextView textViewCounter;
    ProgramBuilder pB;
    Button buttonGo;
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

        buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    }
                }).start();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_review, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(BuildNewProgramReview.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void showCount(int num){
        textViewCounter.setText("Inserting Exercise #" + Integer.toString(num));
    }

}
