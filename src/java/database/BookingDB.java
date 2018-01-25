/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.Booking;

/**
 *
 * @author james
 */
public class BookingDB {

    public static Booking selectBooking(int bookingId) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT b FROM Booking b WHERE b.id = :bookingId";
        TypedQuery<Booking> tq = em.createQuery(qString, Booking.class);
        tq.setParameter("bookingId", bookingId);
        Booking result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return result;
    }

    public static Booking selectBookingById(int bookingId) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Booking.class, bookingId);
    }

    public static List<Booking> selectBookings() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT b from Booking b";
        TypedQuery<Booking> q = em.createQuery(qString, Booking.class);
        List<Booking> results = null;
        try {
            results = q.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

        return results;
    }
}
