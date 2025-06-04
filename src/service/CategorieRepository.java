package service;

import model.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieRepository {
    public void adaugaCategorie(Categorie categorie) {
        String sql = "INSERT INTO categorii (tip, pret) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categorie.getTip());
            stmt.setDouble(2, categorie.getPret());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Categorie> getCategorii() {
        List<Categorie> list = new ArrayList<>();
        String sql = "SELECT * FROM categorii";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categorie c = new Categorie(
                        rs.getInt("id"),
                        rs.getString("tip"),
                        rs.getDouble("pret")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
