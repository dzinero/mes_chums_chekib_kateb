package cal335.projet.dao;

import cal335.projet.modele.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private final String url = "jdbc:sqlite:src/main/resources/taches.db";

    public void ajouter(Contact contact) {
        String sql = "INSERT INTO Contact (nom, prenom, numeroTelephone, favoris) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getNom());
            stmt.setString(2, contact.getPrenom());
            stmt.setString(3, contact.getNumeroTelephone());
            stmt.setBoolean(4, contact.isFavoris());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mettreAJour(Contact contact) {
        String sql = "UPDATE Contact SET nom = ?, prenom = ?, numeroTelephone = ?, favoris = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contact.getNom());
            stmt.setString(2, contact.getPrenom());
            stmt.setString(3, contact.getNumeroTelephone());
            stmt.setBoolean(4, contact.isFavoris());
            stmt.setInt(5, contact.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contact> trouverTous() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT id, nom, prenom, numeroTelephone, favoris FROM Contact";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                contacts.add(new Contact(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("numeroTelephone"),
                        rs.getBoolean("favoris"),
                        new ArrayList<>()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM Contact WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
