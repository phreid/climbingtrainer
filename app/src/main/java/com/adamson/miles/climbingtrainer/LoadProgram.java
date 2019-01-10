package com.adamson.miles.climbingtrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class LoadProgram extends AppCompatActivity {

    String[] names;
    ScrollView scrollView;
    LinearLayout scrollLayoutChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_program);

        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        names = db.selectAllPrograms();
        scrollView = (ScrollView)findViewById(R.id.scrollViewPrograms);
        scrollLayoutChild = (LinearLayout)findViewById(R.id.scrollViewProgramsChild);

        // If there are programs, creates button for each one
        if(names != null) {
            for (int i = 0; i < names.length; i++) {
                LinearLayout layoutHorizontal = new LinearLayout(new ContextThemeWrapper(this, R.style.LayoutHorizontalTransparent), null, 0);
                final Button button = new Button(new ContextThemeWrapper(this, R.style.ButtonWhite), null, 0);
                float pixels = 40 * getApplicationContext().getResources().getDisplayMetrics().density;
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) pixels);
                button.setLayoutParams(lp);
                button.setText(names[i]);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoadProgram.this, ViewProgramByDate.class);
                        intent.putExtra("programName", button.getText().toString());
                        startActivity(intent);
                    }
                });
                layoutHorizontal.addView(button);
                scrollLayoutChild.addView(layoutHorizontal);

                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoadProgram.this);
                        builder.setTitle("Delete " + button.getText().toString() + "? This cannot be undone.");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                                db.deleteProgram(button.getText().toString());
                                dialog.dismiss();
                                finish();
                                startActivity(getIntent());
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
        } else {
            // There were no programs. Let user know they will be here once they make one.
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.program_error), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_load_program, menu);
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
