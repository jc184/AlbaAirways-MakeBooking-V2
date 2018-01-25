/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author james
 */
public class Validator {

    public boolean validateForm(String firstName, String surname, String mobileNumber, String homePhoneNumber, String email, String loginName, String password, String creditcardtype, String creditcardnumber, String address1, String postCode, String city, String countyState, String country, HttpServletRequest request) {

        boolean errorFlag = false;
        boolean firstNameError;
        boolean surnameError;
        boolean mobileNumberError;
        boolean homePhoneNumberError;
        boolean emailError;
        boolean loginNameError;
        boolean passwordError;
        boolean creditcardtypeError;
        boolean creditcardnumberError;
        boolean address1Error;
        boolean postCodeError;
        boolean cityError;
        boolean countyStateError;
        boolean countryError;
        

        if (firstName == null || firstName.equals("") || firstName.length() > 45) {
            errorFlag = true;
            firstNameError = true;
            request.setAttribute("firstNameError", firstNameError);
        }
        if (surname == null || surname.equals("") || surname.length() > 45) {
            errorFlag = true;
            surnameError = true;
            request.setAttribute("surnameError", surnameError);
        }
        if (mobileNumber == null || mobileNumber.equals("") || mobileNumber.length() > 12) {
            errorFlag = true;
            mobileNumberError = true;
            request.setAttribute("mobileNumberError", mobileNumberError);
        }
        if (homePhoneNumber == null || homePhoneNumber.equals("") || homePhoneNumber.length() > 12) {
            errorFlag = true;
            homePhoneNumberError = true;
            request.setAttribute("homePhoneNumberError", homePhoneNumberError);
        }
        if (email == null || email.equals("") || !email.contains("@")) {
            errorFlag = true;
            emailError = true;
            request.setAttribute("emailError", emailError);
        }
        if (loginName == null || loginName.equals("") || loginName.length() > 45) {
            errorFlag = true;
            loginNameError = true;
            request.setAttribute("loginNameError", loginNameError);
        }
        if (password == null || password.equals("") || password.length() > 45) {
            errorFlag = true;
            passwordError = true;
            request.setAttribute("passwordError", passwordError);
        }
        if (creditcardtype == null || creditcardtype.equals("") || creditcardtype.length() > 45) {
            errorFlag = true;
            creditcardtypeError = true;
            request.setAttribute("creditcardtypeError", creditcardtypeError);
        }
        if (creditcardnumber == null || creditcardnumber.equals("") || creditcardnumber.length() > 19) {
            errorFlag = true;
            creditcardnumberError = true;
            request.setAttribute("creditcardnumberError", creditcardnumberError);
        }
        if (address1 == null || address1.equals("") || address1.length() > 45) {
            errorFlag = true;
            address1Error = true;
            request.setAttribute("address1Error", address1Error);
        }
        if (postCode == null || postCode.equals("") || postCode.length() > 12) {
            errorFlag = true;
            postCodeError = true;
            request.setAttribute("postCodeError", postCodeError);
        }
        if (city == null || city.equals("") || city.length() > 45) {
            errorFlag = true;
            cityError = true;
            request.setAttribute("cityError", cityError);
        }
        if (countyState == null || countyState.equals("") || countyState.length() > 45) {
            errorFlag = true;
            countyStateError = true;
            request.setAttribute("countyStateError", countyStateError);
        }
        if (country == null || country.equals("") || country.length() > 45) {
            errorFlag = true;
            countryError = true;
            request.setAttribute("countryError", countryError);
        }
        return errorFlag;
    }
}
