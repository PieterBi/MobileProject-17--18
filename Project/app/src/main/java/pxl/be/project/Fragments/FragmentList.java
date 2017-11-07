package pxl.be.project.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pxl.be.project.Model.Book;
import pxl.be.project.CustomListAdapter;
import pxl.be.project.MyListener;
import pxl.be.project.R;
import pxl.be.project.DAL.ReadingBuddyDbHelper;

/**
 * Created by 11305181 on 24/10/2017.
 */

public class FragmentList extends Fragment {
    private Context context;

    private Book[] books;
    private EditText inputSearch;
    private ListView listView;
    private CustomListAdapter adapter;

    private Handler searchHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        context = getActivity().getApplicationContext();

        inputSearch = view.findViewById(R.id.et_inputSearch);
        inputSearch.addTextChangedListener(searchWatcher);
        listView = view.findViewById(R.id.lv_listOfBooks);

        List<Book> list = getFavouriteBooks();

        books = new Book[list.size()];
        list.toArray(books);
        Integer[] imgId = null;

        adapter = new CustomListAdapter(getActivity(), books, imgId);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = (String) listView.getAdapter().getItem(position).toString();
//                Toast.makeText(context, item + " selected", Toast.LENGTH_LONG).show();
                sendPosition(position);
            }
        });

        return view;
    }

    private ArrayList<Book> getFavouriteBooks(){
        ReadingBuddyDbHelper dbHelper = new ReadingBuddyDbHelper(getActivity().getApplicationContext());

        return dbHelper.getAllBooks();
    }

    private void sendPosition(int position) {
        MyListener myListener = (MyListener) getActivity();
        myListener.sendBook(position);
    }

    private void searchFavouriteBooks(String s) {adapter.getFilter().filter(s);
    }

    TextWatcher searchWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            searchHandler.postDelayed(new Runnable() {
                public void run() {
                    searchFavouriteBooks(inputSearch.getText().toString());
                }
            }, 1200);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}
