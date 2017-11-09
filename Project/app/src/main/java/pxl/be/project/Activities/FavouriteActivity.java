package pxl.be.project.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import pxl.be.project.Model.Book;
import pxl.be.project.Fragments.FragmentDetail;
import pxl.be.project.MyListener;
import pxl.be.project.R;
import pxl.be.project.DAL.ReadingBuddyDbHelper;
import pxl.be.project.Model.StandardBook;
import pxl.be.project.SearchTask;

public class FavouriteActivity extends AppCompatActivity implements MyListener {

    private FragmentManager manager;
    private Book selectedBook;
    private Boolean wideMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        manager = getFragmentManager();
        checkLayoutMode(getResources().getConfiguration());

        //use this function to add a dummy entry in the local sqlite db
        //testSaveBook();

        //testRemoveBook();
    }

    @Override
    public void onConfigurationChanged(Configuration _newConfig) {
        super.onConfigurationChanged(_newConfig);

        checkLayoutMode(getResources().getConfiguration());
    }

    @Override
    public void sendBook(int position)
    {
        ListView listView = (ListView) findViewById(R.id.lv_listOfBooks);
        selectedBook = (Book) listView.getItemAtPosition(position);
        sendDataToFragmentFavourite();
    }

    private void checkLayoutMode(Configuration config) {
        int orientation = config.orientation;

        if (orientation == config.ORIENTATION_PORTRAIT) {
            wideMode = false;
        } else if (orientation == config.ORIENTATION_LANDSCAPE) {
            wideMode = true;
        }
    }

    private void sendDataToFragmentFavourite()
    {
        if (wideMode) {
            //if phone is in landscape, use detail fragment to show data
            FragmentDetail fragmentDetail = (FragmentDetail) manager.findFragmentById(R.id.frag_detail);
            fragmentDetail.setBookDetails(selectedBook);
        }
        else {
            //if phone is in portrait, create new activity with intent
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("selectedBook", selectedBook);
            startActivity(intent);
        }
    }

    private void testSaveBook(){
        ReadingBuddyDbHelper dbHelper = new ReadingBuddyDbHelper(getApplicationContext());

        Book sqlBook = StandardBook.getStandardBook();
        sqlBook.setTitle("SQL Book");
        dbHelper.insertBook(sqlBook);

    }

    public void searchBooksOnline()
    {
        new SearchTask(FavouriteActivity.this).execute();
    }

    private void testRemoveBook()
    {
        ReadingBuddyDbHelper dbHelper = new ReadingBuddyDbHelper(getApplicationContext());

        Book b = new Book(2, StandardBook.getStandardBook());
        dbHelper.deleteBook(b);
    }

}