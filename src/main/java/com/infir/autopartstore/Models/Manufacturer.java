package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String name;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "stamp_id")
    private Stamp stamp;

    public Manufacturer(Long id, String name, Stamp stamp) {
        this.id = id;
        this.name = name;
        this.stamp = stamp;
    }

    public Manufacturer() {

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

    public Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
    }
}
