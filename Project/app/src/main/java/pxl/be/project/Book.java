package pxl.be.project;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pieter on 16/10/2017.
 * Used for making book objects which retain information about books
 * And to easily pass the object between the listView and detailView
 */

public class Book implements Parcelable{
    private String title;
    private String author;
    private String description;
    private String releaseDate;
    private String publisher;
    private String ISBN10;
    private String ISBN13;
    private String summary;

    //TODO add book cover

    public Book(String title, String author, String description, String releaseDate, String publisher, String ISBN10, String ISBN13, String summary)
    {
        this.title = title;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN10()
    {
        return ISBN10;
    }

    public void setISBN10(String ISBN10)
    {
        this.ISBN10 = ISBN10;
    }

    public String getISBN13()
    {
        return ISBN13;
    }

    public void setISBN13(String ISBN13)
    {
        this.ISBN13 = ISBN13;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Book(Parcel in)
    {
        String[] data = new String[8];

        in.readStringArray(data);

        this.title = data[0];
        this.author = data[1];
        this.description = data[2];
        this.releaseDate = data[3];
        this.publisher = data[4];
        this.ISBN10 = data[5];
        this.ISBN13 = data[6];
        this.summary = data[7];
    }

    @Override
    public String toString()
    {
        return this.title.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.title,
                this.author,
                this.description,
                this.releaseDate,
                this.publisher,
                this.ISBN10,
                this.ISBN13,
                this.summary
        });

    }

    public static final Parcelable.Creator<Book> CREATOR
            = new Parcelable.Creator<Book>()
    {
        public Book createFromParcel(Parcel in)
        {
            return new Book(in);
        }

        public Book[] newArray(int size)
        {
            return new Book[size];
        }
    };
}
