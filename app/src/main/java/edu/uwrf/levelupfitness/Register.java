package edu.uwrf.levelupfitness;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

public class Register extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    //Button _registerButton;
    EditText _firstNameText;
    EditText _lastNameText;
    EditText _passwordText;
    EditText _phoneText;
    EditText _emailText;
    EditText _weightText;
    EditText _heightText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        openHelper=new DatabaseHelper(this);
        Button _registerButton = (Button)findViewById(R.id.regButton);
        _firstNameText=(EditText)findViewById(R.id.firstNameText);
        _lastNameText=(EditText)findViewById(R.id.lastNameText);
        _passwordText=(EditText)findViewById(R.id.passwordText);
        _phoneText=(EditText)findViewById(R.id.phoneText);
        _emailText=(EditText)findViewById(R.id.emailText);
        _weightText=(EditText)findViewById(R.id.weightText);
        _heightText=(EditText)findViewById(R.id.heightText);


        _registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname = _firstNameText.getText().toString();
                String lname = _lastNameText.getText().toString();
                String pass1 = _passwordText.getText().toString();
                String phone1 = _phoneText.getText().toString();
                String email = _emailText.getText().toString();
                String weight1 = _weightText.getText().toString();
                String height1 = _heightText.getText().toString();
                insertdata(fname,lname,pass1,phone1,email,weight1,height1);

                // insert the info into database
                Contact c = new Contact();
                c.setFirstName(fname);
                c.setLastName(lname);
                c.setUsername(email);
                c.setHeight(height1);
                c.setWeight(weight1);
                c.setPass(pass1);
                c.setPhone(phone1);
                helper.insertContact(c);

                Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();

            }
        });

        Button backButton1 = (Button) findViewById(R.id.backButton1);
        backButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    // insert into database
    public void insertdata(String fname, String lname, String pass, String phone, String email, String weight, String height){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLOUMN_FirstName, fname);
        contentValues.put(DatabaseHelper.COLOUMN_LastName, lname);
        contentValues.put(DatabaseHelper.COLOUMN_PASSWORD, pass);
        contentValues.put(DatabaseHelper.COLOUMN_PHONE, phone);
        contentValues.put(DatabaseHelper.COLOUMN_USERNAME, email);
        contentValues.put(DatabaseHelper.COLOUMN_WEIGHT, weight);
        contentValues.put(DatabaseHelper.COLOUMN_HEIGHT, height);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }


}
