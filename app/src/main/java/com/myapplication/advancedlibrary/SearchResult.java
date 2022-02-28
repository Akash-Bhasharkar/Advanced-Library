package com.myapplication.advancedlibrary;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity {

    RecyclerView view_text;
    RequestQueue requestQueue;
    public List<Books> booksList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        view_text = findViewById(R.id.view_text);
        view_text.setHasFixedSize(true);
        view_text.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        booksList = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        String word = bundle.getString("Search");
        fetchBooks(word);
    }

    private void fetchBooks(String word) {

        String url = "https://www.googleapis.com/books/v1/volumes?q=" + word;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for(int i = 0; i < jsonArray.length() ; i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("volumeInfo");
                        JSONObject jsonObject3 = jsonObject.getJSONObject("accessInfo");
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("authors");
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("imageLinks");
                        String author = jsonArray1.getString(0);
                        String title = jsonObject1.getString("title");
                        String link = jsonObject2.getString("smallThumbnail");
                        String page_count = jsonObject1.getString("pageCount");
                        String more = jsonObject3.getString("webReaderLink");
                        String image_link = link.replace("http", "https");
                        Log.e("Res", image_link);
                        Books book = new Books(title, author, page_count, image_link, more);
                        booksList.add(book);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

               BookAdapter bookAdapter = new BookAdapter(SearchResult.this, booksList);
                view_text.setAdapter(bookAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}