package com.infir.autopartstore.Models;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;

@Entity
@Table(name = "cheque")
public class Cheque{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Ввод обязателен")
    @Range(min = 0, max = 500, message = "Значение от 0 до 500")
    private Long quantity;
    @NotNull(message = "Необходимо ввести дату")
    private String timesell;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Products products;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Client client;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Employee employees;

    public Cheque(Long id, Long quantity, String timesell, Products products, Client client, Employee employees) {
        this.id = id;
        this.quantity = quantity;
        this.timesell = timesell;
        this.products = products;
        this.client = client;
        this.employees = employees;
    }

    public Cheque() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getTimesell() {
        return timesell;
    }

    public void setTimesell(String timesell) {
        this.timesell = timesell;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employees;
    }

    public void setEmployee(Employee employee) {
        this.employees = employee;
    }
}
