package com.isep.airline.service;

import com.isep.airline.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

public class AirlineService {

    private List<Passenger> passengers;
    private List<Employee> employees;
    private List<Flight> flights;
    private List<Aircraft> aircrafts;
    private List<Airport> airports;
    private List<Book> reservations;

    public AirlineService() {
        this.passengers = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.aircrafts = new ArrayList<>();
        this.airports = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addPassenger(Passenger p) {
        if (p != null && !passengers.contains(p)) {
            passengers.add(p);
            System.out.println("Passager ajoute : " + p.getName());
        }
    }

    public Passenger findPassengerById(String id) {
        for (Passenger p : passengers) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public boolean removePassenger(String id) {
        Passenger p = findPassengerById(id);
        if (p != null) {
            passengers.remove(p);
            System.out.println("Passager supprime : " + p.getName());
            return true;
        }
        return false;
    }

    public List<Passenger> getAllPassengers() {
        return passengers;
    }

    public void addEmployee(Employee e) {
        if (e != null && !employees.contains(e)) {
            employees.add(e);
            System.out.println("Employe ajoute : " + e.getName() + " (" + e.getRole() + ")");
        }
    }

    public Employee findEmployeeById(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public String getRole(String employeeId) {
        Employee e = findEmployeeById(employeeId);
        if (e != null) {
            return e.getRole();
        }
        return "Employe introuvable";
    }

    public boolean removeEmployee(String id) {
        Employee e = findEmployeeById(id);
        if (e != null) {
            employees.remove(e);
            return true;
        }
        return false;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public void addFlight(Flight f) {
        if (f != null && !flights.contains(f)) {
            flights.add(f);
            System.out.println("Vol ajoute : " + f.getFlightNumber());
        }
    }

    public Flight getFlight(String flightNumber) {
        for (Flight f : flights) {
            if (f.getFlightNumber().equals(flightNumber)) {
                return f;
            }
        }
        return null;
    }

    public boolean removeFlight(String flightNumber) {
        Flight f = getFlight(flightNumber);
        if (f != null) {
            flights.remove(f);
            return true;
        }
        return false;
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public void planFlight(List<Flight> flightsToPlan) {
        if (flightsToPlan == null) return;
        for (Flight f : flightsToPlan) {
            this.addFlight(f);
            f.planFlight();
        }
    }

    public boolean cancelFlight(String flightNumber) {
        Flight f = getFlight(flightNumber);
        if (f != null) {
            f.cancelFlight();
            return true;
        }
        System.out.println("Vol " + flightNumber + " introuvable.");
        return false;
    }

    public void addAircraft(Aircraft a) {
        if (a != null && !aircrafts.contains(a)) {
            aircrafts.add(a);
            System.out.println("Avion ajoute : " + a.getRegistration());
        }
    }

    public Aircraft findAircraft(String registration) {
        for (Aircraft a : aircrafts) {
            if (a.getRegistration().equals(registration)) {
                return a;
            }
        }
        return null;
    }

    public List<Aircraft> getAvailableAircrafts(Flight flight) {
        List<Aircraft> available = new ArrayList<>();
        for (Aircraft a : aircrafts) {
            if (a.checkAvailability(flight)) {
                available.add(a);
            }
        }
        return available;
    }

    public List<Aircraft> getAllAircrafts() {
        return aircrafts;
    }

    public void addAirport(Airport a) {
        if (a != null && !airports.contains(a)) {
            airports.add(a);
        }
    }

    public Airport findAirportByName(String name) {
        for (Airport a : airports) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public List<Airport> getAllAirports() {
        return airports;
    }

    public void addReservation(Book b) {
        if (b != null && !reservations.contains(b)) {
            reservations.add(b);
        }
    }

    public boolean cancelBook(String reservationNumber) {
        for (Book b : reservations) {
            if (b.getReservationNumber().equals(reservationNumber)) {
                b.cancelReservation();
                reservations.remove(b);
                if (b.getPassenger() != null) {
                    b.getPassenger().getBooks().remove(b);
                }
                return true;
            }
        }
        System.out.println("Reservation " + reservationNumber + " introuvable.");
        return false;
    }

    public Book getReservations(String reservationNumber) {
        for (Book b : reservations) {
            if (b.getReservationNumber().equals(reservationNumber)) {
                return b;
            }
        }
        return null;
    }

    public List<Book> getAllReservations() {
        return reservations;
    }

    public void generateReport() {
        System.out.println("\n===== RAPPORT D'ACTIVITE =====");
        System.out.println("Nombre total de vols : " + flights.size());

        int totalPassengers = 0;
        for (Flight f : flights) {
            totalPassengers += f.getPassengers().size();
        }
        System.out.println("Passagers transportes : " + totalPassengers);

        double revenue = totalPassengers * 200.0;
        System.out.println("Revenus estimes : " + revenue + " euros");

        System.out.println("Nombre d'avions : " + aircrafts.size());
        System.out.println("Nombre d'employes : " + employees.size());
        System.out.println("Nombre de reservations : " + reservations.size());
        System.out.println("==============================\n");
    }

    public void displayPopularDestinations() {
        Map<String, Integer> destCount = new HashMap<>();

        for (Flight f : flights) {
            if (f.getDestination() != null) {
                String city = f.getDestination().getCity();
                int nbPassengers = f.getPassengers().size();
                destCount.put(city, destCount.getOrDefault(city, 0) + nbPassengers);
            }
        }

        System.out.println("\n=== Destinations les plus populaires ===");
        if (destCount.isEmpty()) {
            System.out.println("Aucune donnee disponible.");
            return;
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(destCount.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        int rank = 1;
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(rank + ". " + entry.getKey() + " - " + entry.getValue() + " passagers");
            rank++;
            if (rank > 5) break;
        }
        System.out.println();
    }

        public void importFlightsFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(";");

                if (data.length < 5) continue;

                String flightNumber = data[0];
                String originName = data[1];
                String destinationName = data[2];
                String date = data[3];
                String time = data[4];

                Airport origin = findAirportByName(originName);
                if (origin == null) {
                    origin = new Airport(originName, originName, "");
                    addAirport(origin);
                }

                Airport destination = findAirportByName(destinationName);
                if (destination == null) {
                    destination = new Airport(destinationName, destinationName, "");
                    addAirport(destination);
                }

                Flight f = new Flight();
                f.setFlightNumber(flightNumber);
                f.setOrigin(origin);
                f.setDestination(destination);

                f.setDepartureTime(LocalDateTime.now());
                f.setArrivalDateTime(LocalDateTime.now().plusHours(2));
                f.setStatus("PLANNED");

                addFlight(f);
            }

            System.out.println("Import CSV terminé.");

        } catch (Exception e) {
            System.out.println("Erreur lecture CSV : " + e.getMessage());
        }
    }
}
