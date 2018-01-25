/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entities.Booking;
import entities.Seat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author james
 */
public class BookingCart {

    List<BookingCartItem> persons;
    List<BookingCartItem> returnPersons;
    private double total;
    private double adultDiscount;
    private double childDiscount;
    private double infantDiscount;

    public BookingCart() {
        persons = new ArrayList<>();
        returnPersons = new ArrayList<>();
        total = 0.0;
        adultDiscount = 1.0;
        childDiscount = 0.5;
        infantDiscount = 0.25;
    }

    public List<BookingCartItem> getReturnPersons() {
        return returnPersons;
    }

    public void setReturnPersons(List<BookingCartItem> returnPersons) {
        this.returnPersons = returnPersons;
    }

    public List<BookingCartItem> getPersons() {
        return persons;
    }

    public void setPersons(List<BookingCartItem> persons) {
        this.persons = persons;
    }

    public double getAdultDiscount() {
        return adultDiscount;
    }

    public void setAdultDiscount(double adultDiscount) {
        this.adultDiscount = adultDiscount;
    }

    public double getChildDiscount() {
        return childDiscount;
    }

    public void setChildDiscount(double childDiscount) {
        this.childDiscount = childDiscount;
    }

    public double getInfantDiscount() {
        return infantDiscount;
    }

    public void setInfantDiscount(double infantDiscount) {
        this.infantDiscount = infantDiscount;
    }

    public double getTotal() {
        total = getBookingAmount();
        return total;
    }

    public int getCount() {
        return persons.size();
    }

    public void addItem(BookingCartItem item) {
        //If the item already exists in the cart, only the quantity is changed.
        int code = item.getSeat().getSeatPK().getSeatNo();
        for (int i = 0; i < persons.size(); i++) {
            BookingCartItem lineItem = persons.get(i);
            if (lineItem.getSeat().getSeatPK().getSeatNo() == code) {
                return;
            }
        }
        persons.add(item);
    }

    public void removeItem(BookingCartItem item) {
        int code = item.getSeat().getSeatPK().getSeatNo();
        for (int i = 0; i < persons.size(); i++) {
            BookingCartItem lineItem = persons.get(i);
            if (lineItem.getSeat().getSeatPK().getSeatNo() == code) {
                persons.remove(i);
                return;
            }
        }
    }

//    public double getOverallTotal() {//Doesn't work
//        double amount = 0;
//        double returnAmount = 0;
//        double totalAmount = amount + returnAmount;
//        Booking booking = new Booking();
//
//        for (BookingCartItem bcItem : persons) {
//            Seat seat = bcItem.getSeat();
//            amount += (booking.getNoOfAdults() * seat.getSeatPrice() * adultDiscount) + (booking.getNoOfChildren() * seat.getSeatPrice() * childDiscount) + (booking.getNoOfInfants() * seat.getSeatPrice() * infantDiscount);
//        }
//        for (BookingCartItem returnBcItem : returnPersons) {
//            Seat returnSeat = returnBcItem.getSeat();
//            returnAmount += (booking.getNoOfAdults() * returnSeat.getSeatPrice() * adultDiscount) + (booking.getNoOfChildren() * returnSeat.getSeatPrice() * childDiscount) + (booking.getNoOfInfants() * returnSeat.getSeatPrice() * infantDiscount);
//        }
//        return totalAmount;
//    }

    public double getBookingAmount() {//Should work 
        Double amount = 0.0;
        Double returnAmount = 0.0;
        
        for (BookingCartItem bcItem : persons) {
            amount += bcItem.getSeatTotal();
        }
        for (BookingCartItem returnBcItem : returnPersons) {
            returnAmount += returnBcItem.getSeatTotal();
        }
        double totalAmount = amount + returnAmount;
        return totalAmount;
    }

    public void clear() {
        persons.clear();
        total = 0;
    }

    public String getOverallTotalCurrencyFormat(Double totalAmount) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getBookingAmount());
    }
}
