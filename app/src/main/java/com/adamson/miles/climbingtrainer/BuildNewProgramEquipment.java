package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class BuildNewProgramEquipment extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    Button buttonToStartDate;
    CheckBox checkBoxBar;
    CheckBox checkBoxFreeweights;
    CheckBox checkBoxRings;
    CheckBox checkBoxCampusBoard;
    CheckBox checkBoxMedicineBalls;
    CheckBox checkBoxFillWall;
    CheckBox checkBoxMoonboard;
    CheckBox checkBoxHangboard;
    CheckBox checkBoxKettleBells;
    CheckBox checkBoxLeadTR;
    CheckBox checkBoxAdjustableWall;
    CheckBox[] boxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_equipment);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        buttonToStartDate = (Button)findViewById(R.id.buttonToStartDate);
        buttonToStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                for(int i = 0; i < boxes.length; i++){
                    if(boxes[i].isChecked()){
                        programBuilder.setEquipmentAvailable(i);
                    }
                }
                startActivity(new Intent(BuildNewProgramEquipment.this, BuildNewProgramStartDate.class));
            }
        });

        initCheckBoxes();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_equipment, menu);
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

    void initCheckBoxes(){
        checkBoxBar = (CheckBox)findViewById(R.id.checkBoxBar);
        checkBoxFreeweights = (CheckBox)findViewById(R.id.checkBoxFreeweights);
        checkBoxRings = (CheckBox)findViewById(R.id.checkBoxRings);
        checkBoxCampusBoard = (CheckBox)findViewById(R.id.checkBoxCampusBoard);
        checkBoxMedicineBalls = (CheckBox)findViewById(R.id.checkBoxMedicineBalls);
        checkBoxFillWall = (CheckBox)findViewById(R.id.checkBoxFillWall);
        checkBoxMoonboard = (CheckBox)findViewById(R.id.checkBoxMoonboard);
        checkBoxHangboard = (CheckBox)findViewById(R.id.checkBoxHangboard);
        checkBoxKettleBells = (CheckBox)findViewById(R.id.checkBoxKettleBells);
        checkBoxLeadTR = (CheckBox)findViewById(R.id.checkBoxLeadTR);
        checkBoxAdjustableWall = (CheckBox)findViewById(R.id.checkBoxAdjustableWall);

        boxes = new CheckBox[]{checkBoxBar,
                checkBoxFreeweights,
                checkBoxRings,
                checkBoxRings,
                checkBoxCampusBoard,
                checkBoxMedicineBalls,
                checkBoxFillWall,
                checkBoxMoonboard,
                checkBoxHangboard,
                checkBoxKettleBells,
                checkBoxLeadTR,
                checkBoxAdjustableWall
        };

        String[] texts = getResources().getStringArray(R.array.equipment);
        for(int i = 0; i < boxes.length; i++){
            boxes[i].setText(texts[i]);
        }
    }

}
