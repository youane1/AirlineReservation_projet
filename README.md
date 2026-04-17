# Airline Reservation System

Projet Java - II.1102 JAVA Programming and Algorithms (ISEP)

## Description

Application de gestion de reservation pour une compagnie aerienne developpee en Java avec Maven.
Le systeme permet de gerer les passagers, le personnel (pilotes, personnel de cabine), les vols, les avions, les aeroports et les reservations.

## Structure du projet

```
AirlineReservation/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/isep/airline/
                ├── model/          # Les classes metier
                │   ├── Person.java          (classe abstraite)
                │   ├── Employee.java        (classe abstraite, herite de Person)
                │   ├── AirplanePilot.java   (herite de Employee)
                │   ├── StaffCabin.java      (herite de Employee)
                │   ├── Passenger.java       (herite de Person)
                │   ├── Flight.java
                │   ├── Aircraft.java
                │   ├── Airport.java
                │   └── Book.java
                ├── service/
                │   └── AirlineService.java  # gestion CRUD
                └── app/
                    └── Main.java            # programme principal
```

## Concepts POO utilises

- **Heritage** : Person -> Employee -> (AirplanePilot, StaffCabin), Person -> Passenger
- **Encapsulation** : attributs prives/proteges + getters/setters
- **Classes abstraites** : Person et Employee
- **Polymorphisme** : methodes getRole() et assignFlight() redefinies
- **Association** : Flight <-> Airport, Flight <-> Passenger
- **Composition** : Flight contient Aircraft, Book lie Passenger et Flight

## Fonctionnalites

### Gestion du personnel
- getInfos() : affichage infos d'une personne
- getRole() : role d'un employe

### Gestion des reservations
- bookFlight() : reserver un vol
- cancelBook() : annuler une reservation
- getReservations() : obtenir infos d'une reservation

### Gestion des vols
- assignFlight() : assignation equipage
- getFlight() : infos d'un vol
- planFlight() : planification
- cancelFlight() : annulation

### Gestion des avions
- affectFlight() : assignation a un vol
- checkAvailability() : verification disponibilite

### Bonus
- Rapport d'activite (nombre de vols, passagers, revenus)
- Top des destinations populaires

## Compilation et execution

Avec Maven :
```
mvn clean compile
mvn exec:java -Dexec.mainClass="com.isep.airline.app.Main"
```

Ou directement :
```
javac -d target/classes src/main/java/com/isep/airline/model/*.java src/main/java/com/isep/airline/service/*.java src/main/java/com/isep/airline/app/Main.java
java -cp target/classes com.isep.airline.app.Main
```

## Auteur

Projet realise dans le cadre du cours II.1102 - ISEP 2024/2025
