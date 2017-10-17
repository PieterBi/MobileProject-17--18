package pxl.be.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pieter on 17/10/2017.
 * This is used to make the custom row view
 */

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;
    private final Book[] bookArray;
    private final Integer[] imgId;//Image files
    public CustomListAdapter(Activity context, Book[] bookArray, Integer[] imgId)
    {
        super(context, R.layout.rowlayout,bookArray);

        this.context = context;
        this.bookArray = bookArray;
        this.imgId = imgId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rowlayout, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.tv_bookTitle);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iv_bookIcon);

        txtTitle.setText(bookArray[position].toString());
        imageView.setImageResource(R.drawable.book1600);//TODO make the imageArray

        return rowView;
    }
}
