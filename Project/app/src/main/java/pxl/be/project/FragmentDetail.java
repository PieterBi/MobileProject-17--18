package pxl.be.project;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pieter on 24/10/2017.
 */

public class FragmentDetail extends Fragment {

    private TextView tvTitle, tvAuthor, tvPublisher;
    private ImageView ivCover;
    private

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

    }
}
