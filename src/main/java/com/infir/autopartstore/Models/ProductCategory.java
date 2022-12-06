package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "productCategory")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String name;

    @OneToMany(mappedBy = "productCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ProductType> productTypes;

    public ProductCategory(Long id, String name, Collection<ProductType> productTypes) {
        this.id = id;
        this.name = name;
        this.productTypes = productTypes;
    }

    public ProductCategory() {

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

    public Collection<ProductType> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(Collection<ProductType> productTypes) {
        this.productTypes = productTypes;
    }
}
