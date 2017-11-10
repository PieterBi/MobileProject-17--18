package pxl.be.project.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import pxl.be.project.Activities.BaseActivity;
import pxl.be.project.DAL.ReadingBuddyDbHelper;
import pxl.be.project.Model.Book;
import pxl.be.project.R;

/**
 * Created by Pieter & 11305181 on 24/10/2017.
 */

public class FragmentDetail extends Fragment implements View.OnClickListener {

    private Book selectedBook;

    private TextView tvTitle, tvAuthor, tvPublisher, tvRelease, tvISBN10, tvISBN13, tvSummary;
    private Button btnFavourites;
    private ImageView ivCover;
    private boolean isSqlBook;

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
        btnFavourites = (Button) view.findViewById((R.id.btn_favouritesButton));
        btnFavourites.setOnClickListener(this);
        return view;
    }

    public void setBookDetails(Book book) {
        selectedBook = book;

        //TODO add book cover
        tvTitle.setText(selectedBook.getTitle());
        tvAuthor.setText(selectedBook.getAuthor());
        tvPublisher.setText(selectedBook.getPublisher());
        tvRelease.setText(selectedBook.getReleaseDate());
        tvISBN10.setText(selectedBook.getISBN10());
        tvISBN13.setText(selectedBook.getISBN13());
        tvSummary.setText(selectedBook.getSummary());

        setIsSqlBook(selectedBook.getId());
    }

    private void setIsSqlBook(int id) {
        //if the book's ID is assigned, it comes from the local db
        if (id != 0){
            btnFavourites.setText("Remove From favourites");
            isSqlBook = true;
        } else {
            btnFavourites.setText("Add to Favourites");
            isSqlBook = false;
        }
    }

    private void switchIsSqlBook() {
        isSqlBook = !isSqlBook;

        if (isSqlBook){
            btnFavourites.setText("Remove From favourites");
        } else {
            btnFavourites.setText("Add to Favourites");
        }
    }


    @Override
    public void onClick(View view) {
        ReadingBuddyDbHelper dbHelper = new ReadingBuddyDbHelper(getActivity().getApplicationContext());

        if (isSqlBook) {
            dbHelper.deleteBook(selectedBook, getActivity());
        } else {
            dbHelper.insertBook(selectedBook, getActivity());
        }

        switchIsSqlBook();
    }
}

