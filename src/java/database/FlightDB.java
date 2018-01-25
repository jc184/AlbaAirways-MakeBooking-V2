/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import entities.Flight;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author james
 */
public class FlightDB {

    public static Flight selectFlight(int flightId) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT f FROM Flight f WHERE f.id = :flightId";
        TypedQuery<Flight> tq = em.createQuery(qString, Flight.class);
        tq.setParameter("flightId", flightId);
        Flight result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static Flight selectFlightById(int flightId) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Flight.class, flightId);
    }

    public static List<Flight> selectFlights() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT f from Flight f";
        TypedQuery<Flight> q = em.createQuery(qString, Flight.class);
        List<Flight> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

    public static List<Flight> selectFlightsByDate(Date flightDate) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT f from Flight f WHERE f.flightDate = :flightDate";
        TypedQuery<Flight> tq = em.createQuery(qString, Flight.class);
        tq.setParameter("flightDate", flightDate);
        List<Flight> results = null;
        try {
            results = tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

//    public static List<Flight> getFilteredFlights(Date departureDateTime) {
//        List<Flight> rtnList = new ArrayList<>();
//        for (Flight flight : FlightDB.selectFlights()) {
//            if (flight.getDepartureDateTime().equals(departureDateTime)) {
//                rtnList.add(flight);
//            }
//        }
//        return (rtnList);
//    }
}
