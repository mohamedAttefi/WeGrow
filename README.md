# WeGrow — API de gestion des départements et des employés

## Présentation

**WeGrow** est une API REST développée avec Spring MVC permettant à l'équipe des ressources humaines de gérer les départements de l'entreprise et leurs employés.

Le projet vise à centraliser les informations des employés, à faciliter leur affectation aux départements et à garantir le respect des règles de gestion.

## Objectifs du projet

- Créer et supprimer des départements.
- Consulter la liste des départements avec le nombre d'employés associés.
- Ajouter des employés dans un département.
- Consulter les employés d'un département.
- Garantir l'unicité des noms de départements et des matricules des employés.
- Assurer la cohérence des données lors de requêtes simultanées.

## Technologies utilisées

- **Java 17+**
- **Spring Core** — inversion de contrôle (IoC) et injection de dépendances.
- **Spring MVC** — gestion des requêtes HTTP et création de l'API REST.
- **Spring Data** — accès et persistance des données.
- **Base de données relationnelle** — stockage des départements et des employés.
- **Maven** — gestion des dépendances et construction du projet.
- **Apache Tomcat 10.1+** — serveur d'application.
- **XML** — configuration de Spring et démarrage de Spring MVC.
- **WAR** — format de déploiement de l'application.

## Architecture du projet

Le projet suit une architecture en couches afin de séparer les responsabilités.

| Couche | Responsabilité |
|---|---|
| `controller` | Recevoir les requêtes HTTP et retourner les réponses. |
| `service` | Appliquer les règles métier et gérer les opérations. |
| `repository` | Accéder aux données avec Spring Data. |
| `model` | Représenter les entités `Departement` et `Employe`. |
| `config` | Regrouper les fichiers de configuration XML de Spring. |

L'injection des dépendances est réalisée par constructeur ou par setter, en privilégiant les interfaces pour limiter le couplage entre les couches.

**Configuration Spring :** toute la configuration de Spring, y compris la déclaration des beans et le démarrage de Spring MVC, est réalisée en XML, conformément au cahier des charges.

## Modèle de données

### Departement

| Attribut | Description |
|---|---|
| `id` | Identifiant du département. |
| `nom` | Nom unique du département. |
| `description` | Description du département. |

### Employe

| Attribut | Description |
|---|---|
| `id` | Identifiant de l'employé. |
| `matricule` | Matricule unique de l'employé. |
| `nom` | Nom de l'employé. |
| `prenom` | Prénom de l'employé. |
| `poste` | Poste occupé par l'employé. |
| `contrat` | Type de contrat : `CDI`, `CDD` ou `STAGE`. |
| `departementId` | Identifiant du département auquel appartient l'employé. |

Chaque employé appartient à un seul département existant.

## Endpoints de l'API

La base des routes est `/api`.

| Méthode | Endpoint | Description | Réponses attendues |
|---|---|---|---|
| `POST` | `/api/departements` | Créer un département. | `201 Created`, `409 Conflict` |
| `GET` | `/api/departements` | Lister les départements avec leur nombre d'employés. | `200 OK` |
| `DELETE` | `/api/departements/{id}` | Supprimer un département. | `204 No Content`, `404 Not Found`, `409 Conflict` |
| `POST` | `/api/departements/{id}/employes` | Ajouter un employé à un département. | `201 Created`, `404 Not Found`, `409 Conflict` |
| `GET` | `/api/departements/{id}/employes` | Lister les employés d'un département. | `200 OK`, `404 Not Found` |

## Exemples de requêtes

### 1. Créer un département

**Requête**

```http
POST /api/departements
Content-Type: application/json
```

```json
{
  "nom": "Ressources humaines",
  "description": "Gestion du personnel"
}
```

**Réponse attendue :** `201 Created`

### 2. Lister les départements

**Requête**

```http
GET /api/departements
```

**Réponse attendue :** `200 OK`

```json
[
  {
    "id": 1,
    "nom": "Ressources humaines",
    "description": "Gestion du personnel",
    "nombreEmployes": 2
  },
  {
    "id": 2,
    "nom": "Informatique",
    "description": "Développement",
    "nombreEmployes": 1
  }
]
```

### 3. Ajouter un employé à un département

**Requête**

```http
POST /api/departements/1/employes
Content-Type: application/json
```

```json
{
  "matricule": "EMP001",
  "nom": "Alami",
  "prenom": "Sara",
  "poste": "Responsable RH",
  "contrat": "CDI"
}
```

**Réponse attendue :** `201 Created`

### 4. Lister les employés d'un département

**Requête**

```http
GET /api/departements/1/employes
```

**Réponse attendue :** `200 OK`

```json
[
  {
    "id": 1,
    "matricule": "EMP001",
    "nom": "Alami",
    "prenom": "Sara",
    "poste": "Responsable RH",
    "contrat": "CDI"
  },
  {
    "id": 2,
    "matricule": "EMP002",
    "nom": "Bennani",
    "prenom": "Youssef",
    "poste": "Assistant RH",
    "contrat": "CDD"
  }
]
```

### 5. Supprimer un département

**Requête**

```http
DELETE /api/departements/1
```

- `204 No Content` : département supprimé.
- `404 Not Found` : département inexistant.
- `409 Conflict` : département contenant encore des employés.

## Règles de gestion

- Le nom d'un département doit être unique.
- Le matricule d'un employé doit être unique.
- Un employé doit appartenir à un département existant.
- Un employé ne peut appartenir qu'à un seul département.
- Un département contenant des employés ne peut pas être supprimé.
- Les doublons doivent entraîner une réponse `409 Conflict`.
- Les ressources inexistantes doivent entraîner une réponse `404 Not Found`, lorsque cela est prévu par l'endpoint.
- Les opérations doivent préserver la cohérence des données lorsque plusieurs requêtes arrivent simultanément.

La gestion de la concurrence repose notamment sur les contraintes d'unicité en base de données et sur une stratégie fiable de génération des identifiants.

## Configuration et déploiement

Le projet est une application Spring MVC classique, sans Spring Boot.

- Les beans Spring sont déclarés dans des fichiers XML.
- Le démarrage de Spring MVC est configuré en XML.
- Les dépendances sont gérées avec Maven.
- L'application est empaquetée au format WAR.
- Le déploiement est effectué sur Apache Tomcat 10.1 ou une version compatible.

Les versions de Spring, Spring Data et des autres dépendances doivent être compatibles avec Java 17+ et Jakarta Servlet utilisé par Tomcat 10.1+.

## Bonus : initialisation des données

Un bean `DataInitializer` peut être ajouté pour créer automatiquement des départements et des employés de test au démarrage de l'application.

L'initialisation doit être exécutée pendant la phase `init` du cycle de vie du bean, conformément au cahier des charges.

## Installation et exécution

### Prérequis

- JDK 17 ou supérieur.
- Maven.
- Apache Tomcat 10.1 ou supérieur.
- Une base de données relationnelle configurée pour le projet.

### Étapes

1. Cloner le dépôt Git :

   ```bash
   git clone <URL_DU_DEPOT>
   ```

2. Accéder au répertoire du projet :

   ```bash
   cd wegrow
   ```

3. Compiler et empaqueter l'application :

   ```bash
   mvn clean package
   ```

4. Déployer le fichier WAR généré dans le répertoire `webapps` de Tomcat.

5. Démarrer Tomcat et vérifier que l'application est accessible.

La configuration de la base de données et les paramètres de connexion doivent être adaptés à l'environnement local.

## Tests de l'API

Les endpoints peuvent être testés avec Postman, Insomnia ou `curl`.

Les tests doivent notamment vérifier :

- La création d'un département avec un nom unique.
- Le rejet d'un département dont le nom existe déjà.
- L'ajout d'un employé avec un matricule unique.
- Le rejet d'un matricule déjà utilisé.
- L'ajout d'un employé à un département inexistant.
- Le refus de suppression d'un département contenant des employés.
- Le retour des départements avec le nombre correct d'employés.
- La cohérence des données lors de requêtes simultanées.

## Informations sur le projet

- **Projet :** WeGrow
- **Sprint :** Sprint 3 — Brief 1
- **Durée :** 1 semaine
- **Mode de travail :** individuel
- **Livrable :** projet disponible sur Git

---

*Projet réalisé dans le cadre d'un exercice de développement d'une API REST avec Spring MVC, Spring Core et Spring Data.*
