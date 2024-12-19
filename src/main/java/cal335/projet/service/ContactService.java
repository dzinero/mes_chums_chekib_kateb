package cal335.projet.service;

import cal335.projet.dao.ContactDAO;
import cal335.projet.modele.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactService {
    private final ContactDAO daoContact = new ContactDAO();

    public void ajouterContact(String nom, String prenom, String numeroTelephone, boolean favoris) {
        Contact contact = new Contact(0, nom, prenom, numeroTelephone, favoris, new ArrayList<>());
        daoContact.ajouter(contact);
    }

    public void mettreAJourContact(int id, String nom, String prenom, String numeroTelephone, boolean favoris) {
        Contact contact = new Contact(id, nom, prenom, numeroTelephone, favoris, new ArrayList<>());
        daoContact.mettreAJour(contact);
    }
    public List<Contact> recupererTousLesContacts() {
        return daoContact.trouverTous();
    }

    public void supprimerContact(int id) {
        daoContact.supprimer(id);
    }
}
