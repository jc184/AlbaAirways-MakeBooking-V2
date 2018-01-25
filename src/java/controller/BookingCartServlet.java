package controller;

import cart.BookingCart;
import cart.BookingCartItem;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Flight;
import database.FlightDB;
import database.SeatDB;
import entities.Seat;
import entities.SeatPK;
import enums.PassengerEnum;
import enums.SeatTypeEnum;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * Alba Airways application AlbaAirways-MakeBooking
 *
 *
 * @author james chalmers Open University F6418079
 * @version 1.0
 */
@WebServlet(name = "BookingCartServlet", urlPatterns = {"/BookingCartServlet"})
public class BookingCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    FlightDB flightDB;
    SeatDB seatDB;

    @Override
    public void init() throws ServletException {
        flightDB = new FlightDB();
        seatDB = new SeatDB();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException if a SDF Parse error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        String url = "";
        Date sdfFlightDate = null;
        Date sdfOutboundFlightDate = null;
        Date sdfReturnFlightDate = null;
        HttpSession session = request.getSession();

        String submit = request.getParameter("submit");
        if (submit != null && submit.length() > 0) {

            BookingCart cart = (BookingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new BookingCart();
            }

            if (submit.equals("show cart")) {
                int noOfAdults = Integer.parseInt(request.getParameter("noOfAdults"));
                int noOfChildren = Integer.parseInt(request.getParameter("noOfChildren"));
                int noOfInfants = Integer.parseInt(request.getParameter("noOfInfants"));
                String regex = "^[1-9]\\d*$";

                String outboundFlightIdString = request.getParameter("outboundFlightId");
                String returnFlightIdString = request.getParameter("returnFlightId");

                if (!outboundFlightIdString.matches(regex) || !returnFlightIdString.matches(regex)) {
                    String msg = "You must enter numerical values for your Flight choices";
                    request.setAttribute("msg", msg);
                    session.setAttribute("msg", msg);
                    url = "/makeBooking.jsp";
                } else {
                    List<Integer> personList = new ArrayList<>();
                    personList.add(noOfAdults);
                    personList.add(noOfChildren);
                    personList.add(noOfInfants);

                    int personTotal = noOfAdults + noOfChildren + noOfInfants;

                    int outboundFlightId = Integer.parseInt(request.getParameter("outboundFlightId"));
                    Flight flight = FlightDB.selectFlight(outboundFlightId);
                    request.setAttribute("Flight", flight);

                    if (outboundFlightId == flight.getId()) {
                        int flightId = flight.getId();
                        flightId = outboundFlightId;
                    }
                    request.setAttribute("outboundFlightId", outboundFlightId);
                    session.setAttribute("outboundFlightId", outboundFlightId);

                    int returnFlightId = Integer.parseInt(request.getParameter("returnFlightId"));
                    flight = FlightDB.selectFlight(returnFlightId);
                    request.setAttribute("Flight", flight);

                    if (returnFlightId == flight.getId()) {
                        int flightId = flight.getId();
                        flightId = returnFlightId;
                    }
                    request.setAttribute("returnFlightId", returnFlightId);
                    session.setAttribute("returnFlightId", returnFlightId);

//                    request.setAttribute("noOfAdults", noOfAdults);
//                    request.setAttribute("noOfChildren", noOfChildren);
//                    request.setAttribute("noOfInfants", noOfInfants);
//                    
//                    session.setAttribute("noOfAdults", noOfAdults);
//                    session.setAttribute("noOfChildren", noOfChildren);
//                    session.setAttribute("noOfInfants", noOfInfants);
                    List<BookingCartItem> persons = new ArrayList<>();
                    List<BookingCartItem> returnPersons = new ArrayList<>();

                    if (personList.get(0) > 0) {
                        for (int j = 0; j < noOfAdults; j++) {
                            SeatPK seatPK = new SeatPK(null, 0);
                            Seat seat = new Seat(seatPK);
                            persons.add(new BookingCartItem(seat, PassengerEnum.ADULT, SeatTypeEnum.UNSPECIFIED));
                            if (returnFlightId != 0) {
                                SeatPK returnSeatPK = new SeatPK(null, 0);
                                Seat returnSeat = new Seat(returnSeatPK);
                                returnPersons.add(new BookingCartItem(returnSeat, PassengerEnum.ADULT, SeatTypeEnum.UNSPECIFIED));
                            }
                        }
                    }
                    if (personList.get(1) > 0) {
                        for (int k = 0; k < noOfChildren; k++) {
                            SeatPK seatPK = new SeatPK(null, 0);
                            Seat seat = new Seat(seatPK);
                            persons.add(new BookingCartItem(seat, PassengerEnum.CHILD, SeatTypeEnum.UNSPECIFIED));
                            if (returnFlightId != 0) {
                                SeatPK returnSeatPK = new SeatPK(null, 0);
                                Seat returnSeat = new Seat(returnSeatPK);
                                returnPersons.add(new BookingCartItem(returnSeat, PassengerEnum.CHILD, SeatTypeEnum.UNSPECIFIED));
                            }
                        }
                    }
                    if (personList.get(2) > 0) {
                        for (int l = 0; l < noOfInfants; l++) {
                            SeatPK seatPK = new SeatPK(null, 0);
                            Seat seat = new Seat(seatPK);
                            persons.add(new BookingCartItem(seat, PassengerEnum.INFANT, SeatTypeEnum.UNSPECIFIED));
                            if (returnFlightId != 0) {
                                SeatPK returnSeatPK = new SeatPK(null, 0);
                                Seat returnSeat = new Seat(returnSeatPK);
                                returnPersons.add(new BookingCartItem(returnSeat, PassengerEnum.INFANT, SeatTypeEnum.UNSPECIFIED));
                            }
                        }
                    }
                    cart.setPersons(persons);
                    cart.setReturnPersons(returnPersons);

                    Double total = cart.getBookingAmount();
                    session.setAttribute("total", total);
                    String totalCost = cart.getOverallTotalCurrencyFormat(total);
                    session.setAttribute("totalCost", totalCost);

                    if (cart.getPersons().isEmpty()) {
                        String message = "Your cart is empty";
                        request.setAttribute("message", message);
                        url = "/cart.jsp";
                    }
                    int vacantSeatsOutbound = seatDB.getRemainingSeats(outboundFlightId);
                    int vacantSeatsReturn = seatDB.getRemainingSeats(returnFlightId);
                    String routeOutbound = FlightDB.selectFlight(outboundFlightId).getRouteId().getRouteName();
                    String routeReturn = FlightDB.selectFlight(returnFlightId).getRouteId().getRouteName();
                    String routeOutboundDate = FlightDB.selectFlight(outboundFlightId).getDepartureDateTime().toString();
                    String routeReturnDate = FlightDB.selectFlight(returnFlightId).getDepartureDateTime().toString();
                    String outboundMessage = routeOutbound + ", " + routeOutboundDate + ": There are " + vacantSeatsOutbound + " seats left on Flight " + outboundFlightId;
                    request.setAttribute("outboundMessage", outboundMessage);
                    session.setAttribute("outboundMessage", outboundMessage);
                    String returnMessage = routeReturn + ", " + routeReturnDate + ": There are " + vacantSeatsReturn + " seats left on Flight " + returnFlightId;
                    request.setAttribute("returnMessage", returnMessage);
                    session.setAttribute("returnMessage", returnMessage);
                    session.setAttribute("cart", cart);

                    url = "/cart.jsp";

                }
            } else if (submit.equals("back to cart")) {
                int seatNumber = (int) session.getAttribute("seatNumber");
                int flightId = (int) session.getAttribute("flightId");

                List<BookingCartItem> persons = cart.getPersons();
                List<BookingCartItem> returnPersons = cart.getReturnPersons();

                if (flightId == (int) session.getAttribute("outboundFlightId")) {
                    Flight outboundFlight = new Flight(flightId);
                    for (BookingCartItem outboundBcItem : persons) {
                        int passengerNo = persons.indexOf(outboundBcItem);
                        if (passengerNo == (int) session.getAttribute("passengerNo")) {
                            SeatPK seatPK = outboundBcItem.getSeat().getSeatPK();
                            seatPK.setSeatNo(seatNumber);
                            seatPK.setFlightId(flightId);

                            Seat seat = new Seat(seatPK);
                            outboundBcItem.setSeat(seat);
                            outboundBcItem.getSeat().setFlight(outboundFlight);
                            outboundBcItem.getSeat().getFlight().setId(flightId);
                            if (seatNumber < 12) {
                                outboundBcItem.setSeatType(SeatTypeEnum.FIRSTCLASS);
                            } else {
                                outboundBcItem.setSeatType(SeatTypeEnum.ECONOMY);
                            }
                            double seatPrice = 75.0;
                            seat.setSeatPrice(seatPrice);

                        }
                    }

                } else if (flightId == (int) session.getAttribute("returnFlightId")) {
                    Flight returnFlight = new Flight(flightId);
                    for (BookingCartItem returnBcItem : returnPersons) {
                        int passengerNo = returnPersons.indexOf(returnBcItem);
                        if (passengerNo == (int) session.getAttribute("returnPassengerNo")) {
                            SeatPK returnSeatPK = returnBcItem.getSeat().getSeatPK();
                            returnSeatPK.setSeatNo(seatNumber);
                            returnSeatPK.setFlightId(flightId);

                            Seat returnSeat = new Seat(returnSeatPK);
                            returnBcItem.setSeat(returnSeat);
                            returnBcItem.getSeat().setFlight(returnFlight);
                            returnBcItem.getSeat().getFlight().setId(flightId);
                            if (seatNumber < 12) {
                                returnBcItem.setSeatType(SeatTypeEnum.FIRSTCLASS);
                            } else {
                                returnBcItem.setSeatType(SeatTypeEnum.ECONOMY);
                            }
                            double seatPrice = 75.0;
                            returnSeat.setSeatPrice(seatPrice);

                        }
                    }
                }
                Double total = cart.getBookingAmount();
                session.setAttribute("total", total);
                String totalCost = cart.getOverallTotalCurrencyFormat(total);
                session.setAttribute("totalCost", totalCost);
                int outboundFlightId = (int) session.getAttribute("outboundFlightId");
                int returnFlightId = (int) session.getAttribute("returnFlightId");
                int vacantSeatsOutbound = seatDB.getRemainingSeats(outboundFlightId);
                int vacantSeatsReturn = seatDB.getRemainingSeats(returnFlightId);
                String routeOutbound = FlightDB.selectFlight(outboundFlightId).getRouteId().getRouteName();
                String routeReturn = FlightDB.selectFlight(returnFlightId).getRouteId().getRouteName();
                String routeOutboundDate = FlightDB.selectFlight(outboundFlightId).getDepartureDateTime().toString();
                String routeReturnDate = FlightDB.selectFlight(returnFlightId).getDepartureDateTime().toString();
                String outboundMessage = routeOutbound + " ," + routeOutboundDate + ": There are " + vacantSeatsOutbound + " seats left on Flight " + outboundFlightId;
                request.setAttribute("outboundMessage", outboundMessage);
                session.setAttribute("outboundMessage", outboundMessage);
                String returnMessage = routeReturn + " ," + routeReturnDate + ": There are " + vacantSeatsReturn + " seats left on Flight " + returnFlightId;

                request.setAttribute("returnMessage", returnMessage);
                session.setAttribute("returnMessage", returnMessage);

                session.setAttribute("cart", cart);
                url = "/cart.jsp";

            } else if (submit.equals("show flights")) {
                String flightDate = request.getParameter("FlightDate");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                sdfFlightDate = formatter.parse(flightDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(sdfFlightDate);
                cal.getTime();

                request.setAttribute("flightStore", FlightDB.selectFlightsByDate(sdfFlightDate));
                request.setAttribute("sdfFlightDate", sdfFlightDate);
                request.setAttribute("cal", formatter.format(cal.getTime()));
                url = "/flightpage.jsp";

            } else if (submit.equals("select outbound")) {
                int outboundFlightId = Integer.parseInt(request.getParameter("outboundFlightId"));
                Flight flight = FlightDB.selectFlight(outboundFlightId);
                request.setAttribute("Flight", flight);

                if (Integer.parseInt(request.getParameter("outboundFlightId")) == flight.getId()) {
                    int flightId = flight.getId();
                    flightId = outboundFlightId;
                    request.setAttribute("outboundFlightId", outboundFlightId);
                    session.setAttribute("outboundFlightId", outboundFlightId);
                }
                url = "/makeBooking.jsp";

            } else if (submit.equals("select return")) {
                int returnFlightId = Integer.parseInt(request.getParameter("returnFlightId"));
                Flight flight = FlightDB.selectFlight(returnFlightId);
                request.setAttribute("Flight", flight);

                if (Integer.parseInt(request.getParameter("returnFlightId")) == flight.getId()) {
                    int flightId = flight.getId();
                    flightId = returnFlightId;
                    request.setAttribute("returnFlightId", returnFlightId);
                    session.setAttribute("returnFlightId", returnFlightId);
                }
                url = "/makeBooking.jsp";

            } else if (submit.equals("back to main page")) {
                url = "/makeBooking.jsp";

            } else if (submit.equals("outbound flights")) {
                try {
                    String outboundFlightDate = request.getParameter("outboundFlightDate");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                    sdfOutboundFlightDate = formatter.parse(outboundFlightDate);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdfOutboundFlightDate);
                    cal.getTime();
                    Date today = new Date();
                    Date nextMonth = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 30));
                    if (outboundFlightDate.isEmpty()) {
                        String msg = "You must enter a valid date for your outbound flight.";
                        request.setAttribute("msg", msg);

                        url = "/albavalidation.jsp";
                    } else if (sdfOutboundFlightDate.before(nextMonth)) {
                        String msg = "Click on the Back button in your browser and re-enter your details. You must book at least one month in advance.";
                        request.setAttribute("msg", msg);
                        url = "/albavalidation.jsp";
                    }

                    request.setAttribute("FlightStore", FlightDB.selectFlightsByDate(sdfOutboundFlightDate));

                    request.setAttribute("sdfOutboundFlightDate", sdfOutboundFlightDate);
                    request.setAttribute("cal", formatter.format(cal.getTime()));
                } catch (ParseException ex) {
                    Logger.getLogger(BookingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
                url = "/flightpage.jsp";
            } else if (submit.equals("return flights")) {

                try {
                    String returnFlightDate = request.getParameter("returnFlightDate");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    sdfReturnFlightDate = formatter.parse(returnFlightDate);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(sdfReturnFlightDate);
                    cal.getTime();
                    Date today = new Date();
                    Date nextMonth = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 30));
                    if (returnFlightDate.isEmpty()) {
                        String msg = "Click on the Back button in your browser and re-enter your details.";
                        request.setAttribute("msg", msg);
                        url = "/albavalidation.jsp";
                    } else if (sdfReturnFlightDate.before(nextMonth)) {
                        String msg = "Click on the Back button in your browser and re-enter your details. You must book at least one month in advance.";
                        request.setAttribute("msg", msg);
                        url = "/albavalidation.jsp";
                    }

                    request.setAttribute("FlightStore", FlightDB.selectFlightsByDate(sdfReturnFlightDate));

                    request.setAttribute("sdfReturnFlightDate", sdfReturnFlightDate);
                    request.setAttribute("cal", formatter.format(cal.getTime()));
                } catch (ParseException ex) {
                    Logger.getLogger(BookingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }

                url = "/flightpage.jsp";
            } else if (submit.equals("Choose Seat")) {
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                int passengerNo = Integer.parseInt(request.getParameter("passengerNo"));
                request.setAttribute("flightId", flightId); // add to request
                request.getSession().setAttribute("flightId", flightId); // add to session
                request.setAttribute("passengerNo", passengerNo); // add to request
                request.getSession().setAttribute("passengerNo", passengerNo); // add to session
                this.getServletConfig().getServletContext().setAttribute("flightId", flightId); // add to application context
                this.getServletConfig().getServletContext().setAttribute("passengerNo", passengerNo); // add to application context
                request.getRequestDispatcher("/SeatingServlet").forward(request, response);
                return;
            } else if (submit.equals("Choose Return Seat")) {
                int flightId = Integer.parseInt(request.getParameter("flightId"));
                int returnPassengerNo = Integer.parseInt(request.getParameter("returnPassengerNo"));
                request.setAttribute("flightId", flightId); // add to request
                request.getSession().setAttribute("flightId", flightId); // add to session
                request.setAttribute("returnPassengerNo", returnPassengerNo); // add to request
                request.getSession().setAttribute("returnPassengerNo", returnPassengerNo); // add to session
                this.getServletConfig().getServletContext().setAttribute("flightId", flightId); // add to application context
                this.getServletConfig().getServletContext().setAttribute("returnPassengerNo", returnPassengerNo); // add to application context
                request.getRequestDispatcher("/SeatingServlet").forward(request, response);
                return;
            }
        }
        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(BookingCartServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(BookingCartServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
