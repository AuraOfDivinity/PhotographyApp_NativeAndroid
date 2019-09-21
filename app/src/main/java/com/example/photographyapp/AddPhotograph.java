package com.example.photographyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Database.ArtistObject;
import Database.DBHandler;
import Database.PhotoObject;

public class AddPhotograph extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String selectedArtistId, selectedCategory, selectedArtistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photograph);

        DBHandler db = new DBHandler(this);
        List<String> artistNames = db.loadArtists();
        ArrayList<String> categoryList = new ArrayList<>(Arrays.asList("Landscape", "Wildlife", "Sports", "Wedding", "Fashion", "Black and White"));

        Spinner photoCategorySpinner = findViewById(R.id.photoCategorySpinner);
        Spinner artistNameSpinner = findViewById(R.id.artistNameSpinner);

        photoCategorySpinner.setOnItemSelectedListener(this);
        artistNameSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> photoCategoryAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categoryList);
        ArrayAdapter<String> artistNameAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,artistNames);

        photoCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        photoCategorySpinner.setAdapter(photoCategoryAdapter);
        artistNameSpinner.setAdapter(artistNameAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View view, int position, long l) {
        DBHandler db = new DBHandler(this);
        List<String> artistNames = db.loadArtists();
        ArrayList<String> categoryList = new ArrayList<>(Arrays.asList("Landscape", "Wildlife", "Sports", "Wedding", "Fashion", "Black and White"));

        switch(arg0.getId()){
            case R.id.photoCategorySpinner:
                selectedCategory = categoryList.get(position);
                break;
            case R.id.artistNameSpinner:
                selectedArtistName = artistNames.get(position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addPhotograph(View view){
        EditText photoName = findViewById(R.id.photoName);

        DBHandler db  = new DBHandler(this);
        int id = db.getArtistIdByName(selectedArtistName);

        long status = db.addPhoto(new PhotoObject(photoName.getText().toString(), id, selectedCategory ));

        if(status > 0){
            Toast.makeText(this, "Photograph successfully added", Toast.LENGTH_SHORT).show();
            List<String> photoNames = db.loadPhotoNames();
            for(String name: photoNames){
                System.out.println("111111111111111111111111111111" + name);
            }
        }else{
            Toast.makeText(this, "Error adding the photograph", Toast.LENGTH_SHORT).show();
        }
    }
}
