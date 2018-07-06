package com.example.android.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AndroidLibraryJokesMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();
        String joke = intent.getStringExtra( "Joke");

        setContentView( R.layout.android_joke_library_main );
        TextView textView = (TextView) findViewById( R.id.android_library_tv );
        textView.setText( joke );
        super.onCreate( savedInstanceState );
    }
}
