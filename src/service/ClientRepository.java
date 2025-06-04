package service;

import model.Client;
import model.Utilizator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    public void adaugaClient(Client c) {
        String sql = "INSERT INTO clienti (cod_utilizator, nume, prenume) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, c.getUtilizator().getId()); // utilizator compus
            stmt.setString(2, c.getNume());
            stmt.setString(3, c.getPrenume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getClienti() {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM clienti";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int codUtilizator = rs.getInt("cod_utilizator");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");

                // interogăm tabelul utilizatori pentru a obține obiectul
                Utilizator utilizator = getUtilizatorById(codUtilizator, conn);
                if (utilizator != null) {
                    Client c = new Client(id, utilizator, nume, prenume);
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Utilizator getUtilizatorById(int id, Connection conn) {
        String sql = "SELECT * FROM utilizatori WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Utilizator(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("parola")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
