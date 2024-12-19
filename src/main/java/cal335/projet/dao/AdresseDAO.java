package cal335.projet.dao;
import cal335.projet.modele.Adresse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresseDAO {
    private final String url = "jdbc:sqlite:src/main/resources/taches.db";

    public List<Adresse> recupererAdressesParContact(int contactId) {
        List<Adresse> adresses = new ArrayList<>();
        String sql = "SELECT id, rue, ville, code_postal, pays, latitude, longitude, contact_id FROM adresse WHERE contact_id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Adresse adresse = new Adresse(
                        rs.getInt("id"),
                        rs.getString("rue"),
                        rs.getString("ville"),
                        rs.getString("code_postal"),
                        rs.getString("pays"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getInt("contact_id")
                );
                adresses.add(adresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adresses;
    }

}
