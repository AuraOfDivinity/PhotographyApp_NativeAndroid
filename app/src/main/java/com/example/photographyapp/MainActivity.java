package com.example.photographyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import Database.ArtistObject;
import Database.DBHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db  = new DBHandler(this);
    }

    public void addArtist(View view){
        Intent intent = new Intent(this, AddArtist.class);
        startActivity(intent);
    }

    public void addPhotograph(View view){
        Intent intent = new Intent(this, AddPhotograph.class);
        startActivity(intent);
    }

    public void deleteArtistOrPhotograph(View view){
        Intent intent = new Intent(this, RemoveArtistOrPhoto.class);
        startActivity(intent);
    }
}
