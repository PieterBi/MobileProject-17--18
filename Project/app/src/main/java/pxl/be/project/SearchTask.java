package pxl.be.project;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.attr.id;

/**
 * Created by Pieter on 7/11/2017.
 */


public class SearchTask extends AsyncTask<Void, Void, String> {
    private Exception exception;
    private ListView listOfBooks;
    private EditText searchEditText;
    private Activity mActivity;

    //constructor
    public SearchTask(Activity activity)
    {
        mActivity = activity;
    }

    protected void onPreExecute()
    {
        listOfBooks = (ListView) mActivity.findViewById(R.id.lv_listOfBooks);
        searchEditText = (EditText) mActivity.findViewById(R.id.et_inputSearch);
    }

    protected String doInBackground(Void... urls) {

        Editable searchText = searchEditText.getText();
        String email = emailText.getText().toString();
        // Do some validation here

        try {
            URL url = new URL(API_URL + "email=" + email + "&apiKey=" + API_KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);
        responseView.setText(response);
    }

}
