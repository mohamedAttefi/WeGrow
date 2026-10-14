
package org.weGrow.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.weGrow.model.Employe;
import org.weGrow.repository.EmployeRepository;

import java.util.List;

public class EmployeRepositoryImpl implements EmployeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void ajouter(Employe employe) {
        entityManager.persist(employe);
    }

    @Override
    public List<Employe> trouverParDepartement(Long departementId) {
        return entityManager.createQuery(
                        "SELECT e FROM Employe e WHERE e.departement.id = :id",
                        Employe.class)
                .setParameter("id", departementId)
                .getResultList();
    }

    @Override
    public Employe trouverParMatricule(String matricule) {
        List<Employe> resultats = entityManager.createQuery(
                        "SELECT e FROM Employe e WHERE e.matricule = :matricule",
                        Employe.class)
                .setParameter("matricule", matricule)
                .getResultList();

        return resultats.isEmpty() ? null : resultats.get(0);
    }

    @Override
    public long compterParDepartement(Long departementId) {
        return entityManager.createQuery(
                        "SELECT COUNT(e) FROM Employe e WHERE e.departement.id = :id",
                        Long.class)
                .setParameter("id", departementId)
                .getSingleResult();
    }
}
