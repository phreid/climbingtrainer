package com.adamson.miles.climbingtrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewTip extends AppCompatActivity {

    TextView textViewTip;
    TextView textViewTitle;
    TextView textViewLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tip);

        textViewTip = (TextView)findViewById(R.id.textViewTip);
        textViewTip.setText(getIntent().getStringExtra("tip"));
        textViewTip.setMovementMethod(new ScrollingMovementMethod());

        textViewTitle = (TextView)findViewById(R.id.textViewTipTitle);
        textViewTitle.setText(getIntent().getStringExtra("title"));
        textViewLink = (TextView)findViewById(R.id.textViewLink);

        if(getIntent().getStringExtra("link")!=null){
            textViewLink.setMovementMethod(LinkMovementMethod.getInstance());
            textViewLink.setText(getIntent().getStringExtra("link"));
        } else {
            ((ViewManager)textViewLink.getParent()).removeView(textViewLink);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textViewTip.getLayoutParams();

            // Set TextView layout margin 15dip
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float logicalDensity = metrics.density;
            int px = (int) Math.ceil(15 * logicalDensity);
            // (Left Top Right Bottom)
            lp.setMargins(px, 0, px, px);

            // Apply the updated layout parameters to TextView
            textViewTip.setLayoutParams(lp);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_tip, menu);
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
