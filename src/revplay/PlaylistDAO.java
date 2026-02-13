package revplay;

import java.sql.*;
import java.util.*;

public class PlaylistDAO {

    // 🔹 CREATE PLAYLIST
    public boolean createPlaylist(int userId, String name) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "INSERT INTO playlists(name, user_id) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 🔹 GET USER PLAYLISTS
    public List<Playlist> getUserPlaylists(int userId) {

        List<Playlist> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();

            String sql = "SELECT * FROM playlists WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Playlist p = new Playlist(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("user_id")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
