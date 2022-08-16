package com.electrolux.task.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appliance")
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "factoryNr")
    private String factoryNr;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created")
    private LocalDateTime created;

    public Appliance() {
    }

    public Appliance(String factoryNr, String type, String description, String status, LocalDateTime created) {
        this.factoryNr = factoryNr;
        this.type = type;
        this.description = description;
        this.status = status;
        this.created = created;
    }

    public long getId() {
        return id;
    }

    public String getFactoryNr() {
        return factoryNr;
    }

    public void setFactoryNr(String title) {
        this.factoryNr = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String description) {
        this.type = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Appliance [id=" + id + ", title=" + factoryNr + ", desc=" + type +
                ", published=" + description + ", created=" + created + "]";
    }

}
