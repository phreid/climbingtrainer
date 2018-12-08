package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BuildNewProgramTypeSelect extends AppCompatActivity {

    Context context;

    Button buttonVolumeOnly;
    Button buttonStrPowOnly;
    Button buttonPowEndOnly;
    Button buttonEndOnly;
    Button buttonBoulderingProgram;
    Button buttonRoutesProgram;

    ImageButton imageHelpVolume;
    ImageButton imageHelpStrPow;
    ImageButton imageHelpPowEnd;
    ImageButton imageHelpEnd;
    ImageButton imageHelpBoulderingProgram;
    ImageButton imageHelpRoutesProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_type_select);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        context = getApplicationContext();

        initButtons();
        initHelp();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_type_select, menu);
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

    void initButtons(){
        buttonVolumeOnly = (Button) findViewById(R.id.buttonVolume);
        buttonVolumeOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(buttonVolumeOnly.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramAboutYou.class));
            }
        });

        buttonStrPowOnly = (Button) findViewById(R.id.buttonStrPow);
        buttonStrPowOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(buttonStrPowOnly.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramAboutYou.class));
            }
        });

        buttonPowEndOnly = (Button) findViewById(R.id.buttonPowEnd);
        buttonPowEndOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(buttonPowEndOnly.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramAboutYou.class));
            }
        });

        buttonEndOnly = (Button) findViewById(R.id.buttonEnd);
        buttonEndOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(buttonEndOnly.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramAboutYou.class));
            }
        });

        buttonBoulderingProgram = (Button) findViewById(R.id.buttonBoulderingProgram);
        buttonBoulderingProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(buttonBoulderingProgram.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramAboutYou.class));
            }
        });

        buttonRoutesProgram = (Button) findViewById(R.id.buttonRoutesProgram);
        buttonRoutesProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(buttonRoutesProgram.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramAboutYou.class));
            }
        });
        }


    void initHelp(){
        imageHelpVolume = (ImageButton) findViewById(R.id.imageHelpVolume);
        imageHelpVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help.helpAlert(R.id.imageHelpVolume, BuildNewProgramTypeSelect.this);
            }
        });

        imageHelpStrPow = (ImageButton) findViewById(R.id.imageHelpStrPow);
        imageHelpStrPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help.helpAlert(R.id.imageHelpStrPow, BuildNewProgramTypeSelect.this);
            }
        });

        imageHelpPowEnd = (ImageButton) findViewById(R.id.imageHelpPowEnd);
        imageHelpPowEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help.helpAlert(R.id.imageHelpPowEnd, BuildNewProgramTypeSelect.this);
            }
        });

        imageHelpEnd = (ImageButton) findViewById(R.id.imageHelpEnd);
        imageHelpEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help.helpAlert(R.id.imageHelpEnd, BuildNewProgramTypeSelect.this);
            }
        });

        imageHelpBoulderingProgram = (ImageButton) findViewById(R.id.imageHelpBoulderingProgram);
        imageHelpBoulderingProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help.helpAlert(R.id.imageHelpBoulderingProgram, BuildNewProgramTypeSelect.this);
            }
        });

        imageHelpRoutesProgram = (ImageButton) findViewById(R.id.imageHelpRoutesProgram);
        imageHelpRoutesProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Help.helpAlert(R.id.imageHelpRoutesProgram, BuildNewProgramTypeSelect.this);
            }
        });




    }

}
