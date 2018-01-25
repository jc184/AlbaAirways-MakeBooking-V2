/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.BookingCart;
import database.BookingDB;
import database.CustomerDB;
import entities.Booking;
import entities.Customer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookingManager;
import validation.Validator;

/**
 *
 * @author james
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
@ServletSecurity(
        @HttpConstraint(transportGuarantee = TransportGuarantee.CONFIDENTIAL)
)
public class CustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private BookingManager bookingManager;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "/checkoutpage.jsp";
        String email = request.getParameter("email1");
        String password = request.getParameter("password1");
        String submit = request.getParameter("submit");
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        if (submit != null && submit.length() > 0) {

            if (submit.equals("login")) {
                if (CustomerDB.checkCustomerExists(email, password) == true) {
                    Customer customer = CustomerDB.selectCustomer(email, password);
                    session.setAttribute("customer", customer);
                    BookingCart bCart = (BookingCart) session.getAttribute("cart");

                    int bookingId = bookingManager.makeBookingByLogin(customer, bCart);
                    if (bookingId != 0) {
                        Booking booking = BookingDB.selectBooking(bookingId);
                        int confirmationNo = booking.getConfirmationNo();
                        request.setAttribute("confirmationNo", confirmationNo);
                        String message = "You have successfully booked flights with Alba Airways. Your Booking Confirmation number is noted below.";
                        request.setAttribute("message", message);
                        url = "/paypalJS.jsp";
                        // empty cart
                        bCart = null;
                        // end session
//                        session.invalidate();
                    } else {
                        String message = "Your Booking transaction did not complete successfully.";
                        request.setAttribute("message", message);
                        url = "/albavalidation2.jsp";
                    }

                } else {
                    String message = "Your login details are wrong. Please re-enter or register to continue.";
                    request.setAttribute("message", message);
                    url = "/checkoutpage.jsp";
                }
            } else if (submit.equals("continue")) {
                try {
                    String title = request.getParameter("title");
                    String firstName = request.getParameter("firstName");
                    String surname = request.getParameter("surname");
                    String address1 = request.getParameter("addressLine1");
                    String address2 = request.getParameter("addressLine2");
                    String city = request.getParameter("city");
                    String postCode = request.getParameter("postCode");
                    String countyState = request.getParameter("countyState");
                    String country = request.getParameter("country");
                    String mobileNumber = request.getParameter("mobileNumber");
                    String homePhoneNumber = request.getParameter("homePhoneNumber");
                    String ccexpirydate = request.getParameter("ccExpiry");
                    String creditcardnumber = request.getParameter("ccNumber");
                    String creditcardtype = request.getParameter("ccType");
                    String loginName = request.getParameter("loginName");
                    String email2 = request.getParameter("email2");
                    String password2 = request.getParameter("password2");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    Date sdfccExpiryDate = formatter.parse(ccexpirydate);

                    Customer customer = (Customer) session.getAttribute("customer");
                    if (customer == null) {
                        customer = new Customer();
                    }
                    customer.setTitle(title);
                    customer.setFirstName(firstName);
                    customer.setSurname(surname);
                    customer.setAddressLine1(address1);
                    customer.setAddressLine2(address2);
                    customer.setTownCity(city);
                    customer.setPostCode(postCode);
                    customer.setCountyState(countyState);
                    customer.setCountry(country);
                    customer.setMobileNumber(mobileNumber);
                    customer.setHomePhoneNumber(homePhoneNumber);
                    customer.setCardExpiry(sdfccExpiryDate);
                    customer.setCardNumber(creditcardnumber);
                    customer.setCardType(creditcardtype);
                    customer.setLoginName(loginName);
                    customer.setEmailAddress(email2);
                    customer.setLoginPassword(password2);
                    if (CustomerDB.checkCustomerExists(email2, password2) == true) {
                        //CustomerDB.updateCustomer(customer);
                        String message = "You have already registered with those details. Please login with your username and password.";
                        request.setAttribute("message", message);

                        url = "/checkoutpage.jsp";
                    } else {

                        session.setAttribute("customer", customer);
                        BookingCart bCart = (BookingCart) session.getAttribute("cart");

                        //session.setAttribute("cart", cart);
//                        double overallTotal = (double) session.getAttribute("overallTotal");
//                        String totalSum = bCart.getOverallTotalCurrencyFormat(overallTotal);
//                        request.setAttribute("overallTotal", overallTotal);
//                        request.setAttribute("totalSum", totalSum);
//                        session.setAttribute("overallTotal", overallTotal);
//                        String message = "You have been added to our database.";
//                        request.setAttribute("message", message);
                        url = "/confirmBooking.jsp";
//                        if (authorized) {
//                            sendEmailConfirmation(request, response, customer.getEmailaddress());
//                        }
                    }
                    session.setAttribute("customer", customer);

                    BookingCart bCart = (BookingCart) session.getAttribute("cart");
                    boolean validationErrorFlag = false;
                    validationErrorFlag = validator.validateForm(firstName, surname, mobileNumber, homePhoneNumber, email2, loginName, password2, creditcardtype, creditcardnumber, address1, postCode, city, countyState, country, request);

                    // if validation error found, return user to checkout
                    if (validationErrorFlag == true) {
                        request.setAttribute("validationErrorFlag", validationErrorFlag);
                        String message = "There were errors in your form submission. Please re-enter your details.";
                        request.setAttribute("message", message);
                        url = "/checkoutpage.jsp";
                    } else {
                        int bookingId = bookingManager.makeBooking(title, firstName, surname, mobileNumber, homePhoneNumber, email2, loginName, password2, creditcardtype, creditcardnumber, sdfccExpiryDate, address1, address2, postCode, city, countyState, country, bCart);
                        if (bookingId != 0) {
                            Booking booking = BookingDB.selectBooking(bookingId);
                            int confirmationNo = booking.getConfirmationNo();
                            request.setAttribute("confirmationNo", confirmationNo);
                            String message = "You have successfully booked flights with Alba Airways. Your Booking Confirmation number is noted below.";
                            request.setAttribute("message", message);

                            url = "/paypalJS.jsp";
                            // empty cart
                            bCart = null;
                            // end session
//                            session.invalidate();
                        } else {
                            String message = "Your Booking transaction did not complete successfully.";
                            request.setAttribute("message", message);
                            url = "/albavalidation2.jsp";
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (submit.equals("Confirm Booking")) {
                String message = "To confirm your booking you must first login or register as a Customer";
                request.setAttribute("message", message);
                url = "/checkoutpage.jsp";
            }
        }
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
