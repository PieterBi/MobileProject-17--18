package pxl.be.project;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FavoriteActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.rowlayout);
        List<Book> list = new ArrayList<Book>();
        /*The list should later on be populated with data from the api/favorite database
            But for now standard books are enough
         */
        for(int i = 0;i<5;i++)
        {
            list.add(StandardBook.getStandardBook());
        }

        Book[] books = new Book[list.size()];
        list.toArray(books);


        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, R.layout.rowlayout, R.id.label, books);
//        ListView lv = (ListView) findViewById(R.id.label);
//        lv.setAdapter(adapter);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
