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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author james
 */
@Entity
@Table(name = "flight")
@NamedQueries({
    @NamedQuery(name = "Flight.findAll", query = "SELECT f FROM Flight f")
    , @NamedQuery(name = "Flight.findById", query = "SELECT f FROM Flight f WHERE f.id = :id")
    , @NamedQuery(name = "Flight.findByDepartureDateTime", query = "SELECT f FROM Flight f WHERE f.departureDateTime = :departureDateTime")
    , @NamedQuery(name = "Flight.findByArrivalDateTime", query = "SELECT f FROM Flight f WHERE f.arrivalDateTime = :arrivalDateTime")
    , @NamedQuery(name = "Flight.findByFlightDate", query = "SELECT f FROM Flight f WHERE f.flightDate = :flightDate")})
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DepartureDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ArrivalDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FlightDate")
    @Temporal(TemporalType.DATE)
    private Date flightDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private Collection<Seat> seatCollection;
    @JoinColumn(name = "Route_Id", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private Route routeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "outboundFlightId")
    private Collection<Booking> bookingCollection;
    @OneToMany(mappedBy = "inboundFlightId")
    private Collection<Booking> bookingCollection1;

    public Flight() {
    }

    public Flight(Integer id) {
        this.id = id;
    }

    public Flight(Integer id, Date departureDateTime, Date arrivalDateTime, Date flightDate) {
        this.id = id;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.flightDate = flightDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(Date arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Collection<Seat> getSeatCollection() {
        return seatCollection;
    }

    public void setSeatCollection(Collection<Seat> seatCollection) {
        this.seatCollection = seatCollection;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    public Collection<Booking> getBookingCollection1() {
        return bookingCollection1;
    }

    public void setBookingCollection1(Collection<Booking> bookingCollection1) {
        this.bookingCollection1 = bookingCollection1;
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
        if (!(object instanceof Flight)) {
            return false;
        }
        Flight other = (Flight) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Flight[ id=" + id + " ]";
    }
    
}
