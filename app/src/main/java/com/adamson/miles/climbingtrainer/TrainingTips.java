package com.adamson.miles.climbingtrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TrainingTips extends AppCompatActivity {

    Button buttonTipGrades;
    Button buttonTipVolume;
    Button buttonTipStrength;
    Button buttonTipPower;
    Button buttonTipPowerEnd;
    Button buttonTipEndurance;
    Button buttonTipFastLeadLaps;
    Button buttonTipBouldering;
    Button buttonTipOvertraining;
    Button buttonTipTrainingCycles;
    Button buttonTipWarmUp;
    Button buttonTipHangboard;
    Button buttonTipAlcohol;
    Button buttonTipMetolius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_tips);

        buttonTipGrades = (Button)findViewById(R.id.buttonTipGrades);
        buttonTipGrades.setOnClickListener(onClick(buttonTipGrades, getString(R.string.tip_minimum_grade), null));

        buttonTipVolume = (Button)findViewById(R.id.buttonTipVolume);
        buttonTipVolume.setOnClickListener(onClick(buttonTipVolume, getString(R.string.tip_volume), null));

        buttonTipStrength = (Button)findViewById(R.id.buttonTipStrength);
        buttonTipStrength.setOnClickListener(onClick(buttonTipStrength, getString(R.string.tip_strength), null));

        buttonTipPower = (Button)findViewById(R.id.buttonTipPower);
        buttonTipPower.setOnClickListener(onClick(buttonTipPower, getString(R.string.tip_power), null));

        buttonTipPowerEnd = (Button)findViewById(R.id.buttonTipPowerEnd);
        buttonTipPowerEnd.setOnClickListener(onClick(buttonTipPowerEnd, getString(R.string.tip_powerEnd), null));

        buttonTipEndurance = (Button)findViewById(R.id.buttonTipEndurance);
        buttonTipEndurance.setOnClickListener(onClick(buttonTipEndurance, getString(R.string.tip_endurance), null));


        buttonTipFastLeadLaps = (Button)findViewById(R.id.buttonTipFastLeadLaps);
        buttonTipFastLeadLaps.setOnClickListener(onClick(buttonTipFastLeadLaps, getString(R.string.tip_fast_lead_laps), null));

        buttonTipBouldering = (Button)findViewById(R.id.buttonTipBouldering);
        buttonTipBouldering.setOnClickListener(onClick(buttonTipBouldering, getString(R.string.tip_boulder_for_routes), null));

        buttonTipOvertraining = (Button)findViewById(R.id.buttonTipOvertraining);
        buttonTipOvertraining.setOnClickListener(onClick(buttonTipOvertraining, getString(R.string.tip_overtraining), null));

        buttonTipTrainingCycles = (Button)findViewById(R.id.buttonTipTrainingCycles);
        buttonTipTrainingCycles.setOnClickListener(onClick(buttonTipTrainingCycles, getString(R.string.tip_training_cycles), null));

        buttonTipWarmUp = (Button)findViewById(R.id.buttonTipWamUp);
        buttonTipWarmUp.setOnClickListener(onClick(buttonTipWarmUp, getString(R.string.tip_warm_up), null));

        buttonTipHangboard = (Button)findViewById(R.id.buttonTipHangboard);
        buttonTipHangboard.setOnClickListener(onClick(buttonTipHangboard, getString(R.string.tip_hangboard_form), null));

        buttonTipAlcohol = (Button)findViewById(R.id.buttonTipAlcohol);
        buttonTipAlcohol.setOnClickListener(onClick(buttonTipAlcohol, getString(R.string.tip_alcohol), null));

        buttonTipMetolius = (Button)findViewById(R.id.buttonTipMetolius);
        buttonTipMetolius.setOnClickListener(onClick(buttonTipMetolius, getString(R.string.tip_metolius), getString(R.string.tip_metolius_link)));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_training_tips, menu);
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

    View.OnClickListener onClick(final Button button, final String tip, final String link){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", tip);
                intent.putExtra("title", button.getText());
                intent.putExtra("link", link);
                startActivity(intent);
            }
        };
    }

}
