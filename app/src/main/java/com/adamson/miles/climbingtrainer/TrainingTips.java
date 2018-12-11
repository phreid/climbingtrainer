package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

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
    Button buttonTipWamUp;
    Button buttonTipHangboard;
    Button buttonTipAlcohol;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_tips);

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        buttonTipGrades = (Button)findViewById(R.id.buttonTipGrades);
        buttonTipGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_minimum_grade));
                intent.putExtra("title", buttonTipGrades.getText());
                startActivity(intent);
            }
        });

        buttonTipVolume = (Button)findViewById(R.id.buttonTipVolume);
        buttonTipVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_volume));
                intent.putExtra("title", buttonTipVolume.getText());
                startActivity(intent);
            }
        });

        buttonTipStrength = (Button)findViewById(R.id.buttonTipStrength);
        buttonTipStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_strength));
                intent.putExtra("title", buttonTipStrength.getText());
                startActivity(intent);
            }
        });

        buttonTipPower = (Button)findViewById(R.id.buttonTipPower);
        buttonTipPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_power));
                intent.putExtra("title", buttonTipPower.getText());
                startActivity(intent);
            }
        });

        buttonTipPowerEnd = (Button)findViewById(R.id.buttonTipPowerEnd);
        buttonTipPowerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_powerEnd));
                intent.putExtra("title", buttonTipPowerEnd.getText());
                startActivity(intent);
            }
        });

        buttonTipEndurance = (Button)findViewById(R.id.buttonTipEndurance);
        buttonTipEndurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_endurance));
                intent.putExtra("title", buttonTipEndurance.getText());
                startActivity(intent);
            }
        });

        buttonTipFastLeadLaps = (Button)findViewById(R.id.buttonTipFastLeadLaps);
        buttonTipFastLeadLaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_fast_lead_laps));
                intent.putExtra("title", buttonTipFastLeadLaps.getText());
                startActivity(intent);
            }
        });

        buttonTipBouldering = (Button)findViewById(R.id.buttonTipBouldering);
        buttonTipBouldering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_boulder_for_routes));
                intent.putExtra("title", buttonTipBouldering.getText());
                startActivity(intent);
            }
        });

        buttonTipOvertraining = (Button)findViewById(R.id.buttonTipOvertraining);
        buttonTipOvertraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_overtraining));
                intent.putExtra("title", buttonTipOvertraining.getText());
                startActivity(intent);
            }
        });

        buttonTipTrainingCycles = (Button)findViewById(R.id.buttonTipTrainingCycles);
        buttonTipTrainingCycles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_training_cycles));
                intent.putExtra("title", buttonTipTrainingCycles.getText());
                startActivity(intent);
            }
        });

        buttonTipWamUp = (Button)findViewById(R.id.buttonTipWamUp);
        buttonTipWamUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_warm_up));
                intent.putExtra("title", buttonTipWamUp.getText());
                startActivity(intent);
            }
        });

        buttonTipHangboard = (Button)findViewById(R.id.buttonTipHangboard);
        buttonTipHangboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_hangboard_form));
                intent.putExtra("title", buttonTipHangboard.getText());
                startActivity(intent);
            }
        });

        buttonTipAlcohol = (Button)findViewById(R.id.buttonTipAlcohol);
        buttonTipAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainingTips.this, ViewTip.class);
                intent.putExtra("tip", getString(R.string.tip_alcohol));
                intent.putExtra("title", buttonTipAlcohol.getText());
                startActivity(intent);
            }
        });
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

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {

            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }
}
