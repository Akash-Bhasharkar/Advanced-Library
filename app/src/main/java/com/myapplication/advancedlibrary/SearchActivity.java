package com.myapplication.advancedlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    Button search;
    EditText word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search = findViewById(R.id.search);
        word = findViewById(R.id.search_src_text);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = word.getText().toString().trim();
                Intent intent = new Intent(SearchActivity.this, SearchResult.class);

                Bundle bundle = new Bundle();
                bundle.putString("Search", text);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}