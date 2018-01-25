/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cart.BookingCart;
import entities.Seat;
import entities.SeatPK;
import enums.SeatTypeEnum;
import database.SeatDB;
import java.sql.SQLException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Alba Airways application AlbaAirways-MakeBooking
 *
 * @author james chalmers Open University F6418079
 */
@WebServlet(name = "SeatingServlet", urlPatterns = {"/SeatingServlet"})
public class SeatingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    SeatDB seatDB;
    SeatTypeEnum seatType;

    private int personCount;

    @Override
    public void init() throws ServletException {
        seatDB = new SeatDB();
        personCount = 1;
    }

    public String chooseSeat(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException, SQLException {
        String msg = "";
        String url = "/indexRevB.jsp";
        HttpSession session = request.getSession();
        int seatNumber = (int) request.getAttribute("seatNumber");

        int flightId = (int) session.getAttribute("flightId");
        BookingCart bCart = new BookingCart();
        bCart = (BookingCart) session.getAttribute("cart");
        boolean[] seatingLayout = seatDB.getUpdatedSeats(bCart, flightId);


        if (personCount == 1) {
            if (seatDB.getUpdatedSeats(bCart, flightId)[seatNumber] == false) {
                Seat seat = reserveSeat(request, response, seatNumber);

                request.setAttribute("seat", seat);
                session.setAttribute("seatNumber", seatNumber);
                msg = "Your Seat Booking:";

                request.setAttribute("seatNumber", seatNumber);
                request.setAttribute("seatType", seatType);

                request.setAttribute("msg", msg);
                url = "/message.jsp";

            } else if (seatDB.getUpdatedSeats(bCart, flightId)[seatNumber] == true) {
                msg = "This seat is already booked. Please choose another seat.";
                request.setAttribute("msg", msg);
                url = "/booked.jsp";
            }
        } else {
            msg = "You have already booked your seat for this passenger.";
            request.setAttribute("msg", msg);
            url = "/booked.jsp";
        }
        return url;
    }

    /* EXPERIMENTAL */
    public Seat reserveSeat(HttpServletRequest request, HttpServletResponse response, int seatNumber) throws SQLException {
        HttpSession session = request.getSession();
        int flightId = (int) session.getAttribute("flightId");
        BookingCart bCart = new BookingCart();
        bCart = (BookingCart) session.getAttribute("cart");
        
        SeatPK reservedSeatPK = new SeatPK();
        Seat seat = new Seat(reservedSeatPK);
        reservedSeatPK.setFlightId(flightId);
        reservedSeatPK.setSeatNo(seatNumber);
        seat.setSeatPK(reservedSeatPK);
        
        boolean[] seats = new boolean[seatDB.getNUMBER_OF_SEATS()];
        seats = seatDB.getReservedSeats(bCart);//?
        seats[seatNumber] = true;
        seatDB.setSeats(seats);
        request.setAttribute("seatNumber", seatNumber);//???
        session.setAttribute("seatNumber", seatNumber);//???
        
//        session.setAttribute("reservationLayout", seatDB.getUpdatedSeats(bCart, flightId));
        return seat;
    }

    public String allocateEconomySeat(HttpServletRequest request, HttpServletResponse response, String seatType) throws ServletException, IOException, ClassNotFoundException, SQLException {
        String msg = "";
        String url = "";
        HttpSession session = request.getSession();
        int seatNumber = 0;
        int flightId = (int) session.getAttribute("flightId");
        BookingCart bCart = new BookingCart();
        bCart = (BookingCart) session.getAttribute("cart");
        boolean[] seatingLayout = seatDB.getUpdatedSeats(bCart, flightId);

        if (personCount == 1) {

            //If there are vacant seats, randomly select one etc...
            Random random = new Random();
            int economySeat = random.nextInt(23 - 12 + 1) + 12;
            seatNumber = economySeat;
            request.setAttribute("seatNumber", seatNumber);

            if (seatDB.getUpdatedSeats(bCart, flightId)[seatNumber] == false) {

                Seat seat = reserveSeat(request, response, seatNumber);
                request.setAttribute("seat", seat);

                msg = "Your Economy Class Seat Booking:";
                request.setAttribute("seatNumber", seatNumber);
                request.setAttribute("seatType", seatType);

                request.setAttribute("msg", msg);

                url = "/message.jsp";
            } else if (seatDB.getUpdatedSeats(bCart, flightId)[seatNumber] = true) {
                msg = "This seat is already booked.";
                request.setAttribute("msg", msg);
                url = "/booked.jsp";
                return url;
            }

        } else {
            msg = "You have already booked your seat for this passenger.";
            request.setAttribute("msg", msg);
            url = "/booked.jsp";

        }

        session.setAttribute("seatNumber", seatNumber);

        return url;

    }

    public String showSeats(HttpServletRequest request, HttpServletResponse response) {
        String url = "/indexRevB.jsp";
        return url;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        int seatNumber;
        personCount = 1;
        String msg = "";
        String url = "/indexRevB.jsp";
        String submit = request.getParameter("submit");
        if (submit != null && submit.length() > 0) {

            switch (submit) {
                case "seat01F":
                    seatNumber = 0;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat02F":
                    seatNumber = 1;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat03F":
                    seatNumber = 2;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat04F":
                    seatNumber = 3;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat05F":
                    seatNumber = 4;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat06F":
                    seatNumber = 5;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat07F":
                    seatNumber = 6;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat08F":
                    seatNumber = 7;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat09F":
                    seatNumber = 8;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat10F":
                    seatNumber = 9;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat11F":
                    seatNumber = 10;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat12F":
                    seatNumber = 11;
                    request.setAttribute("seatNumber", seatNumber);
                    seatType = SeatTypeEnum.FIRSTCLASS;
                    url = chooseSeat(request, response);
                    personCount++;
                    break;
                case "seat13E":
//                    seatNumber = 12;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat14E":
//                    seatNumber = 13;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat15E":
//                    seatNumber = 14;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat16E":
//                    seatNumber = 15;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat17E":
//                    seatNumber = 16;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat18E":
//                    seatNumber = 17;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat19E":
//                    seatNumber = 18;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat20E":
//                    seatNumber = 19;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat21E":
//                    seatNumber = 20;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat22E":
//                    seatNumber = 21;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat23E":
//                    seatNumber = 22;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seat24E":
//                    seatNumber = 23;
//                    seatType = SeatTypeEnum.ECONOMY;
                    url = "/indexRevB.jsp";
                    break;
                case "seats":
                    url = showSeats(request, response);
                    break;
                case "Economy":
                    seatType = SeatTypeEnum.ECONOMY;
                    url = allocateEconomySeat(request, response, seatType.toString());
                    personCount++;
                    break;
                default:
                    break;
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

        try {
            response.setContentType("application/json;charset=utf-8");

            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            JSONObject member = new JSONObject();

            HttpSession session = request.getSession();
            int flightId = (int) session.getAttribute("flightId");
            BookingCart bCart = new BookingCart();
            bCart = (BookingCart) session.getAttribute("cart");
            //member.put("arrayData", seatDB.getSeatingLayout(flightId));
            member.put("arrayData", seatDB.getUpdatedSeats(bCart, flightId));
            array.add(member);

            json.put("jsonArray", array);

            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
        } catch (IOException e) {
            e.getMessage();
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SeatingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
