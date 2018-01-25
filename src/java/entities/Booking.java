/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author james
 */
@Entity
@Table(name = "booking")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b")
    , @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id")
    , @NamedQuery(name = "Booking.findByNoOfAdults", query = "SELECT b FROM Booking b WHERE b.noOfAdults = :noOfAdults")
    , @NamedQuery(name = "Booking.findByNoOfChildren", query = "SELECT b FROM Booking b WHERE b.noOfChildren = :noOfChildren")
    , @NamedQuery(name = "Booking.findByNoOfInfants", query = "SELECT b FROM Booking b WHERE b.noOfInfants = :noOfInfants")
    , @NamedQuery(name = "Booking.findByAmount", query = "SELECT b FROM Booking b WHERE b.amount = :amount")
    , @NamedQuery(name = "Booking.findByConfirmationNo", query = "SELECT b FROM Booking b WHERE b.confirmationNo = :confirmationNo")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfAdults")
    private int noOfAdults;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfChildren")
    private int noOfChildren;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NoOfInfants")
    private int noOfInfants;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount")
    private BigDecimal amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ConfirmationNo")
    private int confirmationNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
    private Collection<Seat> seatCollection;
    @JoinColumn(name = "Customer_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "OutboundFlight_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Flight outboundFlightId;
    @JoinColumn(name = "InboundFlight_Id", referencedColumnName = "Id")
    @ManyToOne
    private Flight inboundFlightId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingId")
    private Collection<Passenger> passengerCollection;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Booking(Integer id, int noOfAdults, int noOfChildren, int noOfInfants, BigDecimal amount, int confirmationNo) {
        this.id = id;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.noOfInfants = noOfInfants;
        this.amount = amount;
        this.confirmationNo = confirmationNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public int getNoOfInfants() {
        return noOfInfants;
    }

    public void setNoOfInfants(int noOfInfants) {
        this.noOfInfants = noOfInfants;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(int confirmationNo) {
        this.confirmationNo = confirmationNo;
    }

    public Collection<Seat> getSeatCollection() {
        return seatCollection;
    }

    public void setSeatCollection(Collection<Seat> seatCollection) {
        this.seatCollection = seatCollection;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Flight getOutboundFlightId() {
        return outboundFlightId;
    }

    public void setOutboundFlightId(Flight outboundFlightId) {
        this.outboundFlightId = outboundFlightId;
    }

    public Flight getInboundFlightId() {
        return inboundFlightId;
    }

    public void setInboundFlightId(Flight inboundFlightId) {
        this.inboundFlightId = inboundFlightId;
    }

    public Collection<Passenger> getPassengerCollection() {
        return passengerCollection;
    }

    public void setPassengerCollection(Collection<Passenger> passengerCollection) {
        this.passengerCollection = passengerCollection;
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
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Booking[ id=" + id + " ]";
    }
    
}
