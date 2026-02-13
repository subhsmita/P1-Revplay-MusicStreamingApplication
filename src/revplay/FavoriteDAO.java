package revplay;

import java.sql.*;
import java.util.*;

public class FavoriteDAO {

    // ⭐ ADD TO FAVORITES
    public boolean addFavorite(int userId, int songId) {

        String sql = "INSERT INTO favorites (user_id, song_id) VALUES (?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // ⭐ VIEW FAVORITES
    public List<Song> getFavorites(int userId) {

        List<Song> list = new ArrayList<>();

        String sql = """
            SELECT s.id, s.title, s.artist, s.genre, s.duration
            FROM favorites f
            JOIN songs s ON f.song_id = s.id
            WHERE f.user_id = ?
        """;

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

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
}
