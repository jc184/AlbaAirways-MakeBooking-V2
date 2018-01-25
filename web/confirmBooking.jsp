<%-- 
    Document   : message
    Created on : 11-Jul-2017, 12:21:39
    Author     : james chalmers Open University F6418079
    Project    : Alba Airways application AlbaAirways-MakeBooking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="enums.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Info: ${message}</h3>
        <h1>Alba Airways</h1>
        <h2>Booking Cart - Outbound</h2>
        <p>${outboundMessage}</p>
        <table border="1">
            <tr>
                <th>Passenger No</th>
                <th>Passenger Type</th>
                <th>Seat Type</th>
                <th>Seat Number</th>
                <th>Flight</th>
                <th>Amount</th>

            </tr>
            <c:forEach var="item" items="${cart.persons}" varStatus="loop">
                <tr>
                    <td><c:out value='${loop.index}'/></td>
                    <td><c:out value='${item.seatOwner}'/></td>
                    <td><select name="seatType" id="seatType" value="">
                            <option selected><c:out value='${item.seatType}'/></option>
                            <option><c:out value='ECONOMY'/></option>
                            <option><c:out value='FIRSTCLASS'/></option>
                        </select></td>
                    <td><c:out value='${item.seat.seatPK.seatNo}'/></td>
                    <td><c:out value='${sessionScope.outboundFlightId}'/></td>
                    <td><c:out value='${item.totalCurrencyFormat}'/></td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>

            </tr>
        </table>
        <br>
        <br>
        <h2>Booking Cart - Return</h2>
        <p>${returnMessage}</p>
        <table border="1">
            <tr>
                <th>Passenger No</th>
                <th>Passenger Type</th>
                <th>Seat Type</th>
                <th>Seat Number</th>
                <th>Flight</th>
                <th>Amount</th>

            </tr>
            <c:forEach var="item" items="${cart.returnPersons}" varStatus="loop">
                <tr>
                    <td><c:out value='${loop.index}'/></td>
                    <td><c:out value='${item.seatOwner}'/></td>
                    <td><select name="seatType" id="seatType" value="">
                            <option selected><c:out value='${item.seatType}'/></option>
                            <option><c:out value='ECONOMY'/></option>
                            <option><c:out value='FIRSTCLASS'/></option>
                        </select></td>
                    <td><c:out value='${item.seat.seatPK.seatNo}'/></td>
                    <td><c:out value='${sessionScope.returnFlightId}'/></td>
                    <td><c:out value='${item.totalCurrencyFormat}'/></td>

                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>

            </tr>
        </table>
        <p><b>Overall cost:</b>${sessionScope.totalCost}</p>

        <table>
            <tr>
                <!-- COMMENT HTML WITH THIS -->
                <td><form name="Confirm" action="CustomerServlet" method="POST">
                    <input type="submit" value="Make Booking" name="submit" style="width:100px" /></form></td>
            </tr>
        </table>
    </body>
</html>
