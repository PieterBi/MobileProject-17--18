package pxl.be.project.DAL;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import pxl.be.project.Activities.BaseActivity;
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

    public void insertBook(Book b, Activity a) {
        Params params = new Params(b, a);
        new InsertTask().execute(params);
        Log.d("SQL", "starting task");
    }

    public void deleteBook(Book b, Activity a)
    {
        Log.d("SQL", "Executing remove task");
        Params params = new Params(b, a);
        new RemoveTask().execute(params);
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

    private class InsertTask extends AsyncTask<Params, Void, Params> {

        @Override
        protected Params doInBackground(Params... params) {
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Get the book from Params
            Book book = params[0].getBook();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(ReadingBuddy.COLUMN_NAME_TITLE, book.getTitle());
            values.put(ReadingBuddy.COLUMN_NAME_AUTHOR, book.getAuthor());
            values.put(ReadingBuddy.COLUMN_NAME_DESCRIPTION, book.getDescription());
            values.put(ReadingBuddy.COLUMN_NAME_RELEASEDATE, book.getReleaseDate());
            values.put(ReadingBuddy.COLUMN_NAME_PUBLISHER, book.getPublisher());
            values.put(ReadingBuddy.COLUMN_NAME_ISBN10, book.getISBN10());
            values.put(ReadingBuddy.COLUMN_NAME_ISBN13, book.getISBN13());
            values.put(ReadingBuddy.COLUMN_NAME_SUMMARY, book.getSummary());


            Log.d("SQL", "inserting new row");
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(ReadingBuddy.TABLE_NAME, null, values);
            Log.d("SQL", "new row id" + newRowId);

            return params[0];
        }

        @Override
        protected void onPostExecute(Params aParams) {

            ((BaseActivity)aParams.getActivity()).update();
        }
    }

    private class RemoveTask extends AsyncTask<Params, Void, Activity> {

        @Override
        protected Activity doInBackground(Params... params) {
            // Gets the data repository in write mode
            SQLiteDatabase db = getWritableDatabase();

            // Get the book from Params
            Book book = params[0].getBook();

            // Define 'where' part of query.
            String selection = ReadingBuddy._ID + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { Integer.toString(book.getId()) };
            // Issue SQL statement.
            db.delete(ReadingBuddy.TABLE_NAME, selection, selectionArgs);
            Log.d("SQL", "deleting ... ");

            return params[0].getActivity();
        }

        @Override
        protected void onPostExecute(Activity aActivity) {
            ((BaseActivity)aActivity).update();
        }
    }

    private class Params {
        private Book _book;
        private Activity _activity;

        public Params(Book b, Activity a) {
            _book = b;
            _activity = a;
        }

        public Book getBook() {
            return _book;
        }

        public Activity getActivity() {
            return _activity;
        }
    }
}

