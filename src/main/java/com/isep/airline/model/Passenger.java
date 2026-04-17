package com.isep.airline.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Person {

    private String passport;
    private List<Book> books;

    public Passenger() {
        super();
        this.passport = "";
        this.books = new ArrayList<>();
    }

    public Passenger(String id, String name, String address, String contact, String passport) {
        super(id, name, address, contact);
        this.passport = passport;
        this.books = new ArrayList<>();
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book bookFlight(Flight flight) {
        if (flight == null) {
            System.out.println("Vol invalide pour la reservation.");
            return null;
        }
        if (flight.getStatus().equalsIgnoreCase("Annule")) {
            System.out.println("Impossible de reserver : vol annule.");
            return null;
        }
        String resNumber = "RES" + System.currentTimeMillis() % 100000;
        Book reservation = new Book(resNumber, "En attente", this, flight);
        this.books.add(reservation);
        flight.addPassenger(this);
        System.out.println("Reservation effectuee : " + resNumber
                + " pour " + this.name + " sur le vol " + flight.getFlightNumber());
        return reservation;
    }

    public boolean cancelFlight(String reservationNumber) {
        for (Book b : this.books) {
            if (b.getReservationNumber().equals(reservationNumber)) {
                b.cancelReservation();
                this.books.remove(b);
                System.out.println("Reservation " + reservationNumber + " annulee.");
                return true;
            }
        }
        System.out.println("Reservation " + reservationNumber + " introuvable.");
        return false;
    }

    public List<Book> getBooksList() {
        return this.books;
    }

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Passeport : " + this.passport);
        System.out.println("Nombre de reservations : " + this.books.size());
    }
}
