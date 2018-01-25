<%-- 
    Document   : index
    Created on : 26-Nov-2017, 20:54:43
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/includes/header_2.jsp" %>

<div class="container">
    <p style="color: red">${message}</p>
    <h3>Login to continue</h3>
    <form action="CustomerServlet" method="post">
        <div class="form-group">
            <label for="email1">Email address</label>
            <input type="email" class="form-control" id="email1" name="email1" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="password1">Login Password</label>
            <input type="password" class="form-control" id="password1" name="password1" placeholder="Enter Login Password">
        </div>
        <button type="submit" class="btn btn-primary" value="login" name="submit">Login</button>
    </form><br>



    <!-- Example row of columns   <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more &raquo;</a></p> --> 
    <h3>Or enter your name and contact information</h3>
    <form action="CustomerServlet" method="post">

        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="Enter Title">
        </div>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter First Name">
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" class="form-control" id="surname" name="surname" placeholder="Enter Last Name">
        </div>
        <div class="form-group">
            <label for="addressLine1">Address line 1</label>
            <input type="text" class="form-control" id="addressLine1" name="addressLine1" placeholder="Enter Address line 1">
        </div>
        <div class="form-group">
            <label for="addressLine2">Address line 2</label>
            <input type="text" class="form-control" id="addressLine2" name="addressLine2" placeholder="Enter Address line 2">
        </div>
        <div class="form-group">
            <label for="city">City</label>
            <input type="text" class="form-control" id="city" name="city" placeholder="Enter City">
        </div>
        <div class="form-group">
            <label for="postCode">Post Code</label>
            <input type="text" class="form-control" id="postCode" name="postCode" placeholder="Enter PostCode">
        </div>
        <div class="form-group">
            <label for="countyState">County / State</label>
            <input type="text" class="form-control" id="countyState" name="countyState" placeholder="Enter County / State">
        </div>        
        <div class="form-group">
            <label for="country">Country</label>
            <input type="text" class="form-control" id="country" name="country" placeholder="Enter Country">
        </div>
        <div class="form-group">
            <label for="mobileNumber">Mobile No</label>
            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="Enter Mobile No">
        </div>
        <div class="form-group">
            <label for="homePhoneNumber">Home Phone No</label>
            <input type="text" class="form-control" id="homePhoneNumber" name="homePhoneNumber" placeholder="Enter Home Phone No">
        </div>
        <div class="form-group">
            <label for="ccExpiry">Credit Card Expiry</label>
            <input type="text" class="form-control" id="datepicker" name="ccExpiry" placeholder="Enter Credit Card Expiry">
        </div>
        <div class="form-group">
            <label for="ccNumber">Credit Card Number</label>
            <input type="text" class="form-control" id="ccNumber" name="ccNumber" placeholder="Enter Credit Card Number">
        </div>
        <div class="form-group">
            <label for="ccType">Credit Card Type</label>
            <select class="form-control" id="ccType" name="ccType">
                <option selected>VISA</option>
                <option>MasterCard</option>
                <option>Access</option>
                <option>American Express</option>
            </select>
        </div> 
<div class="form-group">
            <label for="loginName">Login Name</label>
            <input type="text" class="form-control" id="loginName" name="loginName" placeholder="Enter Login Name">
        </div>
        <div class="form-group">
            <label for="email2">Email address</label>
            <input type="email" class="form-control" id="email2" name="email2" aria-describedby="emailHelp" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="password2">Login Password</label>
            <input type="password" class="form-control" id="password2" name="password2" placeholder="Enter Login Password">
        </div>
        <button type="submit" class="btn btn-primary" value="continue" name="submit">Continue</button>
    </form>

</div>

</main>
<footer class="container">
    <p>&copy; Company 2017</p>
</footer>
<!-- Bootstrap core JavaScript -->
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>


</body>
</html>
