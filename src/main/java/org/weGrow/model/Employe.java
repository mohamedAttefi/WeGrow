
package org.weGrow.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "employes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "matricule")
        }
)
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String matricule;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    private String poste;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Contrat contrat;

    @ManyToOne(optional = false)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    public Employe() {
    }

    public Employe(
            String matricule,
            String nom,
            String prenom,
            String poste,
            Contrat contrat,
            Departement departement) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.contrat = contrat;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPoste() {
        return poste;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Departement getDepartement() {
        return departement;
    }
}
