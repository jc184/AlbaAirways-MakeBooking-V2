/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author james
 */
@Embeddable
public class SeatPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "SeatNo")
    private Integer seatNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Flight_Id")
    private int flightId;

    public SeatPK() {
    }

    public SeatPK(Integer seatNo, int flightId) {
        this.seatNo = seatNo;
        this.flightId = flightId;
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) seatNo;
        hash += (int) flightId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeatPK)) {
            return false;
        }
        SeatPK other = (SeatPK) object;
        if (this.seatNo != other.seatNo) {
            return false;
        }
        if (this.flightId != other.flightId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SeatPK[ seatNo=" + seatNo + ", flightId=" + flightId + " ]";
    }
    
}
