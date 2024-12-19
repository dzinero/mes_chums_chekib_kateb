package cal335.projet;

import cal335.projet.controleur.ContactControleur;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class  Main {
    public static void main(String[] args) {
        try {
            HttpServer serveur = HttpServer.create(new InetSocketAddress(8080), 0);
            serveur.createContext("/contacts", new ContactControleur());
            serveur.setExecutor(null);
            System.out.println("Serveur démarré sur le port 8080");
            serveur.start();
        } catch (IOException e) {
            System.out.println("Erreur lors du démarrage du serveur : " + e.getMessage());
        }
    }
}
