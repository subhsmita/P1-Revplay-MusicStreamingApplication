
package revplay;

import java.sql.*;
import java.util.*;

public class SongDAO {

    // GET ALL SONGS
    public List<Song> getAllSongs() {
        List<Song> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM songs";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getString("duration")
                );
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // GET SONG BY ID  ⭐ IMPORTANT METHOD
    public Song getSongById(int id) {

        try (Connection con = DBUtil.getConnection()) {

            String sql = "SELECT * FROM songs WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("genre"),
                        rs.getString("duration")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
