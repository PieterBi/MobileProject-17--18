package pxl.be.project;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11305181 on 24/10/2017.
 */

public class FragmentList extends Fragment {
    private Context context;

    private Book[] books;
    private EditText inputSearch;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        context = getActivity().getApplicationContext();

        inputSearch = view.findViewById(R.id.et_inputSearch);
        listView = view.findViewById(R.id.lv_listOfBooks);

        List<Book> list = new ArrayList<Book>();
        /*The list should later on be populated with data from the api/favorite database
            But for now standard books are enough
         */
        for (int i = 0; i < 5; i++) {
            list.add(StandardBook.getStandardBook());
        }

        books = new Book[list.size()];
        list.toArray(books);
        Integer[] imgId = null;

        CustomListAdapter adapter = new CustomListAdapter(getActivity(), books, imgId);
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

    private void sendPosition(int position) {
        MyListener myListener = (MyListener) getActivity();
        myListener.sendBook(position);
    }

}
