package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonBuildNewProgram;
    Button buttonLoadProgram;
    Button buttonTrainingTips;
    Button buttonAboutTheAuthor;
    Button buttonExercises;
    Button buttonRemoveAds;

    ImageButton imageBuildNewProgram;
    ImageButton imageLoadProgram;
    ImageButton imageTips;
    ImageButton imageAboutTheAuthor;
    ImageButton imageExercises;
    ImageButton imageRemoveAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        ExerciseBuilder e = new ExerciseBuilder(this);

        buttonBuildNewProgram = (Button) findViewById(R.id.buttonBuildNewProgram);
        buttonLoadProgram = (Button) findViewById(R.id.buttonLoadProgram);
        buttonTrainingTips = (Button) findViewById(R.id.buttonTrainingTips);
        buttonAboutTheAuthor = (Button) findViewById(R.id.buttonAboutTheAuthor);
        buttonRemoveAds  = (Button) findViewById(R.id.buttonRemoveAds);
        buttonExercises = (Button)findViewById(R.id.buttonExercises);

        imageBuildNewProgram = (ImageButton)findViewById(R.id.imageBuildNewProgram);
        imageLoadProgram = (ImageButton)findViewById(R.id.imageLoadProgram);
        imageTips = (ImageButton)findViewById(R.id.imageTips);
        imageAboutTheAuthor = (ImageButton)findViewById(R.id.imageAboutTheAuthor);
        imageExercises = (ImageButton)findViewById(R.id.imageExercises);
        imageRemoveAds = (ImageButton)findViewById(R.id.imageRemoveAds);


        buttonBuildNewProgram.setOnClickListener(navigateTo(BuildNewProgramRoot.class));
        buttonAboutTheAuthor.setOnClickListener(navigateTo(AboutTheAuthor.class));
        buttonRemoveAds.setOnClickListener(navigateTo(RemoveAds.class));
        buttonTrainingTips.setOnClickListener(navigateTo(TrainingTips.class));
        buttonExercises.setOnClickListener(navigateTo(ExerciseList.class));
        buttonLoadProgram.setOnClickListener(navigateTo(LoadProgram.class));

        imageBuildNewProgram.setOnClickListener(navigateTo(BuildNewProgramRoot.class));
        imageAboutTheAuthor.setOnClickListener(navigateTo(AboutTheAuthor.class));
        imageRemoveAds.setOnClickListener(navigateTo(RemoveAds.class));
        imageTips.setOnClickListener(navigateTo(TrainingTips.class));
        imageExercises.setOnClickListener(navigateTo(ExerciseList.class));
        imageLoadProgram.setOnClickListener(navigateTo(LoadProgram.class));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    View.OnClickListener navigateTo(Class<?> destination){
        final Intent intent = new Intent(MainActivity.this, destination);
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        };
    }

}
