package revplay;

public class Favorite {

    private int id;
    private int userId;
    private int songId;

    public Favorite(int id, int userId, int songId) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getSongId() {
        return songId;
    }
}
