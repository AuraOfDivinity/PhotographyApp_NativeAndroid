package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler  extends SQLiteOpenHelper {

    public DBHandler(Context context) {
        super(context, ArtistMaster.DATABASE_NAME, null, ArtistMaster.DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTIST_DETAILS_TABLE = "CREATE TABLE " + ArtistMaster.ARTIST_DATABASE_NAME + "("
                + ArtistMaster.AARTIST_ID + " INTEGER PRIMARY KEY," + ArtistMaster.ARTIST_NAME + " TEXT" + ")";


        String CREATE_PHOTOGRAPH_DETAILS_TABLE = "CREATE TABLE " + ArtistMaster.DATABASE_NAME + "("
                + ArtistMaster.ID + " INTEGER PRIMARY KEY," + ArtistMaster.PHOTOGRAPH_NAME + " TEXT,"
                + ArtistMaster.ARTIST_ID + " INTEGER, " +ArtistMaster.PHOTO_CATEGORY + " TEXT," +
                " CONSTRAINT fk_artists FOREIGN KEY ("+ ArtistMaster.ARTIST_ID+") REFERENCES " +ArtistMaster.ARTIST_DATABASE_NAME+"("+ArtistMaster.AARTIST_ID+"))";



        db.execSQL(CREATE_ARTIST_DETAILS_TABLE);
        db.execSQL(CREATE_PHOTOGRAPH_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ArtistMaster.ARTIST_DATABASE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ArtistMaster.DATABASE_NAME);
        // Create tables again
        onCreate(db);
    }

//     public void addPhotos(PhotoObject photo){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//
//        values.put(ArtistMaster.ID, photo.getId());
//        values.put(ArtistMaster.PHOTOGRAPH_NAME, photo.getPhotographName());
//        values.put(ArtistMaster.PHOTO_CATEGORY, photo.getPhotoCategory());
//        values.put(ArtistMaster.ARTIST_ID, photo.getArtistId());
//
//        db.insert(ArtistMaster.DATABASE_NAME, null, values);
//        db.close();
//    }

    public long addArtist(ArtistObject artist){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ArtistMaster.ARTIST_NAME, artist.getArtistName());

        long status = db.insert(ArtistMaster.ARTIST_DATABASE_NAME, null, values);
        db.close();
        return status;
    }

    public long addPhoto(PhotoObject photo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ArtistMaster.PHOTOGRAPH_NAME, photo.getPhotographName());
        values.put(ArtistMaster.ARTIST_ID, photo.getArtistId());
        values.put(ArtistMaster.PHOTO_CATEGORY, photo.getPhotoCategory());

        long status = db.insert(ArtistMaster.DATABASE_NAME, null, values);
        db.close();
        return status;
    }


    public List<String> loadArtists() {
        List<String> artistNAmeList = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + ArtistMaster.ARTIST_DATABASE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String currentArtistName = cursor.getString(1);
                artistNAmeList.add(currentArtistName);
            } while (cursor.moveToNext());
        }

        return artistNAmeList;
    }

    public int getArtistIdByName(String name){
        int id;

        String selectQuery = "SELECT "+ArtistMaster.AARTIST_ID+" FROM " +  ArtistMaster.ARTIST_DATABASE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        id = cursor.getInt(0);

        return id;
    }

    public List<String> loadPhotoNames() {
        List<String> photoNameList = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + ArtistMaster.DATABASE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String currentPhototName = cursor.getString(1);
                photoNameList.add(currentPhototName);
            } while (cursor.moveToNext());
        }

        return photoNameList;
    }

    public void deleteDetails(String tableName, String itemName){
        SQLiteDatabase db = this.getWritableDatabase();

        if(tableName.equals( ArtistMaster.DATABASE_NAME)){
            String sqlQuery = "DELETE FROM "+ArtistMaster.DATABASE_NAME+ " WHERE "+ArtistMaster.PHOTOGRAPH_NAME+ " = '" +itemName +"'";
            db.execSQL(sqlQuery);
        }
        else if(tableName.equals(ArtistMaster.ARTIST_DATABASE_NAME)){
            String sqlQuery = "DELETE FROM "+ArtistMaster.ARTIST_DATABASE_NAME+ " WHERE "+ArtistMaster.ARTIST_NAME+ " = '" +itemName + "' ";
            db.execSQL(sqlQuery);
        }
    }




}
