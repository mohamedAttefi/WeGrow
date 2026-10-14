
package org.weGrow.service;

import org.weGrow.exeption.DepartementInexistantExeption;
import org.weGrow.model.Departement;
import org.weGrow.repository.DepartementRepository;

import java.util.List;

public class DepartementService {

    private DepartementRepository departementRepository;

    public void setDepartementRepository(
            DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public void creerDepartement(Departement departement) {
    }

    public List<Departement> listerDepartements() {
        return departementRepository.trouverTous();
    }

    public void supprimerDepartement(Long id) {

        Departement departement = departementRepository.trouverParId(id);

        if(departement == null){
            throw new DepartementInexistantExeption("departement inexistant");
        }

        departementRepository.supprimer(departement);

    }
}
