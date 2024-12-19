package cal335.projet.dto;

public class ContactDTO {
    private int idContact;
    private String nom;
    private String prenom;
    private boolean estFavoris;

    public ContactDTO() {}

    public ContactDTO(int idContact, String nom, String prenom, boolean estFavoris) {
        this.idContact = idContact;
        this.nom = nom;
        this.prenom = prenom;
        this.estFavoris = estFavoris;
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isEstFavoris() {
        return estFavoris;
    }

    public void setEstFavoris(boolean estFavoris) {
        this.estFavoris = estFavoris;
    }
}
