package com.adamson.miles.climbingtrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class ExerciseList extends AppCompatActivity {

    Exercise[] exercises;
    String[] types;
    ScrollView scrollView;
    LinearLayout scrollLayoutChild;
    Spinner spinnerFilterType;
    Boolean firstCall = true;
    Boolean replacingExercise = false;
    Exercise oldExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        scrollView = findViewById(R.id.scrollViewExercises);
        scrollLayoutChild = findViewById(R.id.layoutExercises);

        spinnerFilterType = findViewById(R.id.spinnerFilterType);
        types = getResources().getStringArray(R.array.types_strings);
        ArrayAdapter<String> typesArray = new ArrayAdapter<>(
                ExerciseList.this,
                R.layout.spinner_design,
                types);
        typesArray.setDropDownViewResource(R.layout.spinner_my_style);
        spinnerFilterType.setAdapter(typesArray);

        int position = getIntent().getIntExtra("position", types.length - 1);
        spinnerFilterType.setSelection(position);

        DatabaseHelper db = new DatabaseHelper(this);

        oldExercise = (Exercise) getIntent().getSerializableExtra("exercise");
        if(oldExercise != null){
            replacingExercise = true;
        }



        // If selected all types, select all. Also select All by default.
        String intentType = getIntent().getStringExtra("type");
        if(intentType == null || intentType.equals(ExerciseBuilder.types[ExerciseBuilder.ALL])) {
            exercises = db.selectAllExercises();
        } else {
            exercises = db.selectAllExerciseByType(intentType);
        }

        // creates button for each exercise depending on the type given to the
        // activity's intent. Selects all if no type given
        for(int i = 0; i < exercises.length; i++){
            LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent),null, 0);
            Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
            button.setTextColor(getResources().getColor(R.color.colorBlack));
            float pixels =  40 * getApplicationContext().getResources().getDisplayMetrics().density;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) pixels);
            button.setLayoutParams(lp);
            button.setText(exercises[i].name);
            setButtonBG(button, exercises[i]);
            final int index = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // If not replacing an exercise from a program, view the exercises details
                    if(!replacingExercise) {
                        Intent intent = new Intent(ExerciseList.this, ViewExercise.class);
                        intent.putExtra("exercise", exercises[index]);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            builder = new AlertDialog.Builder(ExerciseList.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(ExerciseList.this);
                        }
                        builder.setMessage("Replace "+oldExercise.name+" with "+exercises[index].name+"?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String programName = getIntent().getStringExtra("programName");
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                db.updateProgram(oldExercise, exercises[index], programName);
                                Intent intent = new Intent(ExerciseList.this, ViewProgramByDate.class);
                                intent.putExtra("programName", programName);
                                intent.putExtra("overrideBack", true);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();builder.show();
                    }
                }
            });

            // Allow user to delete custom exercises
            if(exercises[index].name.contains("USER - ") && !replacingExercise){
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            builder = new AlertDialog.Builder(ExerciseList.this, android.R.style.Theme_Material_Light_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(ExerciseList.this);
                        }
                        builder.setMessage("Delete "+exercises[index].name+"?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                String response = db.deleteExercise(exercises[index].name);
                                if(response == null) {
                                    dialog.dismiss();
                                    String type = getIntent().getStringExtra("type");
                                    Intent intent = new Intent(ExerciseList.this, ExerciseList.class);
                                    intent.putExtra("type", type);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        return false;
                    }
                });
            }
            layoutHorizontal.addView(button);
            scrollLayoutChild.addView(layoutHorizontal);
        }

    }

    // setOnItemSelectedListener is called when the spinner is initialized,
    // make this first call not do anything. Should only do something when
    // the user actually selects from the spinner.
    @Override
    protected void onStart() {
        super.onStart();
        spinnerFilterType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!firstCall) {
                    Intent intent = getIntent();
                    intent.putExtra("type", types[position]);
                    intent.putExtra("position", position);
                    if(replacingExercise){
                        intent.putExtra("exercise", oldExercise);
                    }
                    finish();
                    startActivity(intent);
                } else {
                    firstCall = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        if (id == R.id.action_home) {
            startActivity(new Intent(ExerciseList.this, MainActivity.class));
            return true;
        }

        if (id == R.id.action_help) {
            Toast.makeText(getApplicationContext(), "Tap and hold to delete a custom exercise. Built-in exercises cannot be deleted.", Toast.LENGTH_LONG).show();
            return true;
        }


        if(id == R.id.action_add){
            startActivity(new Intent(ExerciseList.this, AddExercise.class));
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
