package edu.uwrf.levelupfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.uwrf.workoutapp.R;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log In");

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText a = (EditText) findViewById(R.id.usernameText);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.passwordText);
                String pass = b.getText().toString();
                String password = helper.searchPass(str);

                if(pass.equals(password)) {
                  // MainActivity.sendUsername(str);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("Username", str);
                    Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                else{
                    Toast temp = Toast.makeText(LoginActivity.this,
                            "Username and password do not match!",
                            Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
