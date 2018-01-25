/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entities.Seat;
import enums.PassengerEnum;
import enums.SeatTypeEnum;
import java.text.NumberFormat;

/**
 *
 * @author james
 */
public class BookingCartItem {

    private Seat seat;
    private PassengerEnum seatOwner;
    private SeatTypeEnum seatType;

    public BookingCartItem() {
    }
    
    public BookingCartItem(Seat seat) {
        this.seat = seat;
    }

    public BookingCartItem(Seat seat,PassengerEnum seatOwner) {
        this.seat = seat;
        this.seatOwner = seatOwner;
    }

    public BookingCartItem(Seat seat, PassengerEnum seatOwner, SeatTypeEnum seatType) {
        this.seat = seat;
        this.seatOwner = seatOwner;
        this.seatType = seatType;
    }

    public BookingCartItem(BookingCartItem seat, PassengerEnum passengerEnum, SeatTypeEnum seatTypeEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public SeatTypeEnum getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatTypeEnum seatType) {
        this.seatType = seatType;
    }

    public PassengerEnum getSeatOwner() {
        return seatOwner;
    }

    public void setSeatOwner(PassengerEnum seatOwner) {
        this.seatOwner = seatOwner;
    }

    public Seat getSeat() {
        return seat;
    }

    public Double getSeatTotal() {
        Double amount = 0.0;
        seat.setSeatPrice(75.0);
        if (null != seatOwner) switch (seatOwner) {
            case ADULT:
                amount = seat.getSeatPrice();
                break;
            case CHILD:
                amount = seat.getSeatPrice() * (1 - 0.5);
                break;
            case INFANT:
                amount = seat.getSeatPrice() * (1 - 0.75);
                break;
            default:
                break;
        }
        return amount;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getSeatTotal().doubleValue());
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
