package com.tsystems.javaschool.webshop.dao.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "UserEntity.getByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email")
})
@Table(name = "user", schema = "web_shop")
public class UserEntity {
    private int id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Timestamp birthDate;
    private Boolean isAdmin;
    private AddressEntity address;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "birth_date")
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "is_admin")
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean userType) {
        this.isAdmin = userType;
    }

    @OneToOne
    @JoinColumn(name = "address_id")
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", isAdmin=" + isAdmin +
                ", address=" + address +
                '}';
    }
}
