package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class BuildNewProgramReview extends AppCompatActivity {

    TextView textViewReview;

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
        s = s + reviewSegments[1] + " " +  programBuilder.getSessionDuration() + "\n\n";
        s = s + reviewSegments[2] + " " +  programBuilder.getSessionsPerWeek() + "\n\n";
        s = s + reviewSegments[3] + " " +  programBuilder.getCommitmentLevel() + "\n\n";
        s = s + reviewSegments[4] + " " +  programBuilder.getCurrentGrade() + "\n\n";
        s = s + reviewSegments[5] + " " +  programBuilder.getStartDate() + "\n\n";
        s = s + reviewSegments[6] + " " +  programBuilder.getEndDate() + "\n\n";
        s = s + reviewSegments[7] + "\n\n";

        String[] equipment = getResources().getStringArray(R.array.equipment);
        boolean[] booleenEquipment = programBuilder.getEquipmentAvailable();
        for(int i = 0; i < equipment.length; i++){
            if(booleenEquipment[i]){
                s = s + equipment[i];
                if(i != equipment.length-1){
                    s = s + ", ";
                }
            }
        }

        textViewReview.setText(s);
    }

}
