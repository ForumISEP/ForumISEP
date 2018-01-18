package isep.forumisep.helper;

/**
 * Created by linfengwang on 10/11/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "TestConnDB";
    // Login table name
    private static final String TABLE_USER = "users";
    // Login Table Columns names
    private static final String KEY_UID = "id";
    private static final String KEY_EMAIL = "email";

    public static final String  CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + KEY_UID + " INTEGER PRIMARY KEY,"
            + KEY_EMAIL + " TEXT UNIQUE)";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String uid,String email) {
        //getWritableDatabase:Create and/or open a database that will be used for reading and writing.
        SQLiteDatabase db = this.getWritableDatabase();

        //Creates an empty set of values using the default initial size
        ContentValues values = new ContentValues();
        //Adds a value to the set.
        values.put(KEY_UID, uid);
        values.put(KEY_EMAIL, email);
        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     * */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        //Returns the numbers of rows in the cursor.
        while (cursor.getCount() > 0) {
            user.put("uid", cursor.getString(1));
            user.put("email", cursor.getString(2));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

    /**
     * Re crate database Delete all tables and create them again
     * */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from sqlite");
    }


}
