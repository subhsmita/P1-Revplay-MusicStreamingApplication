package revplay;

public class Artist {

    private int id;
    private String name;
    private String email;
    private String password;
    private String bio;
    private String genre;

    public Artist() {}

    public Artist(int id, String name, String email, String password, String bio, String genre) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.genre = genre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
