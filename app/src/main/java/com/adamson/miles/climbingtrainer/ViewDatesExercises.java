package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class ViewDatesExercises extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout scrollLayoutChild;
    String dateString;
    String programName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dates_exercises);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        scrollView = (ScrollView)findViewById(R.id.scrollViewDatesExercises);
        scrollLayoutChild = (LinearLayout)findViewById(R.id.scrollViewDatesExercisesChild);
        dateString = getIntent().getStringExtra("dateString");
        programName = getIntent().getStringExtra("programName");
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        ExerciseAndDate exerciseAndDate = db.selectFromProgramByDate(programName, dateString);

        // creates button for each exercise depending on the type given to the
        // activity's intent. Selects all if no type given
        for(int i = 0; i < exerciseAndDate.exerciseNames.length; i++){
            LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent),null, 0);
            Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
            float pixels =  40 * getApplicationContext().getResources().getDisplayMetrics().density;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) pixels);
            button.setLayoutParams(lp);
            button.setText(exerciseAndDate.exerciseNames[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    Intent intent = new Intent(ViewProgramByDate.this, ViewDatesExercises.class);
                    intent.putExtra("programName", programName);
                    intent.putExtra("dateString", dateString);
                    startActivity(intent);
                    */
                }
            });
            layoutHorizontal.addView(button);
            scrollLayoutChild.addView(layoutHorizontal);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_dates_exercises, menu);
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

}
