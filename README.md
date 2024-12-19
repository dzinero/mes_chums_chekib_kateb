# Documentation du projet "mes_chums"

## Présentation de l'Application

### Objectif de l'application
L'application "mes_chums" est une application qui permet de gérer les contacts personnels. Elle stocke les informations comme le nom, le prénom, le numéro de téléphone et les adresses. Elle inclut également des fonctionnalités pour marquer des contacts comme favoris et géolocaliser leurs adresses.

### Fonctionnalités principales
- Ajouter, modifier, supprimer des contacts.
- Marquer un contact comme favori.
- Associer une ou plusieurs adresses à un contact.
- Obtenir les coordonnées GPS des adresses via l'API OpenWeatherMap.
- Consulter tous les contacts via des endpoints REST.

### Technologies utilisées
- **Langage de programmation** : Java 21
- **Gestionnaire de projet et des dépendances** : Maven
- **Serveur** : HTTP Server intégré à Java
- **Base de données** : SQLite
- **API de géocodage** : OpenWeatherMap API

---

## Endpoints REST

Voici les endpoints REST implémentés pour gérer les contacts :

### GET /contacts
Récupère tous les contacts enregistrés dans la base de données.

**Exemple de réponse JSON :**
```json
[
    {
        "id": 1,
        "nom": "Kateb",
        "prenom": "Chekib",
        "numeroTelephone": "111 111 1111",
        "favoris": true
    },
    {
        "id": 2,
        "nom": "Ritter",
        "prenom": "Yohan",
        "numeroTelephone": "222 222 2222",
        "favoris": false
    }
]
```

### POST /contacts
Ajoute un nouveau contact.

**Exemple de requête JSON :**
```json
{
    "nom": "Bob",
    "prenom": "Marley",
    "numeroTelephone": "12093643948",
    "favoris": false
}
```

### PUT /contacts
Met à jour un contact existant.

**Exemple de requête JSON :**
```json
{
    "id": 1,
    "nom": "Kateb",
    "prenom": "Chekib",
    "numeroTelephone": "111 111 1111",
    "favoris": true
}
```

### DELETE /contacts?id={id}
Supprime un contact via son ID.

**Exemple :**
- URL : `http://localhost:8080/contacts?id=1`
- Méthode : DELETE

---

## Architecture Logicielle

L'application est structurée selon une architecture en couches.

### Couche Contrôleur
Cette couche gère les requêtes HTTP. Voici un exemple de méthode :
```java
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
```

### Couche Service
Cette couche contient la logique métier. Exemple :
```java
public void ajouterContact(String nom, String prenom, String numeroTelephone, boolean favoris) {
    Contact contact = new Contact(0, nom, prenom, numeroTelephone, favoris, new ArrayList<>());
    daoContact.ajouter(contact);
}
```

### Couche DAO (Data Access Object)
Elle gère les interactions avec la base de données SQLite. Exemple :
```java
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
```

---

## Utilisation de Géolocalisation

L'application utilise l'API OpenWeatherMap pour récupérer les coordonnées GPS des adresses.

**Exemple de code :**
```java
String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + adresse + "&appid=" + API_KEY;
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();
HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Réponse API : " + response.body());
```

---

## Fonctionnement des Classes et Méthodes

### Classe `ContactControleur`
Cette classe gère les requêtes HTTP. Voici un résumé des méthodes principales :
- `gererGet` : Récupère tous les contacts.
- `gererPost` : Ajoute un nouveau contact.
- `gererPut` : Met à jour un contact existant.
- `gererDelete` : Supprime un contact via son ID.

### Classe `ContactService`
Cette classe applique la logique métier :
- `ajouterContact` : Crée un nouvel objet `Contact` et le passe au DAO.
- `mettreAJourContact` : Met à jour les données d'un contact existant.
- `recupererTousLesContacts` : Récupère la liste de tous les contacts depuis le DAO.
- `supprimerContact` : Supprime un contact via son ID.

### Classe `ContactDAO`
Cette classe interagit directement avec la base de données SQLite :
- `ajouter` : Ajoute un contact dans la base de données.
- `mettreAJour` : Met à jour les informations d'un contact existant.
- `trouverTous` : Récupère tous les contacts.
- `supprimer` : Supprime un contact.

### Classe `CalculateurDistance`
Calcule la distance entre deux coordonnées GPS :
```java
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
```

---

## Conclusion

L'application "mes_chums" est simple et efficace pour gérer des contacts avec des fonctionnalités utiles comme la géolocalisation. Son architecture en couches la rend facile à maintenir et à faire évoluer.

-Chekib Kateb