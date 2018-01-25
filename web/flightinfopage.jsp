<%-- 
    Document   : flightinfopage
    Created on : 29-Jun-2017, 15:18:11
    Alba Airways application AlbaAirways-MakeBooking
    @author james chalmers F6418079
    @version 1.0
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entities.Flight" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="outer">
            <div class="header"><h1>Alba Airways</h1></div>
            <div class="box"><h2>Flight Info page</h2>


                <% Flight flight = (Flight) request.getAttribute("Flight");%>
                <table cellspacing="8" border="0">
                    <tr>
                        <td align="left"><p>Flight Id:</p></td>
                        <td><p><%= flight.getId()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Leave Date Time:</p></td>
                        <td><p><%= flight.getDepartureDateTime()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Arrive Date Time:</p></td>
                        <td><p><%= flight.getArrivalDateTime()%></p></td>
                    </tr>
                    <tr>
                        <td align="left"><p>Route ID:</p></td>
                        <td><p><%= flight.getRouteId()%></p></td>
                    </tr>
                    <tr>
                        <td>
                            <form action="BookingCartServlet">

                                <input type="submit" value="back to main page" name="submit" />
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
    </body>
</html>
