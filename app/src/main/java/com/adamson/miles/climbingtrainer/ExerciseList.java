package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExerciseList extends AppCompatActivity {

    Exercise[] exercises;
    ScrollView scrollView;
    LinearLayout scrollLayoutChild;

    Button buttonVOL;
    Button buttonSTR;
    Button buttonPWR;
    Button buttonPWREND;
    Button buttonEND;
    Button buttonALL;
    Button buttonCON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        scrollView = (ScrollView)findViewById(R.id.scrollViewExercises);
        scrollLayoutChild = (LinearLayout)findViewById(R.id.layoutExercises);

        DatabaseHelper db = new DatabaseHelper(this);

        if (getIntent().getStringExtra("type") == null) {
            exercises = db.selectAllExercises();
        } else {
            exercises = db.selectAllExerciseByType(getIntent().getStringExtra("type"));
        }

        for(int i = 0; i < exercises.length; i++){
            LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent),null, 0);
            Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
            float pixels =  40 * getApplicationContext().getResources().getDisplayMetrics().density;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) pixels);
            button.setLayoutParams(lp);
            button.setText(exercises[i].name);
            setButtonBG(button, exercises[i]);
            final int index = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ExerciseList.this, ViewExercise.class);
                    intent.putExtra("exercise", exercises[index]);
                    startActivity(intent);
                }
            });
            layoutHorizontal.addView(button);
            scrollLayoutChild.addView(layoutHorizontal);
        }

        buttonALL = (Button)findViewById(R.id.buttonALL);
        buttonALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonPWR = (Button)findViewById(R.id.buttonPWR);
        buttonPWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = ExerciseBuilder.types[ExerciseBuilder.POWER];
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });

        buttonEND = (Button)findViewById(R.id.buttonEND);
        buttonEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = ExerciseBuilder.types[ExerciseBuilder.ENDURANCE];
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });

        buttonPWREND = (Button)findViewById(R.id.buttonPWREND);
        buttonPWREND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = ExerciseBuilder.types[ExerciseBuilder.POWEND];
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });

        buttonALL = (Button)findViewById(R.id.buttonALL);
        buttonALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = null;
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });

        buttonVOL = (Button)findViewById(R.id.buttonVOL);
        buttonVOL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = ExerciseBuilder.types[ExerciseBuilder.VOLUME];
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });

        buttonCON = (Button)findViewById(R.id.buttonCON);
        buttonCON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = ExerciseBuilder.types[ExerciseBuilder.CONDITIONING];
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });

        buttonSTR = (Button)findViewById(R.id.buttonSTR);
        buttonSTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String type = ExerciseBuilder.types[ExerciseBuilder.STRENGTH];
                intent.putExtra("type", type);
                finish();
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercises, menu);
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

    void setButtonBG(Button button, Exercise exercise) {
        Drawable[] drawables= new Drawable[]{
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_red),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_indigo),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_orange),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_blue),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_yellow),
                getApplicationContext().getResources().getDrawable(R.drawable.gradient_pink)
        };

        switch (exercise.type){
            case "Volume":
                button.setBackground(drawables[0]);
                break;
            case "Strength":
                button.setBackground(drawables[1]);
                break;
            case "Power":
                button.setBackground(drawables[2]);
                break;
            case "Power Endurance":
                button.setBackground(drawables[3]);
                break;
            case "Endurance":
                button.setBackground(drawables[4]);
                break;
            case "Conditioning":
                button.setBackground(drawables[5]);
                break;
        }
    }

}
