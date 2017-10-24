package pxl.be.project;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FavouriteActivity extends AppCompatActivity implements MyListener{

    FragmentManager manager = getFragmentManager();
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
    }

    @Override
    public void sendBook(int position)
    {
        ListView listView = (ListView) findViewById(R.id.lv_listOfBooks);
        book = (Book) listView.getItemAtPosition(position);
        sendDataToFragmentFavourite();
    }

    private void sendDataToFragmentFavourite()
    {
        FragmentDetail fragmentDetail = (FragmentDetail) manager.findFragmentById(R.id.frag_detail);
        fragmentDetail.setBookDetails(book);
    }
}
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_favourite);
//        List<Book> list = new ArrayList<Book>();
//        /*The list should later on be populated with data from the api/favorite database
//            But for now standard books are enough
//         */
//        for (int i = 0; i < 5; i++) {
//            list.add(StandardBook.getStandardBook());
//        }
//
//        books = new Book[list.size()];
//        list.toArray(books);
//        Integer[] imgId = null;
//        inputSearch = (EditText) findViewById(R.id.et_inputSearch);
//
//        CustomListAdapter adapter = new CustomListAdapter(this, books, imgId);
//        listView = (ListView) findViewById(R.id.lv_listOfBooks);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = (String) listView.getAdapter().getItem(position).toString();
//                Toast.makeText(getApplicationContext(), item + " selected", Toast.LENGTH_LONG).show();
//            }
//        });

//        inputSearch.setTextChangedListener(new TextWatcher()
//        {
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                // TODO Make it filter
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
//                                          int arg3) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                // TODO Auto-generated method stub
//            }
//        });
//    }
//}
        //    Used for extends ListActivity rather then Activity commented for future refrence
//        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, R.layout.rowlayout, R.id.label, books);
//        setListAdapter(adapter);

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id)
//    {
//        String item = (String) getListAdapter().getItem(position).toString();
//        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
//    }
