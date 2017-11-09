package pxl.be.project.DAL;

import android.provider.BaseColumns;

public class ReadingBuddyContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ReadingBuddyContract() {}

    /* Inner class that defines the table contents */
    public static class ReadingBuddy implements BaseColumns {
        public static final String TABLE_NAME = "favourites";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_RELEASEDATE = "releaseDate";
        public static final String COLUMN_NAME_PUBLISHER = "publisher";
        public static final String COLUMN_NAME_ISBN10 = "isbn10";
        public static final String COLUMN_NAME_ISBN13 = "isbn13";
        public static final String COLUMN_NAME_SUMMARY = "summary";
    }

}
