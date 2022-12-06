package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "stamp")
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String titlestamp;

    @OneToMany(mappedBy = "stamp", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Products> products;

    @OneToOne(optional = true, mappedBy = "stamp")
    private Manufacturer manufacturer;

    @ManyToMany
    @JoinTable(name = "typeStamp",
            joinColumns = @JoinColumn(name = "stamp_id"),
            inverseJoinColumns = @JoinColumn(name = "productType_id"))
    private List<ProductType> productTypes;

    public Stamp(Long id, String titlestamp, Collection<Products> products, Manufacturer manufacturer, List<ProductType> productTypes) {
        this.id = id;
        this.titlestamp = titlestamp;
        this.products = products;
        this.manufacturer = manufacturer;
        this.productTypes = productTypes;
    }

    public Stamp() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlestamp() {
        return titlestamp;
    }

    public void setTitlestamp(String titlestamp) {
        this.titlestamp = titlestamp;
    }

    public Collection<Products> getProducts() {
        return products;
    }

    public void setProducts(Collection<Products> products) {
        this.products = products;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductType> productTypes) {
        this.productTypes = productTypes;
    }
}
