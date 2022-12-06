package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "stampcar")
public class StampCar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String titlestampcar;

    @OneToMany(mappedBy = "stampCar", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Products> products;

    public StampCar(Long id, String titlestampcar, Collection<Products> products) {
        this.id = id;
        this.titlestampcar = titlestampcar;
        this.products = products;
    }

    public StampCar() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitlestampcar() {
        return titlestampcar;
    }

    public void setTitlestampcar(String titlestampcar) {
        this.titlestampcar = titlestampcar;
    }

    public Collection<Products> getProducts() {
        return products;
    }

    public void setProducts(Collection<Products> products) {
        this.products = products;
    }
}
