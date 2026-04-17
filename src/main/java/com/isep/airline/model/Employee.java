package com.isep.airline.model;

import java.time.LocalDate;

public abstract class Employee extends Person {

    protected String numberEmp;
    protected LocalDate hiringDate;

    public Employee() {
        super();
        this.numberEmp = "";
        this.hiringDate = LocalDate.now();
    }

    public Employee(String id, String name, String address, String contact,
                    String numberEmp, LocalDate hiringDate) {
        super(id, name, address, contact);
        this.numberEmp = numberEmp;
        this.hiringDate = hiringDate;
    }

    public String getNumberEmp() {
        return numberEmp;
    }

    public void setNumberEmp(String numberEmp) {
        this.numberEmp = numberEmp;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public abstract String getRole();

    public abstract void assignFlight(Flight flight);

    @Override
    public void getInfos() {
        super.getInfos();
        System.out.println("Numero employe : " + this.numberEmp);
        System.out.println("Date embauche : " + this.hiringDate);
        System.out.println("Role : " + this.getRole());
    }
}
