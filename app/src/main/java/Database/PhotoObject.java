package Database;

public class PhotoObject {
    private int id;
    private String photographName;
    private int artistId;
    private String photoCategory;

    public PhotoObject(){
    }

    public PhotoObject( String photographName, int artistId, String photoCategory) {
        this.photographName = photographName;
        this.artistId = artistId;
        this.photoCategory = photoCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotographName() {
        return photographName;
    }

    public void setPhotographName(String photographName) {
        this.photographName = photographName;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getPhotoCategory() {
        return photoCategory;
    }

    public void setPhotoCategory(String photoCategory) {
        this.photoCategory = photoCategory;
    }

}
