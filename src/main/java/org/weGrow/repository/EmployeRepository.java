
package org.weGrow.repository;

import org.weGrow.model.Employe;
import java.util.List;

public interface EmployeRepository {

    void ajouter(Employe employe);

    List<Employe> trouverParDepartement(Long departementId);

    Employe trouverParMatricule(String matricule);

    long compterParDepartement(Long departementId);
}
