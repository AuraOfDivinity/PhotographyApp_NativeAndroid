package com.example.photographyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import Database.ArtistObject;
import Database.DBHandler;

public class AddArtist extends AppCompatActivity {
    EditText artistname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        artistname = findViewById(R.id.newArtistEditText);

    }

    public void addArtist(View view){
        DBHandler db  = new DBHandler(this);
        long status = db.addArtist(new ArtistObject(artistname.getText().toString()));
        if(status > 0){
            Toast.makeText(this, "Artist successfully added", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error adding the artist", Toast.LENGTH_SHORT).show();
        }

//        loop to debug
//        List<String> artistNames = db.loadArtists();
//
//        for(String name: artistNames){
//            Log.d("Name", name);
//        }
    }
}
