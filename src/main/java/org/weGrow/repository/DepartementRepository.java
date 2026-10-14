
package org.weGrow.repository;

import org.weGrow.model.Departement;
import java.util.List;

public interface DepartementRepository {

    void ajouter(Departement departement);

    List<Departement> trouverTous();

    Departement trouverParId(Long id);

    void supprimer(Departement departement);
}
