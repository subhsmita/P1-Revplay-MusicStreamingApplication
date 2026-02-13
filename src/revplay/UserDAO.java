package revplay;

import java.sql.*;

public class UserDAO {

    public boolean register(String name, String email, String pass) {
        String sql = "INSERT INTO users(name,email,password,role) VALUES(?,?,?,'USER')";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public User login(String email, String pass) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) { }

        return null;
    }
}
