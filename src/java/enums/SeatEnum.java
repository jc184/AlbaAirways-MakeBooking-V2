/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Alba Airways application AlbaAirways-MakeBooking
 *
 * @author james chalmers Open University F6418079
 */
public enum SeatEnum {
    SEAT01("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//0
    SEAT02("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//1
    SEAT03("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//2
    SEAT04("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//3
    SEAT05("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//4
    SEAT06("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//5
    SEAT07("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),//etc
    SEAT08("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),
    SEAT09("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),
    SEAT10("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),
    SEAT11("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),
    SEAT12("FirstClass", 75.0, 38.0, 19.0, Boolean.FALSE),
    SEAT13("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT14("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT15("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT16("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT17("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT18("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT19("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT20("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT21("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT22("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT23("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE),
    SEAT24("EconomyClass", 50.0, 25.0, 12.0, Boolean.FALSE);
    
    String SeatDesignation;
    double adultFare;
    double childFare;
    double infantFare;
    boolean booked;
    
    private SeatEnum(String SeatDesignation, double adultFare, double childFare, double infantFare, boolean booked) {
        this.SeatDesignation = SeatDesignation;
        this.adultFare = adultFare;
        this.childFare = childFare;
        this.infantFare = infantFare;
        this.booked = booked;
        
    }
    
    public String getSeatDesignation() {
        return SeatDesignation;
    }
    
    public double getAdultFare() {
        return adultFare;
    }
    
    public double getChildFare() {
        return childFare;
    }
    
    public double getInfantFare() {
        return infantFare;
    }
    
    public boolean isBooked() {
        return booked;
    }
    
    public void setBooked(boolean booked) {
        this.booked = booked;
    }
    
}
