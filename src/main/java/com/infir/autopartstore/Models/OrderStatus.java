package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "orderStatus")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String name;

    @OneToMany(mappedBy = "orderStatus", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Order> orders;

    public OrderStatus(Long id, String name, Collection<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public OrderStatus() {

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

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }
}
