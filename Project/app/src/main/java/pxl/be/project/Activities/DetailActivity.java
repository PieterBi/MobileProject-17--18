package pxl.be.project.Activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import pxl.be.project.Model.Book;
import pxl.be.project.Fragments.FragmentDetail;
import pxl.be.project.R;

public class DetailActivity extends BaseActivity {

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

    @Override
    public void update() {
        //do nothing, FavouriteActivity will update Favourite onResume()
    }
}
