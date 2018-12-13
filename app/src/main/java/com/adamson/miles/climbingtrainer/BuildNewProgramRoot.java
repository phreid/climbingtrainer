package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class BuildNewProgramRoot extends AppCompatActivity {

    Button buttonToProgramType;
    Button buttonToAboutYou;
    Button buttonToEquipment;
    Button buttonToStartDate;
    Button buttonToReview;
    Button buttonHome;

    CheckBox checkBoxType;
    CheckBox checkBoxAboutYou;
    CheckBox checkBoxEquipment;
    CheckBox checkBoxDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_root);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);
        buttonToReview = (Button)findViewById(R.id.buttonToReview);
        buttonToProgramType = (Button)findViewById(R.id.buttonToProgramType);
        buttonToAboutYou = (Button)findViewById(R.id.buttonToAboutYou);
        buttonToEquipment = (Button)findViewById(R.id.buttonToEquipment);
        buttonToStartDate = (Button)findViewById(R.id.buttonToStartDate);
        buttonHome = (Button)findViewById(R.id.buttonHome);

        buttonToProgramType.setOnClickListener(navigateTo(BuildNewProgramTypeSelect.class));
        buttonToAboutYou.setOnClickListener(navigateTo(BuildNewProgramAboutYou.class));
        buttonToEquipment.setOnClickListener(navigateTo(BuildNewProgramEquipment.class));

        // boxesAreChecked initializes CheckBoxes then returns true if user is done
        if(boxesAreChecked()){
            buttonToReview.setOnClickListener(navigateTo(BuildNewProgramReview.class));
            buttonToReview.setBackgroundResource(R.drawable.gradient_green);
        } else {
            buttonToReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "You must complete all the above before you can review.";
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramRoot.this);
                    builder.setMessage(text).setCancelable(true).show();
                }
            });
        }

        if(checkBoxType.isChecked()) {
            buttonToStartDate.setOnClickListener(navigateTo(BuildNewProgramStartDate.class));
        } else {
            buttonToStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "You must select a program type first.";
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramRoot.this);
                    builder.setMessage(text).setCancelable(true).show();
                }
            });
        }
        buttonHome.setOnClickListener(navigateTo(MainActivity.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_root, menu);
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

    // init CheckBoxes. Disable the user from clicking them.
    // check them if that portion of the program is built.
    // returns true if everything is done
    boolean boxesAreChecked(){
        boolean allChecked = true;

        checkBoxType = (CheckBox)findViewById(R.id.checkBoxType);
        checkBoxType.setClickable(false);
        checkBoxAboutYou = (CheckBox)findViewById(R.id.checkBoxAboutYou);
        checkBoxAboutYou.setClickable(false);
        checkBoxEquipment = (CheckBox)findViewById(R.id.checkBoxEquipment);
        checkBoxEquipment.setClickable(false);
        checkBoxDates = (CheckBox)findViewById(R.id.checkBoxDates);
        checkBoxDates.setClickable(false);

        ProgramBuilder programBuilder = ProgramBuilder.getInstance();

        // Check if the ProgramBuilder has all the information
        // it needs to build a program
        if(programBuilder.getProgramType() != null){
            checkBoxType.setChecked(true);
        } else {
            checkBoxType.setChecked(false);
            allChecked = false;
        }

        if(programBuilder.getCommitmentLevel() != null){
            checkBoxAboutYou.setChecked(true);
        } else {
            checkBoxAboutYou.setChecked(false);
            allChecked = false;
        }

        boolean[] equip = programBuilder.getEquipmentAvailable();
        boolean anyEquipment = false;
        for (int i = 0; i < equip.length; i++) {
            if(equip[i]){
                anyEquipment = true;
            }
        }
        if (anyEquipment) {
            checkBoxEquipment.setChecked(true);
        } else {
            checkBoxEquipment.setChecked(false);
            allChecked = false;
        }

        if(programBuilder.getEndDateString() != null){
            checkBoxDates.setChecked(true);
        } else {
            checkBoxDates.setChecked(false);
            allChecked = false;
        }

        return allChecked;
    }

    View.OnClickListener navigateTo(Class<?> destination){
        final Intent intent = new Intent(BuildNewProgramRoot.this, destination);
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        };
    }

}
