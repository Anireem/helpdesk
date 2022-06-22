package com.skorpofeo.helpdesk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String customer; // TODO: 6/22/2022 change to model 
    private String responsible; // TODO: 6/22/2022 change to model

    // TODO: 6/22/2022 add date 

    public Ticket() {
        super(); // TODO: 6/22/2022 check how it works without super
    }

    public Ticket(String name, String description, String customer, String responsible) {
        this.name = name;
        this.description = description;
        this.customer = customer;
        this.responsible = responsible;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}
