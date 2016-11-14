package com.ackerman.j.gavin.ispy;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ackerman.j.gavin.ispy.Domain.User;
import com.ackerman.j.gavin.ispy.Services.Impl.UserServiceImpl;

/**
 * Created by gavin.ackerman on 2016-11-11.
 */
public class UserInfoActivity extends AppCompatActivity {
    private EditText Name;
    private EditText passWord;
    private EditText Email;
    private EditText surName;
    private UserServiceImpl userService;
    private boolean isValid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.
                ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_activity);
       User oldUser = userService.getUser((long)1);
        Name = (EditText)findViewById(R.id.editTextName);
        surName = (EditText) findViewById(R.id.editTextSurname);
        Email = (EditText)findViewById(R.id.editTextEmail);
        passWord = (EditText) findViewById(R.id.editTextPassword);

        Name.setText(oldUser.getName());
        surName.setText(oldUser.getsurname());
        Email.setText(oldUser.getEmail());
        passWord.setText(oldUser.getpassword());
    }


    public void SubmitClick(View v) {
        String name = Name.getText().toString();
        String surname = surName.getText().toString();
        String email = Email.getText().toString();
        String password = passWord.getText().toString();

        if(name == "" || surname == "" || email == "" || password == "") {
            Toast.makeText(getApplicationContext(),
                    "Please Fill in all fields", Toast.LENGTH_LONG)
                    .show();

        }
        else
        {
            UserServiceImpl userService = new UserServiceImpl();

            User user = new User.Builder()

                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            userService.updateUser(user);
            Intent i = new Intent(this,NewsFeedActivity.class);

            startActivity(i);
        }

    }
}
