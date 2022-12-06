package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String address;

    @NotNull(message = "Поле не может быть пустым")
    private Long numberphone;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Post post;

    @OneToMany(mappedBy = "employees", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cheque> cheques;

    public Employee(Long id, String address, Long numberphone, User user, Post post, Set<Cheque> cheques) {
        this.id = id;
        this.address = address;
        this.numberphone = numberphone;
        this.user = user;
        this.post = post;
        this.cheques = cheques;
    }

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(Long numberphone) {
        this.numberphone = numberphone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }
}
