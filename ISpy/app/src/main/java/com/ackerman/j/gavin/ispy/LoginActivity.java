package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;

/**
 * Created by gavin.ackerman on 2016-11-11.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private EditText passWord;
    private UserServiceImpl userService;
    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        // s.addAnimal();
        userName = (EditText)findViewById(R.id.editTextEmail);
        passWord = (EditText) findViewById(R.id.editTextPassword);
    }


    public void LoginClick(View v) {
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        userService = new UserServiceImpl();
        isValid = userService.isAuthentic(user,pass);
        if(isValid == true)
        {
            Intent i = new Intent(this,NewsFeedActivity.class);

            startActivity(i);
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "Please enter correct Username and Password", Toast.LENGTH_LONG)
                    .show();
        }

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
}
