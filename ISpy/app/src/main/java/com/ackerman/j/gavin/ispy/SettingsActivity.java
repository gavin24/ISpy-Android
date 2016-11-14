package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by gavin.ackerman on 2016-11-11.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void TagsClick(View v) {

        Intent i = new Intent(this,TagActivity.class);

        startActivity(i);
    }
    public void UserClick(View v) {

        Intent i = new Intent(this,UserInfoActivity.class);

        startActivity(i);
    }
    public void LogoutClick(View v) {

        Intent i = new Intent(this,MainActivity.class);

        startActivity(i);
    }
}
