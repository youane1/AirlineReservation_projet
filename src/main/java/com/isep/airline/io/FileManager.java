package com.isep.airline.io;

import com.isep.airline.model.*;
import com.isep.airline.service.AirlineService;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FileManager {

    private String dataDirectory;

    public FileManager(String dataDirectory) {
        this.dataDirectory = dataDirectory;
        File dir = new File(dataDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public void listDirectory(String path) {
        File repertoire = new File(path);
        String liste[] = repertoire.list();
        if (liste != null) {
            System.out.println("Contenu du repertoire " + path + " :");
            for (int i = 0; i < liste.length; i++) {
                File f = new File(path + File.separator + liste[i]);
                String type = f.isDirectory() ? "[REP]" : "[FIC]";
                System.out.println("  " + type + " " + liste[i]);
            }
        } else {
            System.err.println("Nom de repertoire invalide : " + path);
        }
    }

    public void savePassengers(List<Passenger> passengers) throws IOException {
        File file = new File(dataDirectory + File.separator + "passengers.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Passenger p : passengers) {
            String line = p.getId() + ";" + p.getName() + ";" + p.getAddress() + ";"
                    + p.getContact() + ";" + p.getPassport();
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        System.out.println("Passagers sauvegardes dans " + file.getPath());
    }

    public void loadPassengers(AirlineService service) throws IOException {
        String filePath = dataDirectory + File.separator + "passengers.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Fichier passengers.txt introuvable.");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String curLine;
        while ((curLine = br.readLine()) != null) {
            String[] parts = curLine.split(";");
            if (parts.length >= 5) {
                Passenger p = new Passenger(parts[0], parts[1], parts[2], parts[3], parts[4]);
                service.addPassenger(p);
            }
        }
        br.close();
        System.out.println("Passagers charges depuis " + filePath);
    }

    public void saveFlights(List<Flight> flights) throws IOException {
        File file = new File(dataDirectory + File.separator + "flights.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Flight f : flights) {
            String originName = f.getOrigin() != null ? f.getOrigin().getName() : "";
            String destName = f.getDestination() != null ? f.getDestination().getName() : "";
            String line = f.getFlightNumber() + ";" + originName + ";" + destName + ";"
                    + f.getDepartureTime() + ";" + f.getArrivalDateTime() + ";" + f.getStatus();
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        System.out.println("Vols sauvegardes dans " + file.getPath());
    }

    public void loadFlights(AirlineService service) throws IOException {
        String filePath = dataDirectory + File.separator + "flights.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Fichier flights.txt introuvable.");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String curLine;
        while ((curLine = br.readLine()) != null) {
            String[] parts = curLine.split(";");
            if (parts.length >= 6) {
                Airport origin = service.findAirportByName(parts[1]);
                Airport dest = service.findAirportByName(parts[2]);
                LocalDateTime dep = LocalDateTime.parse(parts[3]);
                LocalDateTime arr = LocalDateTime.parse(parts[4]);
                Flight f = new Flight(parts[0], origin, dest, dep, arr);
                f.setStatus(parts[5]);
                service.addFlight(f);
            }
        }
        br.close();
        System.out.println("Vols charges depuis " + filePath);
    }

    public void saveAirports(List<Airport> airports) throws IOException {
        File file = new File(dataDirectory + File.separator + "airports.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Airport a : airports) {
            String line = a.getName() + ";" + a.getCity() + ";" + a.getDescription();
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        System.out.println("Aeroports sauvegardes dans " + file.getPath());
    }

    public void loadAirports(AirlineService service) throws IOException {
        String filePath = dataDirectory + File.separator + "airports.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Fichier airports.txt introuvable.");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String curLine;
        while ((curLine = br.readLine()) != null) {
            String[] parts = curLine.split(";");
            if (parts.length >= 3) {
                Airport a = new Airport(parts[0], parts[1], parts[2]);
                service.addAirport(a);
            }
        }
        br.close();
        System.out.println("Aeroports charges depuis " + filePath);
    }

    public void saveAircrafts(List<Aircraft> aircrafts) throws IOException {
        File file = new File(dataDirectory + File.separator + "aircrafts.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Aircraft a : aircrafts) {
            String line = a.getRegistration() + ";" + a.getModel() + ";" + a.getCapacity();
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        System.out.println("Avions sauvegardes dans " + file.getPath());
    }

    public void loadAircrafts(AirlineService service) throws IOException {
        String filePath = dataDirectory + File.separator + "aircrafts.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Fichier aircrafts.txt introuvable.");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String curLine;
        while ((curLine = br.readLine()) != null) {
            String[] parts = curLine.split(";");
            if (parts.length >= 3) {
                Aircraft a = new Aircraft(parts[0], parts[1], Integer.parseInt(parts[2]));
                service.addAircraft(a);
            }
        }
        br.close();
        System.out.println("Avions charges depuis " + filePath);
    }

    public void saveReport(AirlineService service) throws IOException {
        Path path = Paths.get(dataDirectory + File.separator + "report.txt");
        StringBuilder sb = new StringBuilder();
        sb.append("===== RAPPORT D'ACTIVITE =====\n");
        sb.append("Nombre de vols : ").append(service.getAllFlights().size()).append("\n");

        int totalPass = 0;
        for (Flight f : service.getAllFlights()) {
            totalPass += f.getPassengers().size();
        }
        sb.append("Passagers transportes : ").append(totalPass).append("\n");
        sb.append("Revenus estimes : ").append(totalPass * 200.0).append(" euros\n");
        sb.append("Nombre d'avions : ").append(service.getAllAircrafts().size()).append("\n");
        sb.append("Nombre d'employes : ").append(service.getAllEmployees().size()).append("\n");
        sb.append("Nombre de reservations : ").append(service.getAllReservations().size()).append("\n");
        sb.append("==============================\n");

        byte[] bytes = sb.toString().getBytes();
        Files.write(path, bytes);
        System.out.println("Rapport sauvegarde dans " + path);
    }

    public void readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("Fichier introuvable : " + filePath);
            return;
        }
        System.out.println("--- Contenu de " + filePath + " ---");
        BufferedReader br = Files.newBufferedReader(path);
        String curLine;
        while ((curLine = br.readLine()) != null) {
            System.out.println(curLine);
        }
        br.close();
        System.out.println("--- Fin ---");
    }

    public void saveAll(AirlineService service) throws IOException {
        saveAirports(service.getAllAirports());
        saveAircrafts(service.getAllAircrafts());
        savePassengers(service.getAllPassengers());
        saveFlights(service.getAllFlights());
        saveReport(service);
        System.out.println("Toutes les donnees ont ete sauvegardees.");
    }

    public void loadAll(AirlineService service) throws IOException {
        loadAirports(service);
        loadAircrafts(service);
        loadPassengers(service);
        loadFlights(service);
        System.out.println("Toutes les donnees ont ete chargees.");
    }
}
