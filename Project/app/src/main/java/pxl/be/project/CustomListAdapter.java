package pxl.be.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pieter on 17/10/2017.
 * This is used to make the custom row view
 */

public class CustomListAdapter extends ArrayAdapter implements Filterable {

    private final Activity context;
    private final Book[] originalBookArray;
    private Book[] filteredBookList;
    private final Integer[] imgId;//Image files

    public CustomListAdapter(Activity context, Book[] bookArray, Integer[] imgId) {
        super(context, R.layout.rowlayout, bookArray);

        this.context = context;
        this.originalBookArray = bookArray;
        this.filteredBookList = bookArray;
        this.imgId = imgId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.rowlayout, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.tv_bookTitle);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iv_bookIcon);

        txtTitle.setText(filteredBookList[position].toString());
        imageView.setImageResource(R.drawable.book1600);//TODO make the imageArray

        return rowView;
    }

    //For this helper method, return based on filteredBookArray
    public int getCount() {
        return filteredBookList.length;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                ArrayList<Book> filterResultsData = new ArrayList<Book>();

                //If there's nothing to filter on, return the original data for your list
                if (charSequence == null || charSequence.length() == 0) {
                    results.values = new ArrayList<>(Arrays.asList(originalBookArray));
                    results.count = originalBookArray.length;
                } else {
                    for (Book book : originalBookArray) {
                        if (book.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()))
                            filterResultsData.add(book);
                    }
                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                ArrayList<Book> resultList = (ArrayList<Book>) filterResults.values;
                filteredBookList = resultList.toArray(new Book[resultList.size()]);
                notifyDataSetChanged();
            }
        };
    }
}
