package com.infir.autopartstore.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String titleproducts;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 250, message = "От 1 до 250 символов")
    private String description;
    @NotNull(message = "Ввод обязателен")
    @DecimalMax(value = "200000.0", message = "Максимальное значение 200000.0 руб.")
    @DecimalMin(value = "0.0", message = "Стоимость не может быть меньше 0")
    private Double cost;

    public Long getArticul() {
        return articul;
    }

    public void setArticul(Long articul) {
        this.articul = articul;
    }

    @NotNull(message = "Поле не может быть пустым")
    private Long articul;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Stamp stamp;

    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private StampCar stampCar;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "currentBalance_id")
    private CurrentBalance currentBalance;

    public ProductContent getProductContent() {
        return productContent;
    }

    public void setProductContent(ProductContent productContent) {
        this.productContent = productContent;
    }

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "productContent_id")
    private ProductContent productContent;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cheque> cheques;

    @OneToMany(mappedBy = "productss", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Basket> baskets;

    public Products(Long id, String titleproducts, String description, Double cost, Long articul, Stamp stamp, StampCar stampCar, CurrentBalance currentBalance, ProductContent productContent, Set<Cheque> cheques, Set<Basket> baskets) {
        this.id = id;
        this.titleproducts = titleproducts;
        this.description = description;
        this.cost = cost;
        this.articul = articul;
        this.stamp = stamp;
        this.stampCar = stampCar;
        this.currentBalance = currentBalance;
        this.productContent = productContent;
        this.cheques = cheques;
        this.baskets = baskets;
    }

    public Products() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleproducts() {
        return titleproducts;
    }

    public void setTitleproducts(String titleproducts) {
        this.titleproducts = titleproducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Stamp stamp) {
        this.stamp = stamp;
    }

    public CurrentBalance getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(CurrentBalance currentBalance) {
        this.currentBalance = currentBalance;
    }


    public Set<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(Set<Cheque> cheques) {
        this.cheques = cheques;
    }

    public Set<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Set<Basket> baskets) {
        this.baskets = baskets;
    }

    public StampCar getStampCar() {
        return stampCar;
    }

    public void setStampCar(StampCar stampCar) {
        this.stampCar = stampCar;
    }
}
