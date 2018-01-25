/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author james
 */
@Entity
@Table(name = "passenger")
@NamedQueries({
    @NamedQuery(name = "Passenger.findAll", query = "SELECT p FROM Passenger p")
    , @NamedQuery(name = "Passenger.findById", query = "SELECT p FROM Passenger p WHERE p.id = :id")
    , @NamedQuery(name = "Passenger.findByPassengerName", query = "SELECT p FROM Passenger p WHERE p.passengerName = :passengerName")
    , @NamedQuery(name = "Passenger.findByBaggageItemId", query = "SELECT p FROM Passenger p WHERE p.baggageItemId = :baggageItemId")
    , @NamedQuery(name = "Passenger.findByBaggageItemWeightKg", query = "SELECT p FROM Passenger p WHERE p.baggageItemWeightKg = :baggageItemWeightKg")})
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PassengerName")
    private String passengerName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BaggageItemId")
    private int baggageItemId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BaggageItemWeightKg")
    private int baggageItemWeightKg;
    @JoinColumn(name = "Booking_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Booking bookingId;
    @JoinColumns({
        @JoinColumn(name = "Seat_SeatNo", referencedColumnName = "SeatNo")
        , @JoinColumn(name = "Seat_Flight_Id", referencedColumnName = "Flight_Id")})
    @ManyToOne(optional = false)
    private Seat seat;

    public Passenger() {
    }

    public Passenger(Integer id) {
        this.id = id;
    }

    public Passenger(Integer id, String passengerName, int baggageItemId, int baggageItemWeightKg) {
        this.id = id;
        this.passengerName = passengerName;
        this.baggageItemId = baggageItemId;
        this.baggageItemWeightKg = baggageItemWeightKg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getBaggageItemId() {
        return baggageItemId;
    }

    public void setBaggageItemId(int baggageItemId) {
        this.baggageItemId = baggageItemId;
    }

    public int getBaggageItemWeightKg() {
        return baggageItemWeightKg;
    }

    public void setBaggageItemWeightKg(int baggageItemWeightKg) {
        this.baggageItemWeightKg = baggageItemWeightKg;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
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
        if (!(object instanceof Passenger)) {
            return false;
        }
        Passenger other = (Passenger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Passenger[ id=" + id + " ]";
    }
    
}
