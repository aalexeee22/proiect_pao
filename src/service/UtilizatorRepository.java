package service;

import model.Utilizator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorRepository {
    public void adaugaUtilizator(Utilizator u) {
        String sql = "INSERT INTO utilizatori (username, email, parola) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getParola());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilizator> getUtilizatori() {
        List<Utilizator> list = new ArrayList<>();
        String sql = "SELECT * FROM utilizatori";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Utilizator u = new Utilizator(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("parola")
                );
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
