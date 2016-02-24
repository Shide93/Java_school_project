package com.tsystems.javaschool.webshop.dao.entities;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * The type User entity.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "UserEntity.getByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email")
})
@Table(name = "user", schema = "web_shop")
public class UserEntity {
    /**
     * The Id.
     */
    private int id;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Password.
     */
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
    private String phone;
    /**
     * The Birth date.
     */
    private Date birthDate;
    /**
     * The Is admin.
     */
    private Boolean isAdmin;
    /**
     * The Address.
     */
    private AddressEntity address;
    /**
     * The Orders.
     */
    private Set<OrderEntity> orders;

    /**
     * Gets id.
     *
     * @return the id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    @Basic
    @Column(name = "email")
    public final String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    @Basic
    @Column(name = "password")
    public final String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    @Basic
    @Column(name = "name")
    public final String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    @Basic
    @Column(name = "last_name")
    public final String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    @Basic
    @Column(name = "phone")
    public final String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public final void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * Gets birth date.
     *
     * @return the birth date
     */
    @Basic
    @Column(name = "birth_date")
    public final Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets birth date.
     *
     * @param birthDate the birth date
     */
    public final void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets is admin.
     *
     * @return the is admin
     */
    @Basic
    @Column(name = "is_admin")
    public final Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets is admin.
     *
     * @param userType the user type
     */
    public final void setIsAdmin(final Boolean userType) {
        this.isAdmin = userType;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    public final AddressEntity getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public final void setAddress(final AddressEntity address) {
        this.address = address;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public final Set<OrderEntity> getOrders() {
        return orders;
    }

    /**
     * Sets orders.
     *
     * @param orders the orders
     */
    public final void setOrders(final Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
