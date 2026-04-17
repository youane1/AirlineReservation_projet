package com.isep.airline.model;

import java.time.LocalDate;

public class Book {

    private String reservationNumber;
    private LocalDate dateReservation;
    private String status;
    private Passenger passenger;
    private Flight flight;

    public Book() {
        this.reservationNumber = "";
        this.dateReservation = LocalDate.now();
        this.status = "En attente";
    }

    public Book(String reservationNumber, String status, Passenger passenger, Flight flight) {
        this.reservationNumber = reservationNumber;
        this.dateReservation = LocalDate.now();
        this.status = status;
        this.passenger = passenger;
        this.flight = flight;
    }

    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber; }

    public LocalDate getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDate dateReservation) { this.dateReservation = dateReservation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Passenger getPassenger() { return passenger; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public void confirmReservation() {
        this.status = "Confirmee";
        System.out.println("Reservation " + this.reservationNumber + " confirmee.");
    }

    public void cancelReservation() {
        this.status = "Annulee";
        if (flight != null && passenger != null) {
            flight.getPassengers().remove(passenger);
        }
        System.out.println("Reservation " + this.reservationNumber + " annulee.");
    }

    public void modifyReservation(Flight newFlight) {
        if (newFlight == null) {
            System.out.println("Nouveau vol invalide.");
            return;
        }
        if (this.flight != null && this.passenger != null) {
            this.flight.getPassengers().remove(this.passenger);
        }
        this.flight = newFlight;
        newFlight.addPassenger(this.passenger);
        System.out.println("Reservation " + this.reservationNumber
                + " modifiee. Nouveau vol : " + newFlight.getFlightNumber());
    }

    public void printInfos() {
        System.out.println("=== Reservation " + reservationNumber + " ===");
        System.out.println("Date : " + dateReservation);
        System.out.println("Statut : " + status);
        if (passenger != null) {
            System.out.println("Passager : " + passenger.getName());
        }
        if (flight != null) {
            System.out.println("Vol : " + flight.getFlightNumber()
                    + " (" + flight.getOrigin() + " -> " + flight.getDestination() + ")");
        }
    }

    @Override
    public String toString() {
        return "Book[" + reservationNumber + ", " + status + "]";
    }
}
