package pxl.be.project.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import pxl.be.project.Model.Book;

import static pxl.be.project.DAL.ReadingBuddyContract.*;

public class ReadingBuddyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ReadingBuddy.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ReadingBuddy.TABLE_NAME + " (" +
                    ReadingBuddy._ID + " INTEGER PRIMARY KEY," +
                    ReadingBuddy.COLUMN_NAME_TITLE + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_AUTHOR + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_RELEASEDATE + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_PUBLISHER + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_ISBN10 + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_ISBN13 + " TEXT," +
                    ReadingBuddy.COLUMN_NAME_SUMMARY + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReadingBuddy.TABLE_NAME;

    public ReadingBuddyDbHelper(Context context) {
        super(context, ReadingBuddy.TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop and remake db
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void insertBook(Book b) {
        new InsertTask().execute(b);
        Log.d("SQL", "starting task");
    }

    public void deleteBook(Book b) {
        new RemoveTask().execute(b);
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + ReadingBuddy.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Run through the cursor
        while(cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ReadingBuddy._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_TITLE));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_AUTHOR));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_DESCRIPTION));
            String reldate = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_RELEASEDATE));
            String pub = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_PUBLISHER));
            String isbn10 = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_ISBN10));
            String isbn13 = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_ISBN13));
            String summary = cursor.getString(cursor.getColumnIndexOrThrow(ReadingBuddy.COLUMN_NAME_SUMMARY));

            Book b = new Book(id, title, author, desc, reldate, pub, isbn10, isbn13, summary);

            bookList.add(b);
        }
        cursor.close();

        return bookList;
    }

    private class InsertTask extends AsyncTask<Book, Void, Long> {

        @Override
        protected Long doInBackground(Book... books) {
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(ReadingBuddy.COLUMN_NAME_TITLE, books[0].getTitle());
            values.put(ReadingBuddy.COLUMN_NAME_AUTHOR, books[0].getAuthor());
            values.put(ReadingBuddy.COLUMN_NAME_DESCRIPTION, books[0].getDescription());
            values.put(ReadingBuddy.COLUMN_NAME_RELEASEDATE, books[0].getReleaseDate());
            values.put(ReadingBuddy.COLUMN_NAME_PUBLISHER, books[0].getPublisher());
            values.put(ReadingBuddy.COLUMN_NAME_ISBN10, books[0].getISBN10());
            values.put(ReadingBuddy.COLUMN_NAME_ISBN13, books[0].getISBN13());
            values.put(ReadingBuddy.COLUMN_NAME_SUMMARY, books[0].getSummary());


            Log.d("SQL", "inserting new row");
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(ReadingBuddy.TABLE_NAME, null, values);

            return newRowId;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            Log.d("SQL", "new row id: " + Long.toString(aLong));
        }
    }

    private class RemoveTask extends AsyncTask<Book, Void, Void> {

        @Override
        protected Void doInBackground(Book... books) {
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Define 'where' part of query.
            String selection = ReadingBuddy._ID + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { books[0].toString() };
            // Issue SQL statement.
            db.delete(ReadingBuddy.TABLE_NAME, selection, selectionArgs);

            return null;
        }
    }
}

