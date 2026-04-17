package com.isep.airline.model;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    private String name;
    private String city;
    private String description;
    private List<Flight> flights;

    public Airport() {
        this.name = "";
        this.city = "";
        this.description = "";
        this.flights = new ArrayList<>();
    }

    public Airport(String name, String city, String description) {
        this.name = name;
        this.city = city;
        this.description = description;
        this.flights = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void assignFlight(Flight flight) {
        if (flight != null && !this.flights.contains(flight)) {
            this.flights.add(flight);
        }
    }

    @Override
    public String toString() {
        return name + " (" + city + ")";
    }
}
