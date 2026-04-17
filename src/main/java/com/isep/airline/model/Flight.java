package com.isep.airline.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {

    private String flightNumber;
    private Airport origin;
    private Airport destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalDateTime;
    private String status;

    private Aircraft aircraft;
    private AirplanePilot pilot;
    private List<StaffCabin> cabinCrew;
    private List<Passenger> passengers;

    public Flight() {
        this.flightNumber = "";
        this.status = "Planifie";
        this.cabinCrew = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public Flight(String flightNumber, Airport origin, Airport destination,
                  LocalDateTime departureTime, LocalDateTime arrivalDateTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalDateTime = arrivalDateTime;
        this.status = "Planifie";
        this.cabinCrew = new ArrayList<>();
        this.passengers = new ArrayList<>();

        if (origin != null) origin.assignFlight(this);
        if (destination != null) destination.assignFlight(this);
    }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public Airport getOrigin() { return origin; }
    public void setOrigin(Airport origin) { this.origin = origin; }

    public Airport getDestination() { return destination; }
    public void setDestination(Airport destination) { this.destination = destination; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalDateTime() { return arrivalDateTime; }
    public void setArrivalDateTime(LocalDateTime arrivalDateTime) { this.arrivalDateTime = arrivalDateTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    public AirplanePilot getPilot() { return pilot; }
    public void setPilot(AirplanePilot pilot) { this.pilot = pilot; }

    public List<StaffCabin> getCabinCrew() { return cabinCrew; }

    public List<Passenger> getPassengers() { return passengers; }

    public void planFlight() {
        this.status = "Planifie";
        System.out.println("Vol " + flightNumber + " planifie : "
                + origin + " -> " + destination
                + " (" + departureTime + " -> " + arrivalDateTime + ")");
    }

    public void cancelFlight() {
        this.status = "Annule";
        System.out.println("Vol " + flightNumber + " annule.");
        for (Passenger p : new ArrayList<>(this.passengers)) {
            for (Book b : new ArrayList<>(p.getBooks())) {
                if (b.getFlight() == this) {
                    b.cancelReservation();
                    p.getBooks().remove(b);
                }
            }
        }
    }

    public void modifyFlight(LocalDateTime newDeparture, LocalDateTime newArrival) {
        if (newDeparture == null || newArrival == null) {
            System.out.println("Horaires invalides.");
            return;
        }
        if (newArrival.isBefore(newDeparture)) {
            System.out.println("Erreur : l'heure d'arrivee doit etre apres le depart.");
            return;
        }
        this.departureTime = newDeparture;
        this.arrivalDateTime = newArrival;
        System.out.println("Vol " + flightNumber + " modifie.");
    }

    public void listingPassenger() {
        System.out.println("--- Passagers du vol " + flightNumber + " ---");
        if (passengers.isEmpty()) {
            System.out.println("(Aucun passager)");
            return;
        }
        int i = 1;
        for (Passenger p : passengers) {
            System.out.println(i + ". " + p.getName() + " (passeport: " + p.getPassport() + ")");
            i++;
        }
    }

    public void addPassenger(Passenger p) {
        if (p != null && !this.passengers.contains(p)) {
            if (aircraft != null && this.passengers.size() >= aircraft.getCapacity()) {
                System.out.println("Vol complet ! Impossible d'ajouter " + p.getName());
                return;
            }
            this.passengers.add(p);
        }
    }

    public void addStaffCabin(StaffCabin staff) {
        if (staff != null && !this.cabinCrew.contains(staff)) {
            this.cabinCrew.add(staff);
        }
    }

    @Override
    public String toString() {
        return "Flight [" + flightNumber + " " + origin + " -> " + destination
                + " @ " + departureTime + ", status=" + status + "]";
    }
}
