/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import database.FlightDB;
import javax.persistence.EntityManager;
import entities.Booking;
import entities.Flight;
import entities.Seat;
import entities.SeatPK;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author james
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SeatManager {
    
    @PersistenceContext(unitName = "AlbaAirways-MakeBookingPU3")
    private EntityManager em;
    @Resource
    private SessionContext context;

    public SeatManager() {

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Seat addSeat(SeatPK seatPK, Double seatPrice, Booking bookingId) {
        
        try {
            Seat seat = new Seat(seatPK.getSeatNo(), seatPK.getFlightId());
            Flight flight = FlightDB.selectFlight(seatPK.getFlightId());
            seat.setSeatPK(seatPK);
            seat.setSeatPrice(75.0);
            seat.setBookingId(bookingId);
            seat.setFlight(flight);
            em.persist(seat);
            em.flush();
            return seat;
        } catch (Exception e) {
            System.out.println(e);
            context.setRollbackOnly();
            return null;
        } 
    }

}
