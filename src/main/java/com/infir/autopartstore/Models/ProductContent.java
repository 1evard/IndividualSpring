package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productСontent")
public class ProductContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String name;

    @OneToOne(optional = true, mappedBy = "productContent")
    private Products products;

    public ProductContent(Long id, String name, Products products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public ProductContent() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
