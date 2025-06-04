package service;

import model.Eveniment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvenimentRepository {
    public void adaugaEveniment(Eveniment e) {
        String sql = "INSERT INTO evenimente (nume, cod_categorie, cod_locatie, data_evenimentului) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNume());
            stmt.setInt(2, e.getCodCategorie());
            stmt.setInt(3, e.getCodLocatie());
            stmt.setDate(4, Date.valueOf(e.getData()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void stergeEveniment(int id) {
        String sql = "DELETE FROM evenimente WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Evenimentul a fost șters cu succes.");
            } else {
                System.out.println("Nu s-a găsit niciun eveniment cu ID-ul dat.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editeazaEveniment(Eveniment e) {
        String sql = "UPDATE evenimente SET nume = ?, cod_categorie = ?, cod_locatie = ?, data_evenimentului = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNume());
            stmt.setInt(2, e.getCodCategorie());
            stmt.setInt(3, e.getCodLocatie());
            stmt.setDate(4, Date.valueOf(e.getData()));
            stmt.setInt(5, e.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Evenimentul a fost actualizat cu succes.");
            } else {
                System.out.println("Nu s-a găsit niciun eveniment cu ID-ul dat.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public List<Eveniment> getEvenimente() {
        List<Eveniment> list = new ArrayList<>();
        String sql = "SELECT * FROM evenimente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Eveniment e = new Eveniment(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getInt("cod_categorie"),
                        rs.getInt("cod_locatie"),
                        rs.getDate("data_evenimentului").toLocalDate()
                );
                list.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
