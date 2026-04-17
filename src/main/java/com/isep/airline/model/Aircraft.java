package com.isep.airline.model;

import java.util.ArrayList;
import java.util.List;

public class Aircraft {

    private String registration;
    private String model;
    private int capacity;
    private List<Flight> scheduledFlights;

    public Aircraft() {
        this.registration = "";
        this.model = "";
        this.capacity = 0;
        this.scheduledFlights = new ArrayList<>();
    }

    public Aircraft(String registration, String model, int capacity) {
        this.registration = registration;
        this.model = model;
        this.capacity = capacity;
        this.scheduledFlights = new ArrayList<>();
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Flight> getScheduledFlights() {
        return scheduledFlights;
    }

    public boolean assignFlight(Flight flight) {
        if (flight == null) {
            System.out.println("Vol invalide.");
            return false;
        }
        if (!checkAvailability(flight)) {
            System.out.println("L'avion " + this.registration + " n'est pas disponible pour ce vol.");
            return false;
        }
        this.scheduledFlights.add(flight);
        flight.setAircraft(this);
        System.out.println("Avion " + this.registration + " assigne au vol " + flight.getFlightNumber());
        return true;
    }

    public boolean checkAvailability(Flight newFlight) {
        if (newFlight == null) return false;

        for (Flight f : this.scheduledFlights) {
            boolean overlap = !(newFlight.getArrivalDateTime().isBefore(f.getDepartureTime())
                    || newFlight.getDepartureTime().isAfter(f.getArrivalDateTime()));
            if (overlap) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aircraft [" + registration + " - " + model + " (capacity=" + capacity + ")]";
    }
}
