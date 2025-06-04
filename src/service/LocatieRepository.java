package service;

import model.Locatie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatieRepository {
    public void adaugaLocatie(Locatie locatie) {
        String sql = "INSERT INTO locatii (nume, adresa) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, locatie.getNume());
            stmt.setString(2, locatie.getAdresa());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locatie> getLocatii() {
        List<Locatie> list = new ArrayList<>();
        String sql = "SELECT * FROM locatii";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Locatie l = new Locatie(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("adresa")
                );
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
