package revplay;

import java.sql.*;
import java.util.*;

public class PlaylistSongDAO {

    public List<Song> getSongs(int playlistId) {

        List<Song> list = new ArrayList<>();

        try {
            Connection con = DBUtil.getConnection();

            String sql =
                    "SELECT s.id, s.title, s.artist, s.genre, s.duration " +
                            "FROM songs s " +
                            "JOIN playlist_songs ps ON s.id = ps.song_id " +
                            "WHERE ps.playlist_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);

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
