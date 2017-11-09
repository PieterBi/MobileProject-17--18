package pxl.be.project.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pxl.be.project.Model.Book;
import pxl.be.project.Fragments.FragmentDetail;
import pxl.be.project.MyListener;
import pxl.be.project.R;
import pxl.be.project.DAL.ReadingBuddyDbHelper;
import pxl.be.project.Model.StandardBook;
//import pxl.be.project.SearchTask;

public class FavouriteActivity extends AppCompatActivity implements MyListener {

    private FragmentManager manager;
    private Book selectedBook;
    private Boolean wideMode;
    private Button searchButton;
    private String mJSONURLString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        manager = getFragmentManager();
        checkLayoutMode(getResources().getConfiguration());

        //use this function to add a dummy entry in the local sqlite db
        //testSaveBook();

        //testRemoveBook();

        searchButton = (Button) findViewById(R.id.btn_search);
        mJSONURLString = "http://isbndb.com/api/v2/json/VI15DHE7/books?q=";

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchEditText = (EditText) findViewById(R.id.et_inputSearch);
                String searchText = searchEditText.getText().toString();
                searchText = searchText.replaceAll(" ","_");
                mJSONURLString += searchText;

                // Initialize a new RequestQueue instance
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                // Initialize a new JsonArrayRequest instance
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        mJSONURLString,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Do something with response
                                //mTextView.setText(response.toString());

                                // Process the JSON
                                List<Book> bookList = new ArrayList<Book>();
                                Toast toast = Toast.makeText(getApplicationContext(), "working1", Toast.LENGTH_LONG);
                                toast.show();
                                try{
                                    Toast toast2 = Toast.makeText(getApplicationContext(), "working2", Toast.LENGTH_LONG);
                                    toast2.show();
                                    // Loop through the array elements
                                    JSONArray responseArray = response.getJSONArray("Data");
                                    for(int i=0;i<responseArray.length();i++){
                                        // Get current json object
                                        JSONObject book = responseArray.getJSONObject(i);

                                        // Get the current book (json object) data
                                        String title = book.getString("title_latin");
                                        String author = book.getJSONObject("author_data").getString("name");
                                        String description = book.getString("physical_description_text");
                                        String releaseDate = "-";
                                        String publisher = book.getString("publisher_name");
                                        String ISBN10 = book.getString("isbn10");
                                        String ISBN13 = book.getString("isbn13");
                                        String summary = book.getString("summary");

                                        Book newBook = new Book(title,author,description,releaseDate,publisher,ISBN10,ISBN13,summary);
                                        bookList.add(newBook);
                                    }
                                    Toast toast3 = Toast.makeText(getApplicationContext(), "working3", Toast.LENGTH_LONG);
                                    toast3.show();
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                                Toast toast = Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }
                );

                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonObjectRequest);
            }
        });
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

//    public void searchBooksOnline()
//    {
//        new SearchTask(FavouriteActivity.this).execute();
//
//
//    }

    private void testRemoveBook()
    {
        ReadingBuddyDbHelper dbHelper = new ReadingBuddyDbHelper(getApplicationContext());

        Book b = new Book(2, StandardBook.getStandardBook());
        dbHelper.deleteBook(b);
    }

}
