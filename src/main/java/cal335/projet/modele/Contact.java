package cal335.projet.modele;

import java.util.List;

public class Contact {
    private int id;
    private String nom;
    private String prenom;
    private String numeroTelephone;
    private boolean favoris;
    private List<Adresse> adresses;

    // Constructeur
    public Contact(int id, String nom, String prenom, String numeroTelephone, boolean favoris, List<Adresse> adresses) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroTelephone = numeroTelephone;
        this.favoris = favoris;
        this.adresses = adresses;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public boolean isFavoris() {
        return favoris;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }
}
