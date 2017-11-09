package pxl.be.project.Model;

/**
 * Created by Pieter on 16/10/2017.
 * A standard book used for testing the program without calling the api
 */

public class StandardBook {

    public static Book getStandardBook()
    {
        return new Book("Standard Title",
                "Standard Author",
                "Standard Description",
                "Standard ReleaseDate",
                "Standard Publisher",
                "Standard ISBN10",
                "Standard ISBN13",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    }

}
