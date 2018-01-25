/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author james
 */
@Entity
@Table(name = "route")
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r")
    , @NamedQuery(name = "Route.findById", query = "SELECT r FROM Route r WHERE r.id = :id")
    , @NamedQuery(name = "Route.findByRouteName", query = "SELECT r FROM Route r WHERE r.routeName = :routeName")
    , @NamedQuery(name = "Route.findByAirportFrom", query = "SELECT r FROM Route r WHERE r.airportFrom = :airportFrom")
    , @NamedQuery(name = "Route.findByAirportTo", query = "SELECT r FROM Route r WHERE r.airportTo = :airportTo")})
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "RouteName")
    private String routeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "AirportFrom")
    private String airportFrom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "AirportTo")
    private String airportTo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
    private Collection<Flight> flightCollection;

    public Route() {
    }

    public Route(Integer id) {
        this.id = id;
    }

    public Route(Integer id, String routeName, String airportFrom, String airportTo) {
        this.id = id;
        this.routeName = routeName;
        this.airportFrom = airportFrom;
        this.airportTo = airportTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getAirportFrom() {
        return airportFrom;
    }

    public void setAirportFrom(String airportFrom) {
        this.airportFrom = airportFrom;
    }

    public String getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(String airportTo) {
        this.airportTo = airportTo;
    }

    public Collection<Flight> getFlightCollection() {
        return flightCollection;
    }

    public void setFlightCollection(Collection<Flight> flightCollection) {
        this.flightCollection = flightCollection;
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
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Route[ id=" + id + " ]";
    }
    
}
