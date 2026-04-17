package com.isep.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirplanePilot extends Employee {

    private String licence;
    private int flightHours;
    private List<Flight> assignedFlights;

    public AirplanePilot() {
        super();
        this.licence = "";
        this.flightHours = 0;
        this.assignedFlights = new ArrayList<>();
    }

    public AirplanePilot(String id, String name, String address, String contact,
                         String numberEmp, LocalDate hiringDate,
                         String licence, int flightHours) {
        super(id, name, address, contact, numberEmp, hiringDate);
        this.licence = licence;
        this.flightHours = flightHours;
        this.assignedFlights = new ArrayList<>();
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    public List<Flight> getAssignedFlights() {
        return assignedFlights;
    }

    @Override
    public String getRole() {
        return "Pilote";
    }

    @Override
    public void assignFlight(Flight flight) {
        if (flight == null) {
            System.out.println("Erreur : vol invalide");
            return;
        }
        if (this.assignedFlights.contains(flight)) {
            System.out.println("Ce pilote est deja assigne a ce vol !");
            return;
        }
        this.assignedFlights.add(flight);
        flight.setPilot(this);
        System.out.println("Pilote " + this.name + " assigne au vol " + flight.getFlightNumber());
    }

    public List<Flight> obtainVol() {
        return this.assignedFlights;
    }

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Licence : " + this.licence);
        System.out.println("Heures de vol : " + this.flightHours);
        System.out.println("Nombre de vols assignes : " + this.assignedFlights.size());
    }
}
