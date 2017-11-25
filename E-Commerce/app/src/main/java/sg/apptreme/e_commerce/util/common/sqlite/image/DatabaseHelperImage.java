package sg.apptreme.e_commerce.util.common.sqlite.image;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public class DatabaseHelperImage extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "database_name";

    // Table Names
    private static final String DB_TABLE = "table_image";

    // column names
    private static final String KEY_NAME = "image_name";
    private static final String KEY_IMAGE = "image_data";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "(" +
            KEY_NAME + " TEXT," +
            KEY_IMAGE + " BLOB);";

    public DatabaseHelperImage(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        // create new table
        onCreate(db);
    }

    public void addEntry(String name, byte[] image) throws SQLiteException {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_IMAGE, image);
        getWritableDatabase().insert(DB_TABLE, null, cv);
    }

    public byte[] getEntry(String name) {
        byte[] image = new byte[0];
        String selectQuery = "SELECT * FROM " + DB_TABLE + " where " + KEY_NAME + " = '" + name + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    image = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
                }
            } finally {
                cursor.close();
            }
        }
        return image;
    }
}