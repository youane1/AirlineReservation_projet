package com.isep.airline.app;

import com.isep.airline.model.*;
import com.isep.airline.service.AirlineService;
import com.isep.airline.io.FileManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   AIRLINE RESERVATION SYSTEM       ");
        System.out.println("====================================\n");

        AirlineService service = new AirlineService();
        FileManager fileManager = new FileManager("data");

        System.out.println(">> Creation des aeroports...");
        Airport cdg = new Airport("Charles de Gaulle", "Paris", "Aeroport international de Paris");
        Airport jfk = new Airport("JFK", "New York", "John F Kennedy International Airport");
        Airport nrt = new Airport("Narita", "Tokyo", "Aeroport principal de Tokyo");

        service.addAirport(cdg);
        service.addAirport(jfk);
        service.addAirport(nrt);

        System.out.println("\n>> Creation des avions...");
        Aircraft a1 = new Aircraft("F-HXYZ", "Airbus A380", 500);
        Aircraft a2 = new Aircraft("F-ABCD", "Boeing 777", 300);
        Aircraft a3 = new Aircraft("F-EFGH", "Airbus A320", 180);

        service.addAircraft(a1);
        service.addAircraft(a2);
        service.addAircraft(a3);

        System.out.println("\n>> Creation des employes...");
        AirplanePilot pilot1 = new AirplanePilot(
                "E001", "Jean Dupont", "10 rue Paris", "jean@airline.fr",
                "EMP001", LocalDate.of(2015, 3, 10),
                "ATPL", 5000);

        AirplanePilot pilot2 = new AirplanePilot(
                "E002", "Marie Leblanc", "25 av Lyon", "marie@airline.fr",
                "EMP002", LocalDate.of(2018, 7, 22),
                "ATPL", 3200);

        StaffCabin staff1 = new StaffCabin(
                "E003", "Paul Martin", "5 rue Nice", "paul@airline.fr",
                "EMP003", LocalDate.of(2020, 1, 15),
                "Chef de cabine");

        StaffCabin staff2 = new StaffCabin(
                "E004", "Sophie Bernard", "12 bd Marseille", "sophie@airline.fr",
                "EMP004", LocalDate.of(2021, 5, 3),
                "Hotesse");

        service.addEmployee(pilot1);
        service.addEmployee(pilot2);
        service.addEmployee(staff1);
        service.addEmployee(staff2);

        System.out.println("\nRole de E001 : " + service.getRole("E001"));
        System.out.println("Role de E003 : " + service.getRole("E003"));

        System.out.println("\n>> Creation des passagers...");
        Passenger p1 = new Passenger("P001", "Alice Durand", "3 rue Paris",
                "alice@mail.fr", "FR12345");
        Passenger p2 = new Passenger("P002", "Bob Morel", "8 av Lyon",
                "bob@mail.fr", "FR67890");
        Passenger p3 = new Passenger("P003", "Claire Petit", "15 bd Nice",
                "claire@mail.fr", "FR11111");

        service.addPassenger(p1);
        service.addPassenger(p2);
        service.addPassenger(p3);

        System.out.println("\n>> Planification des vols...");
        Flight f1 = new Flight("AF001", cdg, jfk,
                LocalDateTime.of(2025, 1, 15, 10, 0),
                LocalDateTime.of(2025, 1, 15, 18, 0));

        Flight f2 = new Flight("AF002", cdg, nrt,
                LocalDateTime.of(2025, 1, 16, 11, 30),
                LocalDateTime.of(2025, 1, 17, 6, 0));

        Flight f3 = new Flight("AF003", jfk, cdg,
                LocalDateTime.of(2025, 1, 20, 14, 0),
                LocalDateTime.of(2025, 1, 20, 23, 30));

        service.planFlight(Arrays.asList(f1, f2, f3));

        System.out.println("\n>> Affectation des avions...");
        a1.assignFlight(f1);
        a2.assignFlight(f2);
        a3.assignFlight(f3);

        System.out.println("\n>> Test check availability :");
        boolean dispo = a1.checkAvailability(f3);
        System.out.println("Avion A380 dispo pour f3 : " + dispo);

        System.out.println("\n>> Affectation du personnel...");
        pilot1.assignFlight(f1);
        staff1.assignFlight(f1);
        staff2.assignFlight(f1);

        pilot2.assignFlight(f2);
        staff1.assignFlight(f2);

        System.out.println("\n>> Reservations...");
        Book b1 = p1.bookFlight(f1);
        Book b2 = p2.bookFlight(f1);
        Book b3 = p3.bookFlight(f2);

        if (b1 != null) service.addReservation(b1);
        if (b2 != null) service.addReservation(b2);
        if (b3 != null) service.addReservation(b3);

        if (b1 != null) b1.confirmReservation();

        System.out.println("\n>> Informations des personnes :");
        p1.getInfos();
        System.out.println();
        pilot1.getInfos();

        System.out.println("\n>> Liste des passagers sur le vol AF001 :");
        f1.listingPassenger();

        System.out.println("\n>> Test d'annulation de reservation...");
        if (b2 != null) {
            p2.cancelFlight(b2.getReservationNumber());
        }

        System.out.println("\n>> Liste des passagers du vol AF001 apres annulation :");
        f1.listingPassenger();

        System.out.println("\n>> Modification du vol AF003...");
        f3.modifyFlight(
                LocalDateTime.of(2025, 1, 20, 15, 0),
                LocalDateTime.of(2025, 1, 21, 0, 30));

        service.generateReport();
        service.displayPopularDestinations();

        System.out.println(">> Annulation du vol AF002...");
        service.cancelFlight("AF002");

        System.out.println("\n>> Sauvegarde des donnees dans des fichiers...");
        try {
            fileManager.saveAll(service);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }

        System.out.println("\n>> Contenu du repertoire data :");
        fileManager.listDirectory("data");

        System.out.println("\n>> Lecture du fichier report.txt :");
        try {
            fileManager.readFile("data/report.txt");
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }

        System.out.println("\n>> Lecture du fichier passengers.txt :");
        try {
            fileManager.readFile("data/passengers.txt");
        } catch (IOException e) {
            System.err.println("Erreur de lecture : " + e.getMessage());
        }

        System.out.println("\n>> Test chargement depuis fichiers...");
        AirlineService service2 = new AirlineService();
        try {
            fileManager.loadAll(service2);
            System.out.println("Passagers charges : " + service2.getAllPassengers().size());
            System.out.println("Aeroports charges : " + service2.getAllAirports().size());
            System.out.println("Avions charges : " + service2.getAllAircrafts().size());
            System.out.println("Vols charges : " + service2.getAllFlights().size());
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }

        System.out.println("\n====== FIN DU PROGRAMME ======");
    }
}
