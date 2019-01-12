package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

public class BuildNewProgramEquipment extends AppCompatActivity {

    Button buttonToStartDate;
    CheckBox checkBoxBar;
    CheckBox checkBoxFreeweights;
    CheckBox checkBoxRings;
    CheckBox checkBoxCampusBoard;
    CheckBox checkBoxMedicineBalls;
    CheckBox checkBoxMoonboard;
    CheckBox checkBoxHangboard;
    CheckBox checkBoxKettleBells;
    CheckBox checkBoxLeadTR;
    CheckBox checkBoxAdjustableWall;
    CheckBox checkBoxTherabands;
    CheckBox[] boxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_equipment);

        buttonToStartDate = findViewById(R.id.buttonToStartDate);
        buttonToStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                for(int i = 0; i < boxes.length; i++){
                    programBuilder.setEquipmentAvailable(i, boxes[i].isChecked());
                }
                startActivity(new Intent(BuildNewProgramEquipment.this, BuildNewProgramRoot.class));
            }
        });

        initCheckBoxes();
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.equipment_toast), Toast.LENGTH_LONG).show();
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

        if (id == R.id.action_home) {
            startActivity(new Intent(BuildNewProgramEquipment.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void initCheckBoxes(){
        checkBoxBar = findViewById(R.id.checkBoxBar);
        checkBoxFreeweights = findViewById(R.id.checkBoxFreeweights);
        checkBoxRings = findViewById(R.id.checkBoxRings);
        checkBoxCampusBoard = findViewById(R.id.checkBoxCampusBoard);
        checkBoxMedicineBalls = findViewById(R.id.checkBoxMedicineBalls);
        checkBoxMoonboard = findViewById(R.id.checkBoxMoonboard);
        checkBoxHangboard = findViewById(R.id.checkBoxHangboard);
        checkBoxKettleBells = findViewById(R.id.checkBoxKettleBells);
        checkBoxLeadTR = findViewById(R.id.checkBoxLeadTR);
        checkBoxAdjustableWall = findViewById(R.id.checkBoxAdjustableWall);
        checkBoxTherabands = findViewById(R.id.checkBoxTherabands);

        // Required equipment
        forced(checkBoxBar);
        forced(checkBoxHangboard);
        forced(checkBoxCampusBoard);

        boxes = new CheckBox[]{checkBoxBar,
                checkBoxHangboard,
                checkBoxCampusBoard,
                checkBoxLeadTR,
                checkBoxFreeweights,
                checkBoxKettleBells,
                checkBoxRings,
                checkBoxMoonboard,
                checkBoxMedicineBalls,
                checkBoxAdjustableWall,
                checkBoxTherabands
        };

        String[] texts = getResources().getStringArray(R.array.equipment);
        for(int i = 0; i < boxes.length; i++){
            boxes[i].setText(texts[i]);
        }
    }

    void forced(CheckBox checkBox){
        checkBox.setChecked(true);
        checkBox.setClickable(false);
    }

}
