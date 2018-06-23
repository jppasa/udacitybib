package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import gt.com.jpvr.funnyui.JokeActivity;

import static gt.com.jpvr.funnyui.JokeActivity.ARG_JOKE;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.EndpointsCallbacks {

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        dialog = ProgressDialog.show(this, null, getString(R.string.fetching_joke), true, false);
        new EndpointsAsyncTask(this)
                .setCallbacks(this)
                .execute();
    }

    @Override
    public void onEndpointTaskResult(String result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(ARG_JOKE, result);
        startActivity(intent);
    }
}
