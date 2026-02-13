package revplay;

import java.sql.*;
import java.util.*;

public class HistoryDAO {

    public void addHistory(int userId, int songId) {
        try (Connection con = DBUtil.getConnection()) {

            String sql = "INSERT INTO HISTORY (USER_ID, SONG_ID) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, songId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Song> getUserHistory(int userId) {

        List<Song> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection()) {

            String sql =
                    "SELECT s.ID, s.TITLE, s.ARTIST, s.GENRE, s.DURATION " +
                            "FROM HISTORY h JOIN SONGS s ON h.SONG_ID = s.ID " +
                            "WHERE h.USER_ID = ? ORDER BY h.PLAYED_AT DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Song(
                        rs.getInt("ID"),
                        rs.getString("TITLE"),
                        rs.getString("ARTIST"),
                        rs.getString("GENRE"),
                        rs.getString("DURATION")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
