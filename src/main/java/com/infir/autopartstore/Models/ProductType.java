package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "productType")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String name;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private ProductCategory productCategory;

    @ManyToMany
    @JoinTable(name = "typeStamp",
            joinColumns = @JoinColumn(name = "productType_id"),
            inverseJoinColumns = @JoinColumn(name = "stamp_id"))
    private List<Stamp> stamps;

    public ProductType(Long id, String name, ProductCategory productCategory, List<Stamp> stamps) {
        this.id = id;
        this.name = name;
        this.productCategory = productCategory;
        this.stamps = stamps;
    }

    public ProductType() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<Stamp> getStamps() {
        return stamps;
    }

    public void setStamps(List<Stamp> stamps) {
        this.stamps = stamps;
    }
}
