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

public class BuildNewProgramEquipment extends AppCompatActivity {

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

        buttonToStartDate = (Button)findViewById(R.id.buttonToStartDate);
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
