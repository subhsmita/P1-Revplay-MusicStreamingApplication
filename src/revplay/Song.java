package revplay;

public class Song {

    private int id;
    private String title;
    private String artist;
    private String genre;
    private String duration;   // ⭐ NEW FIELD

    // Empty constructor
    public Song() {}

    // ⭐ FINAL constructor (5 parameters)
    public Song(int id, String title, String artist, String genre, String duration) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getGenre() { return genre; }
    public String getDuration() { return duration; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setDuration(String duration) { this.duration = duration; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + artist + " | " + genre + " | " + duration;
    }
}
