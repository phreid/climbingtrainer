package com.adamson.miles.climbingtrainer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BuildNewProgramStartDate extends AppCompatActivity {

    Button buttonToEndDate;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_start_date);

        datePicker = (DatePicker) findViewById(R.id.datePickerStartDate);

        buttonToEndDate = (Button)findViewById(R.id.buttonToEndDate);
        buttonToEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), 0, 0);
                Date startDate = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                ProgramBuilder.getInstance().setStartDateString(sdf.format(startDate));
                startActivity(new Intent(BuildNewProgramStartDate.this, BuildNewProgramEndDate.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_start_date, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(BuildNewProgramStartDate.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
