package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String email;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String address;

    public Client(Long id, String email, String address, User users, Set<Cheque> cheques, Set<Order> orders) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.users = users;
        this.cheques = cheques;
        this.orders = orders;
    }

    public Client() {

    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User users;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cheque> cheques;

    @OneToMany(mappedBy = "clients", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
