package com.adamson.miles.climbingtrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class BuildNewProgramRoot extends AppCompatActivity {

    Button buttonToProgramType;
    Button buttonToAboutYou;
    Button buttonToEquipment;
    Button buttonToStartDate;
    Button buttonToReview;
    Button buttonHome;

    CheckBox checkBoxType;
    CheckBox checkBoxAboutYou;
    CheckBox checkBoxEquipment;
    CheckBox checkBoxDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_new_program_root);

        buttonToReview = (Button) findViewById(R.id.buttonToReview);
        buttonToProgramType = (Button) findViewById(R.id.buttonToProgramType);
        buttonToAboutYou = (Button) findViewById(R.id.buttonToAboutYou);
        buttonToEquipment = (Button) findViewById(R.id.buttonToEquipment);
        buttonToStartDate = (Button) findViewById(R.id.buttonToStartDate);
        buttonHome = (Button) findViewById(R.id.buttonHome);

        buttonToProgramType.setOnClickListener(navigateTo(BuildNewProgramTypeSelect.class));
        buttonToAboutYou.setOnClickListener(navigateTo(BuildNewProgramAboutYou.class));
        buttonToEquipment.setOnClickListener(navigateTo(BuildNewProgramEquipment.class));

        // boxesAreChecked initializes CheckBoxes then returns true if user is done
        if (boxesAreChecked()) {
                buttonToReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkName();
                    }
                });
                buttonToReview.setBackgroundResource(R.drawable.gradient_green);
        } else {
            buttonToReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "You must complete all the above before you can review.";
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramRoot.this);
                    builder.setMessage(text).setCancelable(true).show();
                }
            });
        }

        if (checkBoxType.isChecked()) {
            buttonToStartDate.setOnClickListener(navigateTo(BuildNewProgramStartDate.class));
        } else {
            buttonToStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = "You must select a program type first.";
                    AlertDialog.Builder builder = new AlertDialog.Builder(BuildNewProgramRoot.this);
                    builder.setMessage(text).setCancelable(true).show();
                }
            });
        }
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgramBuilder.getInstance().destroyInstance();
                Intent intent = new Intent(BuildNewProgramRoot.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_build_new_program_root, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(BuildNewProgramRoot.this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // init CheckBoxes. Disable the user from clicking them.
    // check them if that portion of the program is built.
    // returns true if everything is done
    boolean boxesAreChecked() {
        boolean allChecked = true;

        checkBoxType = (CheckBox) findViewById(R.id.checkBoxType);
        checkBoxType.setClickable(false);
        checkBoxAboutYou = (CheckBox) findViewById(R.id.checkBoxAboutYou);
        checkBoxAboutYou.setClickable(false);
        checkBoxEquipment = (CheckBox) findViewById(R.id.checkBoxEquipment);
        checkBoxEquipment.setClickable(false);
        checkBoxDates = (CheckBox) findViewById(R.id.checkBoxDates);
        checkBoxDates.setClickable(false);

        ProgramBuilder programBuilder = ProgramBuilder.getInstance();

        // Check if the ProgramBuilder has all the information
        // it needs to build a program
        if (programBuilder.getProgramType() != null) {
            checkBoxType.setChecked(true);
        } else {
            checkBoxType.setChecked(false);
            allChecked = false;
        }

        if (programBuilder.getCommitmentLevel() != null) {
            checkBoxAboutYou.setChecked(true);
        } else {
            checkBoxAboutYou.setChecked(false);
            allChecked = false;
        }

        boolean[] equip = programBuilder.getEquipmentAvailable();
        boolean anyEquipment = false;
        for (int i = 0; i < equip.length; i++) {
            if (equip[i]) {
                anyEquipment = true;
            }
        }
        if (anyEquipment) {
            checkBoxEquipment.setChecked(true);
        } else {
            checkBoxEquipment.setChecked(false);
            allChecked = false;
        }

        if (programBuilder.getEndDateString() != null) {
            checkBoxDates.setChecked(true);
        } else {
            checkBoxDates.setChecked(false);
            allChecked = false;
        }

        return allChecked;
    }

    View.OnClickListener navigateTo(Class<?> destination) {
        final Intent intent = new Intent(BuildNewProgramRoot.this, destination);
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        };
    }

    // Program weeks must be only letters and no more than 12 chars.
    // It also can't be empty.
    void checkName() {
        final EditText editText = new EditText(getApplicationContext());
        editText.setTextColor(getResources().getColor(R.color.colorBlack));
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Program Name");

        alert.setView(editText);

        alert.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String programName = editText.getText().toString();
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                if(db.programExists(programName)){
                    Toast.makeText(getApplicationContext(), "A program by this name already exists.", Toast.LENGTH_SHORT).show();
                } else {
                    char[] chars = editText.getText().toString().toCharArray();
                    String error = getResources().getString(R.string.name_error);

                    // Cannot be longer than 12 or blank
                    if (chars.length > 20 || editText.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    }

                    // No space first
                    if(chars[0] == ' '){
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        return;
                    }

                    // Must contain only letters and spaces. No new Lines.
                    for (char c : chars) {
                        if ((!Character.isLetter(c) && c != ' ') || c == '\n' || c == '\r') {
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            return;
                        }
                    }
                    // Name passed, enter program and navigate to view it
                    ProgramBuilder programBuilder = ProgramBuilder.getInstance();
                    programBuilder.setProgramName(programName);
                    Intent intent = new Intent(BuildNewProgramRoot.this, BuildNewProgramReview.class);
                    dialog.dismiss();
                    startActivity(intent);

                }
            }
        });

        alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        alert.show();
    }


}
