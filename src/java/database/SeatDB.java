/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import cart.BookingCart;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entities.Seat;
import entities.SeatPK;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author james
 */
public class SeatDB {

    private final int NUMBER_OF_SEATS = 24;
    private boolean[] seats = new boolean[NUMBER_OF_SEATS];
    private boolean[] existingSeatingLayoutDB = new boolean[NUMBER_OF_SEATS];
    private boolean[] reservedSeats = new boolean[NUMBER_OF_SEATS];
    private static final List<boolean[]> arrays = new ArrayList<>();
    private int firstClassCounter;//counter for first class
    private int economyCounter;//counter for economy class

    public SeatDB() {

    }

    public int getNUMBER_OF_SEATS() {
        return NUMBER_OF_SEATS;
    }

    public void setSeats(boolean[] seats) {
        this.seats = seats;
    }

    public boolean[] getSeats() {
        return seats;
    }

    /* NEEDS TO BE LOOKED AT / MADE TO WORK */
    public boolean[] getUpdatedSeats(BookingCart bCart, int flightId) {
        existingSeatingLayoutDB = getExistingSeatsDB(flightId);
        reservedSeats = getReservedSeats(bCart);
        try {
            arrays.add(existingSeatingLayoutDB);
            arrays.add(reservedSeats);
            seats = addBooleanArrays(arrays);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(existingSeatingLayoutDB));//for debugging
        System.out.println(Arrays.toString(reservedSeats));//for debugging
        System.out.println(Arrays.toString(seats));//for debugging
        return seats;
    }

    public int getFirstClassCounter() {
        return firstClassCounter;
    }

    public int getEconomyCounter() {
        return economyCounter;
    }

    public boolean seatsContainsTrue() {
        for (boolean seat : seats) {
            if (seat) {
                return true;
            }
        }
        return false;
    }

    public Seat selectSeat(SeatPK seatPK) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT s FROM Seat s WHERE (s.seatNo = :seatNo) AND (s.flightId = :flightId)";
        TypedQuery<Seat> tq = em.createQuery(qString, Seat.class);
        tq.setParameter("SeatPK", seatPK);
        Seat result = null;
        try {
            result = tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return (Seat) result;
    }

    public Seat selectSeatById(SeatPK seatPK) {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        return em.find(Seat.class, seatPK);
    }

    public List<Seat> selectSeats() {
        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        String qString = "SELECT s from Seat s";
        TypedQuery<Seat> tq = em.createQuery(qString, Seat.class);
        List<Seat> results = null;
        try {
            results = tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        } finally {
            em.close();
        }
        return results;
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException cnfe) {
            System.err.println("Database driver not found: " + cnfe.getMessage());
        }

        String dbUrl = "jdbc:mysql://localhost:3306/flightbookingsystemv2";
        String dbUser = "root";
        String dbPass = "stcallans";
        Connection connection = null;

        try {
            connection = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException sqle) {
            System.err.println("Unable to connect to Database: [" + sqle.getErrorCode() + "]" + sqle.getMessage());
        }
        return (connection);
    }

    public boolean[] getExistingSeatsDB(int flightId) {
        Connection connection = getConnection();
        try (PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                "SELECT * FROM seat WHERE Flight_Id=?")) {
            get.setInt(1, flightId);
            ResultSet results = get.executeQuery();
            ArrayList<Integer> seatNumbers = new ArrayList<>();
            while (results.next()) {
                seatNumbers.add(results.getInt(1));
            }
            for (Integer seatNumber : seatNumbers) {
                existingSeatingLayoutDB[seatNumber] = true;
            }
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());
        }
        return existingSeatingLayoutDB;
    }

    /* NEEDS TO BE LOOKED AT / MADE TO WORK */
    public boolean[] getReservedSeats(BookingCart bCart) {

        if (bCart.equals(bCart.getPersons())) {

            List<Integer> outboundSeatNumbers = getOutboundBookedSeats(bCart);
            if (bCart.getPersons().size() > 0) {//not needed??
                boolean[] outboundReservedSeats = new boolean[NUMBER_OF_SEATS];
                for (Integer outboundSeatNumber : outboundSeatNumbers) {
                    outboundReservedSeats[outboundSeatNumber] = true;
                }
                reservedSeats = outboundReservedSeats;
            }
        } else if (bCart.equals(bCart.getReturnPersons())) {

            List<Integer> returnSeatNumbers = getReturnBookedSeats(bCart);
            if (bCart.getReturnPersons().size() > 0) {//not needed??
                boolean[] returnReservedSeats = new boolean[NUMBER_OF_SEATS];
                for (Integer returnSeatNumber : returnSeatNumbers) {
                    returnReservedSeats[returnSeatNumber] = true;
                }
                reservedSeats = returnReservedSeats;
            }
        }
        return reservedSeats;
    }

    public List<Integer> getOutboundBookedSeats(BookingCart bCart) {
        List<Integer> outboundSeatNumbers = new ArrayList<>();
        for (int i = 0; i < bCart.getPersons().size(); i++) {
            if (bCart.getPersons().get(i).getSeat().getSeatPK().getSeatNo() != null) {
                int outboundSeatNumber = bCart.getPersons().get(i).getSeat().getSeatPK().getSeatNo();
                outboundSeatNumbers.add(outboundSeatNumber);
            }
        }
        return outboundSeatNumbers;
    }

    public List<Integer> getReturnBookedSeats(BookingCart bCart) {
        List<Integer> returnSeatNumbers = new ArrayList<>();
        for (int i = 0; i < bCart.getReturnPersons().size(); i++) {
            if (bCart.getReturnPersons().get(i).getSeat().getSeatPK().getSeatNo() != null) {
                int returnSeatNumber = bCart.getReturnPersons().get(i).getSeat().getSeatPK().getSeatNo();
                returnSeatNumbers.add(returnSeatNumber);
            }
        }
        return returnSeatNumbers;
    }

    public static boolean[] addBooleanArrays(List<boolean[]> arrays) {
        boolean[] result = new boolean[24];
        for (int i = 0; i < 24; i++) {
            for (boolean[] b : arrays) {
                if (b[i] == true) {
                    result[i] = true;
                }
            }
        }
        return result;
    }

    public int getRemainingSeats(int flightId) {
        boolean[] seatingLayout = new boolean[NUMBER_OF_SEATS];
        Connection connection = getConnection();
        int remainingSeats = 0;
        try (PreparedStatement get = (PreparedStatement) connection.prepareStatement(
                "SELECT * FROM seat WHERE Flight_Id=?")) {
            get.setInt(1, flightId);
            ResultSet results = get.executeQuery();
            ArrayList<Integer> seatNumbers = new ArrayList<>();
            while (results.next()) {
                seatNumbers.add(results.getInt(1));
            }
            for (Integer seatNumber : seatNumbers) {
                seatingLayout[seatNumber] = true;
            }
            remainingSeats = 24 - seatNumbers.size();
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("Unable to get records: [" + sqle.getErrorCode() + "] " + sqle.getMessage());

        }
        return remainingSeats;
    }

}
