
package org.weGrow.service;

import org.weGrow.exeption.DepartementInexistantExeption;
import org.weGrow.exeption.MatriculeExistantExeption;
import org.weGrow.model.Departement;
import org.weGrow.model.Employe;
import org.weGrow.repository.DepartementRepository;
import org.weGrow.repository.EmployeRepository;

import java.util.List;

public class EmployeService {

    private EmployeRepository employeRepository;
    private DepartementRepository departementRepository;

    public void setEmployeRepository(
            EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public void setDepartementRepository(
            DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public void ajouterEmploye(Employe employe) throws MatriculeExistantExeption, DepartementInexistantExeption {
        Employe existant = employeRepository.trouverParMatricule(employe.getMatricule());

        if (existant != null) {
            throw new MatriculeExistantExeption("matricule deja existe");
        }

        Long departemantId = employe.getDepartement().getId();
        Departement departement = departementRepository.trouverParId(departemantId);

        if (departement == null){
            throw new DepartementInexistantExeption("departement inexistant");
        }

        employeRepository.ajouter(employe);

    }

    public List<Employe> listerEmployesParDepartement(
            Long departementId) {

        Departement departement = departementRepository.trouverParId(departementId);

        if (departement == null) {
            throw new DepartementInexistantExeption("departement inexistant");
        }

        return employeRepository.trouverParDepartement(departementId);

    }
}
