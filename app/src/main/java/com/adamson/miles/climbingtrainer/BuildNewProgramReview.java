package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BuildNewProgramReview extends AppCompatActivity {

    TextView textViewReview;
    TextView textViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_review);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        initReviewText();

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

        String s = reviewSegments[0] + " " + programBuilder.getProgramType() + "\n\n";
      //  s = s + reviewSegments[1] + " " +  programBuilder.getSessionDuration() + "\n\n";
       // s = s + reviewSegments[2] + " " +  programBuilder.getSessionsPerWeek() + "\n\n";
        s = s + reviewSegments[3] + " " +  programBuilder.getCommitmentLevel() + "\n\n";
        s = s + reviewSegments[4] + " " +  programBuilder.getCurrentGrade() + "\n\n";
        s = s + reviewSegments[5] + " " +  programBuilder.getStartDateString() + "\n\n";
        s = s + reviewSegments[6] + " " +  programBuilder.getEndDateString() + "\n\n";
        s = s + "Program Length: " + Long.toString(programBuilder.getProgramLength()) + " Days\n\n";
        s = s + reviewSegments[7] + "\n\n";

        String[] equipment = getResources().getStringArray(R.array.equipment);
        boolean[] equipmentArray = programBuilder.getEquipmentAvailable();
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

        textViewTest = (TextView)findViewById(R.id.textViewTest);
        ProgramBuilder.getInstance().buildDatesInProgram();
        ProgramBuilder.getInstance().buildTrainingDays();
        Date[] programDates = ProgramBuilder.getInstance().getTrainingDatesInProgram();
        TrainingDay[] trainingDays = ProgramBuilder.getInstance().getTrainingDays();
        String dates = "";
        SimpleDateFormat format_yyyymmdd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format_EEEE = new SimpleDateFormat("EEEE");
        for(int i = 0; i < programDates.length; i++){
            if(programDates[i] != null){
                dates += format_EEEE.format(programDates[i])+ ", " + format_yyyymmdd.format(programDates[i]);
                dates += "..." + trainingDays[i].type + "\n";
            }
        }
        String text = "All Dates of Program and their Type:\n\n";
        textViewTest.setText(text + dates);
    }

}
