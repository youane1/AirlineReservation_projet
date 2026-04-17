package com.isep.airline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffCabin extends Employee {

    private String qualification;
    private List<Flight> assignedFlights;

    public StaffCabin() {
        super();
        this.qualification = "";
        this.assignedFlights = new ArrayList<>();
    }

    public StaffCabin(String id, String name, String address, String contact,
                      String numberEmp, LocalDate hiringDate, String qualification) {
        super(id, name, address, contact, numberEmp, hiringDate);
        this.qualification = qualification;
        this.assignedFlights = new ArrayList<>();
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<Flight> getAssignedFlights() {
        return assignedFlights;
    }

    @Override
    public String getRole() {
        return "Personnel de cabine";
    }

    @Override
    public void assignFlight(Flight flight) {
        if (flight == null) {
            System.out.println("Erreur : vol invalide");
            return;
        }
        if (this.assignedFlights.contains(flight)) {
            System.out.println("Ce membre est deja sur ce vol !");
            return;
        }
        this.assignedFlights.add(flight);
        flight.addStaffCabin(this);
        System.out.println(this.name + " (cabine) assigne(e) au vol " + flight.getFlightNumber());
    }

    public List<Flight> obtainVol() {
        return this.assignedFlights;
    }

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Qualification : " + this.qualification);
        System.out.println("Nombre de vols : " + this.assignedFlights.size());
    }
}
