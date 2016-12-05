package com.ackerman.j.gavin.ispy;

/**
 * Created by cuan.meyer on 2016/12/04.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;


public class PopUpActivity extends AppCompatActivity implements OnMenuItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_click).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(PopUpActivity.this, view);
                popupMenu.setOnMenuItemClickListener(PopUpActivity.this);
                popupMenu.inflate(R.menu.popup_activity);
                popupMenu.show();
            }
        });
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_comedy:
                Toast.makeText(this, "Comedy Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_movies:
                Toast.makeText(this, "Movies Clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item_music:
                Toast.makeText(this, "Music Clicked", Toast.LENGTH_SHORT).show();

        }
        return true;
    }
}
