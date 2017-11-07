package pxl.be.project;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pieter on 16/10/2017.
 * Used for making book objects which retain information about books
 * And to easily pass the object between the listView and detailView
 */

public class Book implements Parcelable {
    //id is used to reference book objects from sqlite
    private int id;
    private String title;
    private String author;
    private String description;
    private String releaseDate;
    private String publisher;
    private String ISBN10;
    private String ISBN13;
    private String summary;

    //TODO add book cover

    public Book(String title, String author, String description, String releaseDate, String publisher, String ISBN10, String ISBN13, String summary) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
        this.summary = summary;
    }

    public Book(int id, String title, String author, String description, String releaseDate, String publisher, String ISBN10, String ISBN13, String summary) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.ISBN10 = ISBN10;
        this.ISBN13 = ISBN13;
        this.summary = summary;
    }

    public int getId() {
        return id;
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

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        description = in.readString();
        releaseDate = in.readString();
        publisher = in.readString();
        ISBN10 = in.readString();
        ISBN13 = in.readString();
        summary = in.readString();
    }

    @Override
    public String toString() {
        return this.title.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(description);
        dest.writeString(releaseDate);
        dest.writeString(publisher);
        dest.writeString(ISBN10);
        dest.writeString(ISBN13);
        dest.writeString(summary);
    }


    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
