package com.isep.airline.model;

public abstract class Person {

    protected String id;
    protected String name;
    protected String address;
    protected String contact;

    public Person() {
        this.id = "";
        this.name = "";
        this.address = "";
        this.contact = "";
    }

    public Person(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void getInfos() {
        System.out.println("----- Informations -----");
        System.out.println("ID : " + this.id);
        System.out.println("Nom : " + this.name);
        System.out.println("Adresse : " + this.address);
        System.out.println("Contact : " + this.contact);
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + "]";
    }
}
