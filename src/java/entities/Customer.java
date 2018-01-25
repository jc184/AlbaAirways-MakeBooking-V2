/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author james
 */
@Entity
@Table(name = "customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id")
    , @NamedQuery(name = "Customer.findByTitle", query = "SELECT c FROM Customer c WHERE c.title = :title")
    , @NamedQuery(name = "Customer.findByFirstName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName")
    , @NamedQuery(name = "Customer.findBySurname", query = "SELECT c FROM Customer c WHERE c.surname = :surname")
    , @NamedQuery(name = "Customer.findByMobileNumber", query = "SELECT c FROM Customer c WHERE c.mobileNumber = :mobileNumber")
    , @NamedQuery(name = "Customer.findByHomePhoneNumber", query = "SELECT c FROM Customer c WHERE c.homePhoneNumber = :homePhoneNumber")
    , @NamedQuery(name = "Customer.findByEmailAddress", query = "SELECT c FROM Customer c WHERE c.emailAddress = :emailAddress")
    , @NamedQuery(name = "Customer.findByLoginName", query = "SELECT c FROM Customer c WHERE c.loginName = :loginName")
    , @NamedQuery(name = "Customer.findByLoginPassword", query = "SELECT c FROM Customer c WHERE c.loginPassword = :loginPassword")
    , @NamedQuery(name = "Customer.findByCardType", query = "SELECT c FROM Customer c WHERE c.cardType = :cardType")
    , @NamedQuery(name = "Customer.findByCardNumber", query = "SELECT c FROM Customer c WHERE c.cardNumber = :cardNumber")
    , @NamedQuery(name = "Customer.findByCardExpiry", query = "SELECT c FROM Customer c WHERE c.cardExpiry = :cardExpiry")
    , @NamedQuery(name = "Customer.findByAddressLine1", query = "SELECT c FROM Customer c WHERE c.addressLine1 = :addressLine1")
    , @NamedQuery(name = "Customer.findByAddressLine2", query = "SELECT c FROM Customer c WHERE c.addressLine2 = :addressLine2")
    , @NamedQuery(name = "Customer.findByPostCode", query = "SELECT c FROM Customer c WHERE c.postCode = :postCode")
    , @NamedQuery(name = "Customer.findByTownCity", query = "SELECT c FROM Customer c WHERE c.townCity = :townCity")
    , @NamedQuery(name = "Customer.findByCountyState", query = "SELECT c FROM Customer c WHERE c.countyState = :countyState")
    , @NamedQuery(name = "Customer.findByCountry", query = "SELECT c FROM Customer c WHERE c.country = :country")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "MobileNumber")
    private String mobileNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "HomePhoneNumber")
    private String homePhoneNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "EmailAddress")
    private String emailAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LoginName")
    private String loginName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LoginPassword")
    private String loginPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CardType")
    private String cardType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "CardNumber")
    private String cardNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CardExpiry")
    @Temporal(TemporalType.DATE)
    private Date cardExpiry;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "AddressLine1")
    private String addressLine1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "AddressLine2")
    private String addressLine2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "PostCode")
    private String postCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TownCity")
    private String townCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CountyState")
    private String countyState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Country")
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Booking> bookingCollection;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String title, String firstName, String surname, String mobileNumber, String homePhoneNumber, String emailAddress, String loginName, String loginPassword, String cardType, String cardNumber, Date cardExpiry, String addressLine1, String addressLine2, String postCode, String townCity, String countyState, String country) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.emailAddress = emailAddress;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.postCode = postCode;
        this.townCity = townCity;
        this.countyState = countyState;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(Date cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getCountyState() {
        return countyState;
    }

    public void setCountyState(String countyState) {
        this.countyState = countyState;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Customer[ id=" + id + " ]";
    }
    
}
