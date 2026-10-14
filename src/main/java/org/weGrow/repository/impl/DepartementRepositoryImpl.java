
package org.weGrow.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.weGrow.model.Departement;
import org.weGrow.repository.DepartementRepository;

import java.util.List;

public class DepartementRepositoryImpl implements DepartementRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void ajouter(Departement departement) {
        entityManager.persist(departement);
    }

    @Override
    public List<Departement> trouverTous() {
        return entityManager
                .createQuery("SELECT d FROM Departement d", Departement.class)
                .getResultList();
    }

    @Override
    public Departement trouverParId(Long id) {
        return entityManager.find(Departement.class, id);
    }

    @Override
    public void supprimer(Departement departement) {
        entityManager.remove(departement);
    }
}
