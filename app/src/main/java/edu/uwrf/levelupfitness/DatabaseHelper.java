package edu.uwrf.levelupfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //private vs public
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLOUMN_ID = "ID";
    public static final String COLOUMN_USERNAME = "username"; // this is the email
    public static final String COLOUMN_PASSWORD = "pass"; // password
    public static final String COLOUMN_FirstName = "FirstName";
    public static final String COLOUMN_LastName = "LastName";
    public static final String COLOUMN_HEIGHT = "height"; // in inches
    public static final String COLOUMN_WEIGHT = "weight"; // in pounds
    public static final String COLOUMN_PHONE = "phone";
    public static final String COLOUMN_WORKOUT1 = "WO1";
    public static final String COLOUMN_WORKOUT2 = "WO2";
    public static final String COLOUMN_WORKOUT3 = "WO3";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (ID INTEGER PRIMARY KEY,FirstName TEXT,"
            +"LastName TEXT, pass TEXT,phone TEXT,username TEXT, weight TEXT, height TEXT,WO1 TEXT,WO2 TEXt,WO3 TEXT)";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL("CREATE TABLE" + TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTO INCREMENT, )");
        db.execSQL(TABLE_CREATE);
        // db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,"
        //         +"LastName TEXT, pass TEXT,phone TEXT,username TEXT, weight TEXT, height TEXT)");
        this.db = db;
    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLOUMN_ID, count);
        values.put(COLOUMN_USERNAME, c.getUserName());
        values.put(COLOUMN_PASSWORD, c.getPass());
        values.put(COLOUMN_FirstName, c.getFirstName());
        values.put(COLOUMN_LastName, c.getLastName());
        values.put(COLOUMN_HEIGHT, c.getHeight());
        values.put(COLOUMN_WEIGHT, c.getWeight());
        values.put(COLOUMN_PHONE, c.getPhone());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "select username, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());
        }

        return b;
    }

    public void addWorkout(String jsonString, String username){








        db = this.getReadableDatabase();
        String query = "select username from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(username)){
                    if( cursor.getString(7) == null) {
                        b = cursor.getString(7);
                        break;
                    }
                    else if(cursor.getString(8) == null) {
                      //  jsonString.
                        break;
                    }
                    else{
                        b = cursor.getString(7);
                        break;
                    }
                }


            }while(cursor.moveToNext());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME; // drop older table if it exist
        db.execSQL(query);
        this.onCreate(db);
    }
}
