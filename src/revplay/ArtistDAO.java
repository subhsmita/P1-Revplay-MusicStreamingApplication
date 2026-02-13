package revplay;

import java.sql.*;
import java.util.*;

public class ArtistDAO {

    // REGISTER
    public boolean register(String name, String email, String password) {
        String sql = "INSERT INTO artist(name, email, password) VALUES(?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // LOGIN
    public Artist login(String email, String password) {
        String sql = "SELECT * FROM artist WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Artist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("bio"),
                        rs.getString("genre")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE PROFILE
    public boolean updateProfile(int id, String bio, String genre) {
        String sql = "UPDATE artist SET bio=?, genre=? WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bio);
            ps.setString(2, genre);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // UPLOAD SONG
    public boolean uploadSong(int artistId, String title, String album, String genre, int duration) {
        String sql = "INSERT INTO artist_song(artist_id, title, album, genre, duration) VALUES(?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ps.setString(2, title);
            ps.setString(3, album);
            ps.setString(4, genre);
            ps.setInt(5, duration);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // VIEW MY SONGS
    public List<String> getMySongs(int artistId) {
        List<String> list = new ArrayList<>();

        String sql = "SELECT title, album FROM artist_song WHERE artist_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("title") + " (" + rs.getString("album") + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
