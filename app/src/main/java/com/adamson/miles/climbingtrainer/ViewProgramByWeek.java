package com.adamson.miles.climbingtrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewProgramByWeek extends AppCompatActivity {

    String[] weeks;
    ArrayList<CheckBox> checkBoxes;
    String programName;
    ScrollView scrollView;
    LinearLayout scrollLayoutChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_program_by_week);
        checkBoxes = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        programName = getIntent().getStringExtra("programName");
        weeks = db.selectProgramWeeks(programName);
        scrollView = findViewById(R.id.scrollViewWeeks);
        scrollLayoutChild = findViewById(R.id.scrollViewWeeksChild);

        // create button and checkbox for each week
        for (int i = 0; i < weeks.length; i++) {
            final int index = i;
            LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent), null, 0);
            final Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
            final CheckBox checkBox = new CheckBox(getApplicationContext());

            if(db.programWeekCompleted(programName, weeks[i])){
                checkBox.setChecked(true);
            }

            final boolean previous = checkBox.isChecked();
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBox.setChecked(previous);
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewProgramByWeek.this);
                    if(!checkBox.isChecked()) {
                        builder.setTitle("Set everything in week " + weeks[index] + " to completed?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                db.completeWeek(weeks[index], programName);
                                dialog.dismiss();
                                finish();
                                startActivity(getIntent());
                                return;
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                return;
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        builder.setTitle("Set everything in week " + weeks[index] + " to incomplete?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                db.uncompleteWeek(weeks[index], programName);
                                dialog.dismiss();
                                finish();
                                startActivity(getIntent());
                                return;
                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                return;
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            });
            checkBox.setBackground(getApplicationContext().getDrawable(R.drawable.gradient_blue));

            float pixels = 40 * getApplicationContext().getResources().getDisplayMetrics().density;

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int) pixels, 1.0f);
            LinearLayout.LayoutParams lpCheckbox = new LinearLayout.LayoutParams((int) pixels, (int) pixels);

            checkBox.setLayoutParams(lpCheckbox);
            button.setLayoutParams(lp);

            String type = db.selectTypeByWeek(programName, weeks[i]);
            if(type.equals("power")){
                type = "Strength and Power";
            }

            String buttonText = "Week " + weeks[i] +", " + type;
            button.setText(buttonText);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewProgramByWeek.this, ViewProgramByDate.class);
                    intent.putExtra("weekName", weeks[index]);
                    intent.putExtra("programName", programName);
                    startActivity(intent);
                }
            });

            layoutHorizontal.addView(button);
            layoutHorizontal.addView(checkBox);
            checkBoxes.add(checkBox);
            scrollLayoutChild.addView(layoutHorizontal);
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_program_by_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_home) {
            startActivity(new Intent(ViewProgramByWeek.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Update checkboxes when user navigates back and forth
    @Override
    public void onResume(){
        super.onResume();
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        for(int i = 0; i < weeks.length; i++){
            if(db.programWeekCompleted(programName, weeks[i])){
                checkBoxes.get(i).setChecked(true);
            }
        }
    }

}
