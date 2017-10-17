package pxl.be.project;

/**
 * Created by Gebruiker on 16/10/2017.
 */

public class StandardBook {

    public static Book getStandardBook()
    {
        return new Book("title","Author","Description","ReleaseDate","Publisher");
    }

}
