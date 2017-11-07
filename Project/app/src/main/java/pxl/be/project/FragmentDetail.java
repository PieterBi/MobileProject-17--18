package pxl.be.project;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pieter & 11305181 on 24/10/2017.
 */

public class FragmentDetail extends Fragment {

    private TextView tvTitle, tvAuthor, tvPublisher, tvRelease, tvISBN10, tvISBN13, tvSummary;
    private ImageView ivCover;
    //TODO add button to change text depending on where we are at ( favorites or searching for favourites

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tvTitle = (TextView) view.findViewById(R.id.tv_bookTitle);
        ivCover = (ImageView) view.findViewById(R.id.iv_bookIcon);
        tvAuthor = (TextView) view.findViewById(R.id.tv_bookAuthors);
        tvPublisher = (TextView) view.findViewById(R.id.tv_publisher);
        tvRelease = (TextView) view.findViewById(R.id.tv_release);
        tvISBN10 = (TextView) view.findViewById(R.id.tv_isbn10);
        tvISBN13 = (TextView) view.findViewById(R.id.tv_isbn13);
        tvSummary = (TextView) view.findViewById(R.id.tv_bookSummary);
        return view;
    }

    public void setBookDetails(Book book)
    {
        //TODO add book cover
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvPublisher.setText(book.getPublisher());
        tvRelease.setText(book.getReleaseDate());
        tvISBN10.setText(book.getISBN10());
        tvISBN13.setText(book.getISBN13());
        tvSummary.setText(book.getSummary());
    }
}

