<%-- 
    Document   : message
    Created on : 11-Jul-2017, 12:21:39
    Author     : james chalmers Open University F6418079
    Project    : Alba Airways application AlbaAirways-MakeBooking
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Info: ${msg}</h3>
        <table>
            <tr>
                <!-- COMMENT HTML WITH THIS -->
                <td><p>Back to previous page</p></td>
                <td><input type="button" value="back" onclick="location.href = document.referrer; return false;" style="width:75px" /></td>
            </tr>
            <td>
            <tr>
                <!-- COMMENT HTML WITH THIS -->
                <td><form name="Seats" action="SeatingServlet" method="POST"> <p>Seating Layout</p>
                    <input type="submit" value="seats" name="submit" style="width:100px" /></form></td>
            </tr>
        </table>
    </body>
</html>
