package com.infir.autopartstore.Models;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 70)
    private String login;
    @NotBlank
    private String password;

    @NotNull
    private boolean active;

    @NotBlank
    @Size(min = 1, max = 70)
    private String surname = "-";

    @NotBlank
    @Size(min = 1, max = 70)
    private String name = "-";

    @NotBlank
    @Size(min = 1, max = 70)
    private String middlename = "-";

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    public Date birthdate = new Date();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String isRole(){
        String inString = roles.toString();
        return roles.toString().replace("USER","Пользователь").replace("ADMIN","Администратор").replace("EMPLOYEE","Сотрудник");

    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @OneToOne(optional = true, mappedBy = "user")
    private Employee employee;

    @OneToOne(optional = true, mappedBy = "users")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setLogin(String username) {
        this.login = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
