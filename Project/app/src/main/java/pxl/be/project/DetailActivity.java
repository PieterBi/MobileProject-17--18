package pxl.be.project;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private FragmentManager manager;
    private Book selectedBook;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        manager = getFragmentManager();
        selectedBook = getIntent().getExtras().getParcelable("selectedBook");
        showBookDetails();
    }

    private void showBookDetails() {
        FragmentDetail fragmentDetail = (FragmentDetail) manager.findFragmentById(R.id.frag_container);
        fragmentDetail.setBookDetails(selectedBook);
    }


}
