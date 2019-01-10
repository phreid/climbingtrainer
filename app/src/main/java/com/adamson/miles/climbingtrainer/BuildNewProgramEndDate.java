package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildNewProgramEndDate extends AppCompatActivity {

    Button buttonToReview;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_end_date);

        datePicker = (DatePicker)findViewById(R.id.datePickerEndDate);

        buttonToReview = (Button)findViewById(R.id.buttonToReview);
        buttonToReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date endDate = new Date(datePicker.getYear()-1900, datePicker.getMonth(), datePicker.getDayOfMonth());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                ProgramBuilder.getInstance().setEndDateString(sdf.format(endDate));
                // Check if their selection was valid and continue if so and alert if not
                if(ProgramBuilder.getInstance().checkDates()){
                    startActivity(new Intent(BuildNewProgramEndDate.this, BuildNewProgramRoot.class));
                } else {
                    String text = "Route climbing programs must be at least 6 weeks long.\n\n" +
                            "Bouldering programs must be at least 4 weeks long.\n\n" +
                            "Program segments must be at least 1 week long.";
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramEndDate.this);
                    builder.setMessage(text).setCancelable(true).show();
                }

            }
        });

        // Upon entry, remind user how long a program must be.
        String text = "Route climbing programs must be at least 6 weeks long.\n\n" +
                "Bouldering programs must be at least 4 weeks long.\n\n" +
                "Program segments must be at least 1 week long.";
        AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramEndDate.this);
        builder.setMessage(text).setCancelable(true).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_end_date, menu);
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

}
