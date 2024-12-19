package cal335.projet.dao;

import cal335.projet.modele.Coordonnees;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoordonneesDAO {
    private final String url = "jdbc:sqlite:src/main/resources/taches.db";

    public List<Coordonnees> recupererToutesCoordonnees() {
        List<Coordonnees> coordonneesList = new ArrayList<>();
        String sql = "SELECT * FROM Coordonnees";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                coordonneesList.add(new Coordonnees(
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordonneesList;
    }
}
