package com.example.photographyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import Database.ArtistMaster;
import Database.DBHandler;

public class RemoveArtistOrPhoto extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String selectedArtist, selectedPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_artist_or_photo);

        DBHandler db = new DBHandler(this);
        List<String> artistNames = db.loadArtists();
        List<String> photoNames = db.loadPhotoNames();

        Spinner artistNameSpinner = findViewById(R.id.artistNameSpinnerRemove);
        Spinner photoNameSpinner = findViewById(R.id.photoNameSpinnerRemove);

        artistNameSpinner.setOnItemSelectedListener(this);
        photoNameSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> artistNameAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,artistNames);
        ArrayAdapter<String> photoNameAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,photoNames);

        artistNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        photoNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        artistNameSpinner.setAdapter(artistNameAdapter);
        photoNameSpinner.setAdapter(photoNameAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int position, long l) {
        DBHandler db = new DBHandler(this);
        List<String> artistNames = db.loadArtists();
        List<String> photoNames = db.loadPhotoNames();

        switch(arg0.getId()){
            case R.id.photoNameSpinnerRemove:
                selectedPhoto = photoNames.get(position);
                break;
            case R.id.artistNameSpinnerRemove:
                selectedArtist = artistNames.get(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void deletePhoto(View view){
        DBHandler db  = new DBHandler(this);
        db.deleteDetails("PhotographDetails", selectedPhoto);
    }

    public void deleteArtist(View view){
        DBHandler db  = new DBHandler(this);
        db.deleteDetails("ArtistDetails", selectedArtist);
    }
}
