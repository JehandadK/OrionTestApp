package com.jehandadk.oriontest.ui;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jehandadk.oriontest.OrionApp;

/**
 * Created by jehandad.kamal on 7/13/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected OrionApp getApp() {
        return (OrionApp) getApplication();
    }
}
