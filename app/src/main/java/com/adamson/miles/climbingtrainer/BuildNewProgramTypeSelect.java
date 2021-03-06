package com.adamson.miles.climbingtrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BuildNewProgramTypeSelect extends AppCompatActivity {

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

        initButtons();
        initHelp();

        AlertDialog.Builder builder;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
        }

        builder.setMessage("Most users will want to select either a routes program or a bouldering program. For example, if you are training for a route climbing trip 6 months away, select Routes Program." +
                "\n\nProgram segments are put together to create complete programs. You can create just a segment if you wish to.");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
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

        if (id == R.id.action_home) {
            startActivity(new Intent(BuildNewProgramTypeSelect.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initButtons(){
        buttonVolumeOnly = findViewById(R.id.buttonVolume);
        buttonStrPowOnly = findViewById(R.id.buttonStrPow);
        buttonPowEndOnly = findViewById(R.id.buttonPowEnd);
        buttonEndOnly = findViewById(R.id.buttonEnd);
        buttonBoulderingProgram = findViewById(R.id.buttonBoulderingProgram);
        buttonRoutesProgram = findViewById(R.id.buttonRoutesProgram);

        buttonVolumeOnly.setOnClickListener(select(buttonVolumeOnly));
        buttonStrPowOnly.setOnClickListener(select(buttonStrPowOnly));
        buttonPowEndOnly.setOnClickListener(select(buttonPowEndOnly));
        buttonEndOnly.setOnClickListener(select(buttonEndOnly));
        buttonBoulderingProgram.setOnClickListener(select(buttonBoulderingProgram));
        buttonRoutesProgram.setOnClickListener(select(buttonRoutesProgram));
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

        imageHelpStrPow =  findViewById(R.id.imageHelpStrPow);
        imageHelpStrPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Strength and Power phase of a training program. Strength and Power are defined as 1-4 moves. Strength is your ability to hold positions, " +
                        "and power is your ability to generate from them. Strength and Power control the hardest individual moves you can do.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpPowEnd =  findViewById(R.id.imageHelpPowEnd);
        imageHelpPowEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Power Endurance phase of a training program. Power Endurance is defined as 8-15 moves." +
                        "It helps with your ability to do several near-limit moves in a row, or when slightly fatigued.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpEnd =  findViewById(R.id.imageHelpEnd);
        imageHelpEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build only the Endurance phase of a training program. Endurance is defined as more than 20 moves." +
                        "It helps with your ability to complete long moderate sequences without getting pumped, and to recover from pump.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpBoulderingProgram =  findViewById(R.id.imageHelpBoulderingProgram);
        imageHelpBoulderingProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build an entire bouldering program with three phases: Volume, Strength and Power, and then Power Endurance.\n\n" +
                        "When selecting dates, remember that this type of program must be at least 4 weeks long.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });

        imageHelpRoutesProgram =  findViewById(R.id.imageHelpRoutesProgram);
        imageHelpRoutesProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "Build an entire routes program with four phases: Volume, Strength and Power, Power Endurance and then Endurance.\n\n" +
                        "When selecting dates, remember that this type of program must be at least 6 weeks long.";
                AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramTypeSelect.this);
                builder.setMessage(text).setCancelable(true).show();
            }
        });
    }

    View.OnClickListener select(final Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                programBuilder.setProgramType(button.getText().toString());
                startActivity(new Intent(BuildNewProgramTypeSelect.this, BuildNewProgramRoot.class));
            }
        };
    }

}
