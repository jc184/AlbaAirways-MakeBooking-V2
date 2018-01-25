<%-- 
    Document   : chooseeconomy
    Created on : 29-Jul-2017, 11:51:48
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Info: All the First Class seats have been booked. Would you like to choose
        an Economy Seat instead?</h3>
        <table>
            <tr>
                <!-- COMMENT HTML WITH THIS -->
                <td><p>Back to previous page</p></td>
                <td><input type="button" value="back" onclick="location.href = document.referrer; return false;" style="width:75px" /></td>
            </tr>
            <td>
            <tr>
                <!-- COMMENT HTML WITH THIS -->
                <td><form name="Seats" action="SeatingServlet" method="POST"> <p>Seating Layout</p></td>
                <td><input type="submit" value="seats" name="submit" style="width:75px" /></td></form>
            </tr>
        </table>
    </body>
</html>
