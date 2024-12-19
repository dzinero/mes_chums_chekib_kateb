package cal335.projet.modele;

public class Adresse {
    private int id;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
    private double latitude;
    private double longitude;
    private int contactId;

    // Constructeur
    public Adresse(int id, String rue, String ville, String codePostal, String pays, double latitude, double longitude, int contactId) {
        this.id = id;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactId = contactId;
    }


    public int getId() {
        return id;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getPays() {
        return pays;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
