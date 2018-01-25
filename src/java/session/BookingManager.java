/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import cart.BookingCart;
import cart.BookingCartItem;
import database.FlightDB;
import entities.Booking;
import entities.Customer;
import entities.Flight;
import entities.Seat;
import entities.SeatPK;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author james
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookingManager {

    @PersistenceContext(unitName = "AlbaAirways-MakeBookingPU3")
    private EntityManager em;
    @Resource
    private SessionContext context;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int makeBooking(String title, String firstName, String surname, String mobileNumber, String homePhoneNumber, String email2, String loginName, String password2, String cardType, String cardNumber, Date cardExpiry, String addressLine1, String addressLine2, String postCode, String city, String countyState, String country, BookingCart cart) {
        try {

            Customer customer = addCustomer(title, firstName, surname, mobileNumber, homePhoneNumber, email2, loginName, password2, cardType, cardNumber, cardExpiry, addressLine1, addressLine2, postCode, city, countyState, country);

            Booking booking = addBooking(customer, cart);
            addSeatBooking(booking, cart);
            return booking.getId();
        } catch (Exception e) {
            e.printStackTrace();
            context.setRollbackOnly();
            return 0;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public int makeBookingByLogin(Customer customer, BookingCart cart) {
        try {

            Booking booking = addBooking(customer, cart);
            addSeatBooking(booking, cart);
            return booking.getId();
        } catch (Exception e) {
            e.printStackTrace();
            context.setRollbackOnly();
            return 0;
        }
    }

    private Customer addCustomer(String title, String firstName, String surname, String mobileNumber, String homePhoneNumber, String email2, String loginName, String password2, String cardType, String cardNumber, Date cardExpiry, String addressLine1, String addressLine2, String postCode, String city, String countyState, String country) {

        Customer customer = new Customer();
        customer.setTitle(title);
        customer.setFirstName(firstName);
        customer.setSurname(surname);
        customer.setMobileNumber(mobileNumber);
        customer.setHomePhoneNumber(homePhoneNumber);
        customer.setEmailAddress(email2);
        customer.setLoginName(loginName);
        customer.setLoginPassword(password2);
        customer.setCardType(cardType);
        customer.setCardNumber(cardNumber);
        customer.setCardExpiry(cardExpiry);
        customer.setAddressLine1(addressLine1);
        customer.setAddressLine2(addressLine2);
        customer.setPostCode(postCode);
        customer.setTownCity(city);
        customer.setCountyState(countyState);
        customer.setCountry(country);

        em.persist(customer);
        return customer;
    }

    private Booking addBooking(Customer customer, BookingCart cart) {
        BookingCartItem outboundBcItem = new BookingCartItem();
        BookingCartItem returnBcItem = new BookingCartItem();

        int outboundFlightId = 0;
        int returnFlightId = 0;

        int noOfAdults = 0;
        int noOfChildren = 0;
        int noOfInfants = 0;

//        if (cart.equals(cart.getPersons())) {
        for (int i = 0; i < cart.getPersons().size(); i++) {
            outboundBcItem = cart.getPersons().get(i);
            switch (outboundBcItem.getSeatOwner()) {
                case ADULT:
                    noOfAdults++;
                    break;
                case CHILD:
                    noOfChildren++;
                    break;
                case INFANT:
                    noOfInfants++;
                    break;
                case UNSPECIFIED:
                    break;
                default:
                    break;
            }
        }
//        } else if (cart.equals(cart.getReturnPersons())) {

//        for (int i = 0; i < cart.getReturnPersons().size(); i++) {
//            returnBcItem = cart.getReturnPersons().get(i);
//            switch (returnBcItem.getSeatOwner()) {
//                case ADULT:
//                    noOfAdults++;
//                    break;
//                case CHILD:
//                    noOfChildren++;
//                    break;
//                case INFANT:
//                    noOfInfants++;
//                    break;
//                case UNSPECIFIED:
//                    break;
//                default:
//                    break;
//            }
//        }
//        }
        outboundFlightId = cart.getPersons().get(0).getSeat().getFlight().getId();
        returnFlightId = cart.getReturnPersons().get(0).getSeat().getFlight().getId();
        Flight outboundFlight = FlightDB.selectFlight(outboundFlightId);
        Flight returnFlight = FlightDB.selectFlight(returnFlightId);

        Booking booking = new Booking();
        booking.setCustomerId(customer);
        booking.setNoOfAdults(noOfAdults);
        booking.setNoOfChildren(noOfChildren);
        booking.setNoOfInfants(noOfInfants);
        booking.setAmount(BigDecimal.valueOf(cart.getTotal()));
        booking.setOutboundFlightId(outboundFlight);
        booking.setInboundFlightId(returnFlight);

        // create confirmation number
        Random random = new Random();
        int i = random.nextInt(999999999);
        booking.setConfirmationNo(i);

        em.persist(booking);
        return booking;
    }

    private void addSeatBooking(Booking booking, BookingCart cart) {

        List<BookingCartItem> outboundSeats = cart.getPersons();
        List<BookingCartItem> returnSeats = cart.getReturnPersons();
        // iterate through booking cart and create seats
        try {
            for (BookingCartItem outboundBcItem : outboundSeats) {
                int flightId = outboundBcItem.getSeat().getSeatPK().getFlightId();
                // set up primary key object
                SeatPK seatPK = new SeatPK();
                seatPK.setSeatNo(outboundBcItem.getSeat().getSeatPK().getSeatNo());
                seatPK.setFlightId(flightId);
                // create seat using PK object
                Seat outboundSeat = new Seat(seatPK);
                outboundSeat.setBookingId(booking);
                outboundSeat.setSeatPrice(75.0);
                em.persist(outboundSeat);
            }
        } catch (ConstraintViolationException e) {
            System.out.println(e.getConstraintViolations().toString());
        }

        try {
            for (BookingCartItem returnBcItem : returnSeats) {
                int flightId = returnBcItem.getSeat().getSeatPK().getFlightId();
                // set up primary key object
                SeatPK seatPK = new SeatPK();
                seatPK.setSeatNo(returnBcItem.getSeat().getSeatPK().getSeatNo());
                seatPK.setFlightId(flightId);
                // create seat using PK object
                Seat returnSeat = new Seat(seatPK);
                returnSeat.setBookingId(booking);
                returnSeat.setSeatPrice(75.0);
                em.persist(returnSeat);
            }
        } catch (ConstraintViolationException e) {
            System.out.println(e.getConstraintViolations().toString());
        }
        em.flush();

    }

}
