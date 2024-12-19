package cal335.projet.calculateur;

import cal335.projet.modele.Coordonnees;

public class CalculateurDistance {

    public static double calculerDistance(Coordonnees coord1, Coordonnees coord2) {
        final int rayonTerre = 6371;

        double latDistance = Math.toRadians(coord2.getLatitude() - coord1.getLatitude());
        double lonDistance = Math.toRadians(coord2.getLongitude() - coord1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(coord1.getLatitude())) *
                        Math.cos(Math.toRadians(coord2.getLatitude())) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return rayonTerre * c;
    }
}
