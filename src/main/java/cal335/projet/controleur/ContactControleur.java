package cal335.projet.controleur;

import cal335.projet.modele.Contact;
import cal335.projet.dto.ContactRequete;

import cal335.projet.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ContactControleur implements HttpHandler {
    private final ContactService serviceContact = new ContactService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        switch (method) {
            case "GET":
                gererGet(exchange);
                break;
            case "POST":
                gererPost(exchange);
                break;
            case "PUT":
                gererPut(exchange);
                break;
            case "DELETE":
                gererDelete(exchange);
                break;
            default:
                envoyerReponse(exchange, 405, "Méthode non autorisée.");
                break;
        }
    }

    private void gererGet(HttpExchange exchange) throws IOException {
        List<Contact> contacts = serviceContact.recupererTousLesContacts();
        StringBuilder responseBuilder = new StringBuilder();
        for (Contact contact : contacts) {
            responseBuilder.append("ID: ").append(contact.getId())
                    .append(", Nom: ").append(contact.getNom())
                    .append(", Prénom: ").append(contact.getPrenom())
                    .append(", Téléphone: ").append(contact.getNumeroTelephone())
                    .append(", Favoris: ").append(contact.isFavoris())
                    .append("\n");
        }
        envoyerReponse(exchange, 200, responseBuilder.toString());
    }

    private void gererPost(HttpExchange exchange) throws IOException {
        ContactRequete dataPost = mapper.readValue(exchange.getRequestBody(), ContactRequete.class);
        serviceContact.ajouterContact(
                dataPost.nom,
                dataPost.prenom,
                dataPost.numeroTelephone,
                dataPost.favoris
        );
        envoyerReponse(exchange, 201, "Contact ajouté.");
    }

    private void gererPut(HttpExchange exchange) throws IOException {
        ContactRequete dataPut = mapper.readValue(exchange.getRequestBody(), ContactRequete.class);
        serviceContact.mettreAJourContact(
                dataPut.id,
                dataPut.nom,
                dataPut.prenom,
                dataPut.numeroTelephone,
                dataPut.favoris
        );
        envoyerReponse(exchange, 200, "Contact mis à jour.");
    }

    private void gererDelete(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        int id = Integer.parseInt(query.split("=")[1]);
        serviceContact.supprimerContact(id);
        envoyerReponse(exchange, 200, "Contact supprimé.");
    }

    private void envoyerReponse(HttpExchange exchange, int statusCode, String reponse) throws IOException {
        exchange.sendResponseHeaders(statusCode, reponse.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(reponse.getBytes());
        }
    }
}
