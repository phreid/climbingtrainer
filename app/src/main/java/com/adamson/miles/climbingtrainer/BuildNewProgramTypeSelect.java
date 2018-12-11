package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

        imageHelpVolume = findViewById(R.id.imageHelpVolume);
        imageHelpVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Volume phase of a training program. Volume training aims to do as many moves as possible in a session. " +
                        "It is often trained first to prepare the body with a baseline fitness level. During this phase the climbing " +
                        "is not continuous or difficult enough to get a pump.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpStrPow = (ImageButton) findViewById(R.id.imageHelpStrPow);
        imageHelpStrPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Strength and Power phase of a training program. Strength and Power are defined as 1-4 moves. Strength is your ability to hold positions, " +
                        "and power is your ability to generate from them. Strength and Power control the hardest individual moves you can do.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpPowEnd = (ImageButton) findViewById(R.id.imageHelpPowEnd);
        imageHelpPowEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Power Endurance phase of a training program. Power Endurance is defined as 8-15 moves." +
                        "It helps with your ability to do several near-limit moves in a row, or when slightly fatigued.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpEnd = (ImageButton) findViewById(R.id.imageHelpEnd);
        imageHelpEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Endurance phase of a training program. Endurance is defined as more than 20 moves." +
                        "It helps with your ability to complete long moderate sequences without getting pumped, and to recover from pump.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpBoulderingProgram = (ImageButton) findViewById(R.id.imageHelpBoulderingProgram);
        imageHelpBoulderingProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build an entire bouldering program with three phases: Volume, Strength and Power, and then Power Endurance.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpRoutesProgram = (ImageButton) findViewById(R.id.imageHelpRoutesProgram);
        imageHelpRoutesProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build an entire routes program with four phases: Volume, Strength and Power, Power Endurance and then Endurance.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });




    }

}
