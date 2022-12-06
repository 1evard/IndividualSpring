package com.infir.autopartstore.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Необходимо ввести дату")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Дата не может быть будущей")
    private Date data;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String noter;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Client clients;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Basket> baskets;

    public Order(Long id, Date data, String noter, Client clients, OrderStatus orderStatus, Collection<Basket> baskets) {
        this.id = id;
        this.data = data;
        this.noter = noter;
        this.clients = clients;
        this.orderStatus = orderStatus;
        this.baskets = baskets;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNoter() {
        return noter;
    }

    public void setNoter(String noter) {
        this.noter = noter;
    }

    public Client getClient() {
        return clients;
    }

    public void setClient(Client clients) {
        this.clients = clients;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Collection<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Collection<Basket> baskets) {
        this.baskets = baskets;
    }
}
