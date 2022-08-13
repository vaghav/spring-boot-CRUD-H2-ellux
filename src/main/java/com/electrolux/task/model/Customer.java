package com.electrolux.task.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Appliance> appliances;

    public Customer() {
    }

    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
        appliance.setCustomer(this);
    }

    public void addAllAppliances(List<Appliance> appliances) {
        for (Appliance appliance: appliances) {
            this.appliances.add(appliance);
            appliance.setCustomer(this);
        }
    }

    public Customer(final String name, final String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(final List<Appliance> appliance) {
        this.appliances = appliance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
}
