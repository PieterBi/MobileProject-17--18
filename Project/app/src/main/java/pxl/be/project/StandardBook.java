package pxl.be.project;

/**
 * Created by Pieter on 16/10/2017.
 * A standard book used for testing the program without calling the api
 */

public class StandardBook {

    public static Book getStandardBook()
    {
        return new Book("title","Author","Description","ReleaseDate","Publisher");
    }

}
