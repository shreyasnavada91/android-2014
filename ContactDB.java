package com.example.android.contacts;
import android.content.ContentValues; import android.content.Context;
import android.database.Cursor; import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase; import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class ContactDB {
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "id"; public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    private static final String DATABASE_NAME = "contacts.db"; private static final int DATABASE_VERSION = 3;
    private DatabaseHelper databaseHelper; private SQLiteDatabase database;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " ("
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT NOT NULL, "
        + COLUMN_EMAIL + " TEXT NOT NULL);";
    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }
        @Override public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE); }
        @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("ContactDB", "Upgrading database"); db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS); onCreate(db);
        }
    }
    public ContactDB(Context context) { this.databaseHelper = new DatabaseHelper(context); }
    public void open() throws SQLException { this.database = databaseHelper.getWritableDatabase(); }
    public void close() { if (databaseHelper != null) databaseHelper.close(); }
    public long insertContact(String name, String email) {
        ContentValues values = new ContentValues(); values.put(COLUMN_NAME, name); values.put(COLUMN_EMAIL, email);
        if (database != null) return database.insert(TABLE_CONTACTS, null, values); return -1;
    }
    public java.util.List<Contact> getAllContacts() {
        java.util.List<Contact> contacts = new java.util.ArrayList<>();
        if (database != null) {
            Cursor cursor = database.query(TABLE_CONTACTS, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                Contact contact = new Contact(); contact.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))); contacts.add(contact);
            } cursor.close();
        }
        return contacts;
    }
}


// JOIN queries