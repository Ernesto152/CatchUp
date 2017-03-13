package pe.edu.utp.catchup.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import pe.edu.utp.catchup.R;

public class SplashActivity extends AppCompatActivity {
    final static int SPLASH_OUT_TIMEOUT = 3000;
    final static int SLEEP_INTERVAL = 100;
    final static String TAG = "CatchUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Thread-based Delay implementation
        Thread splashDelayer = new Thread() {
            int wait = 0;
            @Override
            public void run() {
                try {
                  super.run();
                    while(wait < SPLASH_OUT_TIMEOUT){
                        sleep(SLEEP_INTERVAL);
                        wait += SLEEP_INTERVAL;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d(TAG, "Error on Splash Screen");
                } finally {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        splashDelayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
