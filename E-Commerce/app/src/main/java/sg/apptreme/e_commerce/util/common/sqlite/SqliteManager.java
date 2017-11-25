package sg.apptreme.e_commerce.util.common.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import sg.apptreme.e_commerce.util.common.sqlite.model.SqlitePreference;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public class SqliteManager extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SqliteManager";

    // Contacts table name
    private static final String TABLE_PREFERENCE = "preference";

    // Contacts Table Columns names
    private static final String KEY_CONSTANT = "constant";
    private static final String KEY_VALUE = "value";
    private static final String KEY_TIMESTAMP = "timestamp";

    public SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PREFERENCE_TABLE = "CREATE TABLE " + TABLE_PREFERENCE + "(" + KEY_CONSTANT + " TEXT PRIMARY KEY,"
                + KEY_VALUE + " TEXT," + KEY_TIMESTAMP + " TEXT)";
        db.execSQL(CREATE_PREFERENCE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREFERENCE);

        // Create tables again
        onCreate(db);
    }

    public void addPreference(SqlitePreference preference) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CONSTANT, preference.getConstant());
        values.put(KEY_VALUE, preference.getValue());
        values.put(KEY_TIMESTAMP, preference.getTimestamp());

        db.insert(TABLE_PREFERENCE, null, values);
        db.close(); // Closing database connection
    }

    public void upsertPreference(SqlitePreference preference) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_CONSTANT, preference.getConstant());
            values.put(KEY_VALUE, preference.getValue());
            values.put(KEY_TIMESTAMP, preference.getTimestamp());

            db.insertWithOnConflict(TABLE_PREFERENCE, null, values, SQLiteDatabase.CONFLICT_REPLACE);

//            Crashlytics.log(Log.INFO, "Preference SQLite", "upsert : " + preference.getConstant());
            db.close();
        } catch (Exception e) {
            Crashlytics.log(Log.ERROR, "Error database ", e.getMessage());
        }

    }

    public SqlitePreference getPreference(String constant) {
        SqlitePreference preference = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_PREFERENCE, new String[]{
                            KEY_CONSTANT, KEY_VALUE, KEY_TIMESTAMP}, KEY_CONSTANT + "=?",
                    new String[]{constant.toLowerCase()}, null, null, null, null);
            if (cursor != null) {
                try {
                    if (!(cursor.moveToFirst()) || cursor.getCount() == 0) {
                        //cursor is empty
//                    Crashlytics.log(Log.INFO, "Preference SQLite", "preference not found");
                        return null;
                    } else {
                        preference = new SqlitePreference(cursor.getString(0),
                                cursor.getString(1), Long.parseLong(cursor.getString(2)));

//                    Crashlytics.log(Log.INFO, "Preference SQLite", "get : "+preference.getConstant());
                    }
                } finally {
                    cursor.close();
                }
            } else {
//                Crashlytics.log(Log.INFO, "Preference SQLite", "preference not found");
            }
        } catch (Exception e) {
            Crashlytics.log(Log.ERROR, "Error database ", e.getMessage());
        }

        return preference;
    }

    public List<SqlitePreference> getAllPreference() {
        List<SqlitePreference> preferenceList = new ArrayList<SqlitePreference>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PREFERENCE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    SqlitePreference preference = new SqlitePreference();
                    preference.setConstant(cursor.getString(0));
                    preference.setValue(cursor.getString(1));
                    preference.setTimestamp(Long.parseLong(cursor.getString(2)));

                    preferenceList.add(preference);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return preferenceList;
    }

    public int getPreferenceCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PREFERENCE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updatePreference(SqlitePreference preference) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CONSTANT, preference.getConstant());
        values.put(KEY_VALUE, preference.getValue());
        values.put(KEY_TIMESTAMP, preference.getTimestamp());

        // updating row
        return db.update(TABLE_PREFERENCE, values, KEY_CONSTANT + " = ?",
                new String[]{String.valueOf(preference.getConstant())});
    }

    public void deletePreference(String keyConstant) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PREFERENCE, KEY_CONSTANT + " = ?",
                new String[]{keyConstant});
        db.close();
    }
}
