package com.tsystems.javaschool.webshop.dao.entities;


import com.tsystems.javaschool.webshop.dao.entities.enums.UserRole;
import com.tsystems.javaschool.webshop.dao.validation.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type User entity.
 */
@SuppressWarnings("CheckStyle")
@Entity
@NamedQueries({
        @NamedQuery(name = "UserEntity.getByEmail",
                query = "SELECT u FROM User u WHERE u.email = :email")
})
@Table(name = "user", schema = "web_shop")
public class User {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Email.
     */
    @Email
    @NotEmpty
    private String email;
    /**
     * The Password.
     */
    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Last name.
     */
    private String lastName;
    /**
     * The Phone.
     */
    @Phone
    private String phone;
    /**
     * The Birth date.
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Past
    private Date birthDate;
    /**
     * The Is admin.
     */
    @NotNull
    private UserRole role;
    /**
     * The Address.
     */
    private Address address;
    /**
     * The Orders.
     */
    private Set<Order> orders;

    /**
     * Instantiates a new User entity.
     */
    public User() {
        orders = new LinkedHashSet<>();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets is admin.
     *
     * @return the is admin
     */
    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets is admin.
     *
     * @param userType the user type
     */
    public void setRole(final UserRole userType) {
        this.role = userType;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public void setOrders(final Set<Order> orders) {
        this.orders = orders;
    }
}
