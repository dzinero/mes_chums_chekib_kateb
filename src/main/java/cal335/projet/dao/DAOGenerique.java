package cal335.projet.dao;

import java.util.List;

public interface DAOGenerique<T> {
    void ajouter(T objet);
    void supprimer(Integer id);
    void mettreAJour(T objet);
    T trouverParId(Integer id);
    List<T> trouverTous();
}
