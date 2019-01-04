package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildNewProgramReview extends AppCompatActivity {

    TextView textViewReview;
    TextView textViewDate;
    TextView textViewDayOfWeek;
    TextView textViewType;
    Button buttonGenerate;
    EditText editTextName;
    ProgramBuilder pB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_review);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        pB = ProgramBuilder.getInstance();
        initReviewText();
        editTextName = (EditText)findViewById(R.id.editTextName);
        buttonGenerate = (Button)findViewById(R.id.buttonGenerateProgram);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkName()){
                   DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                   Boolean result = db.insertProgram(pB.getTrainingDays(), editTextName.getText().toString());
                   if(!result){
                       Toast.makeText(getApplicationContext(), "Program Creation Failed", Toast.LENGTH_SHORT).show();
                   } else {
                       pB.destroyInstance();
                       startActivity(new Intent(BuildNewProgramReview.this, LoadProgram.class));
                   }
               }
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initReviewText(){
        textViewReview = (TextView) findViewById(R.id.textViewReview);
        String[] reviewSegments = getResources().getStringArray(R.array.review_segments);
        ProgramBuilder programBuilder = ProgramBuilder.getInstance();

        String s = reviewSegments[0] + " " + pB.getProgramType() + "\n\n";
      //  s = s + reviewSegments[1] + " " +  pB.getSessionDuration() + "\n\n";
       // s = s + reviewSegments[2] + " " +  pB.getSessionsPerWeek() + "\n\n";
        s = s + reviewSegments[3] + " " +  pB.getCommitmentLevel() + "\n\n";
        s = s + reviewSegments[4] + " " +  pB.getCurrentGrade() + "\n\n";
        s = s + reviewSegments[5] + " " +  pB.getStartDateString() + "\n\n";
        s = s + reviewSegments[6] + " " +  pB.getEndDateString() + "\n\n";
        s = s + "Program Length: " + Long.toString(pB.getProgramLength()) + " Days\n\n";
        s = s + reviewSegments[7] + "\n";

        String[] equipment = getResources().getStringArray(R.array.equipment);
        boolean[] equipmentArray = pB.getEquipmentAvailable();
        for(int i = 0; i < equipment.length; i++){
            if(equipmentArray[i]){
                s = s + equipment[i];

                // If this is not the last item of equipment, add a comma
                boolean putComma = false;
                for(int j = i + 1; j < equipmentArray.length; j++){
                    if(equipmentArray[j]){
                        putComma = true;
                    }
                }
                if(putComma){
                    s = s + ", ";
                }
            }
        }

        textViewReview.setText(s);

        textViewDate = (TextView)findViewById(R.id.textViewDate);
        textViewDayOfWeek = (TextView)findViewById(R.id.textViewDayOfWeek);
        textViewType = (TextView)findViewById(R.id.textViewType);

        pB.buildDatesInProgram();
        pB.buildTrainingDays();

        Date[] programDates = pB.getTrainingDatesInProgram();

        // fill the trainingDays in with exercises
        pB.populateTrainingDays(BuildNewProgramReview.this);
        TrainingDay[] trainingDays = pB.getTrainingDays();

        String dates = "";
        String days = "";
        String types = "";

        SimpleDateFormat format_EEEE = new SimpleDateFormat("EEEE");

        for(int i = 0; i < programDates.length; i++){
            if(programDates[i] != null){
                dates += trainingDays[i].dateString + "\n";
                days += format_EEEE.format(programDates[i]) + "\n";
                types += trainingDays[i].type + "\n";
            }
        }
        textViewDate.setText(dates);
        textViewDayOfWeek.setText(days);
        textViewType.setText(types);
    }

    // Program names must be only letters and no more than 12 chars.
    // It also can't be empty.
    boolean checkName() {
        char[] chars = editTextName.getText().toString().toCharArray();
        String error = getResources().getString(R.string.name_error);

        // Cannot be longer than 12 or blank
        if(chars.length > 12 || editTextName.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            return false;
        }

        // Must contain only letters and spaces
        for (char c : chars) {
            if (!Character.isLetter(c) && c != " ".charAt(0)) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
