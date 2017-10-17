package pxl.be.project;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pieter on 16/10/2017.
 * Used for making book objects which retain information about books
 * And to easily pass the object between the listView and detailView
 */

public class Book implements Parcelable{
    private String title, author, description, releaseDate, publisher;

    public Book(String title, String author, String description, String releaseDate, String publisher)
    {
        this.title = title;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
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

    public Book(Parcel in)
    {
        String[] data = new String[5];

        in.readStringArray(data);

        this.title = data[0];
        this.author = data[1];
        this.description = data[2];
        this.releaseDate = data[3];
        this.publisher = data[4];
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
                this.publisher
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
