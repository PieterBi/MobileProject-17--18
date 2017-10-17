package pxl.be.project;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Gebruiker on 17/10/2017.
 */

public class CustomArrayAdapter extends ArrayAdapter {

    List<Book> bookList;
    public CustomArrayAdapter(Context context, List<Book> list)
    {
        super(context,0,list);
        bookList = list;
    }

    @Override
    public View getView(int position, View conevertView, ViewGroep parent)
    {
        ViewHolder
    }
}
