/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 * Alba Airways application M813-TMA02-ChooseSeat
 *
 * @author james chalmers Open University F6418079
 */
public enum SeatTypeEnum {
    UNSPECIFIED("Unspecified"),
    FIRSTCLASS("FirstClass"),
    ECONOMY("Economy");

    String seatTypeString;

    private SeatTypeEnum(String seatTypeString) {
        this.seatTypeString = seatTypeString;
    }

    /**
     * A common method for all enums since they can't have another base class
     *
     * @param <T> Enum type
     * @param c enum type. All enums must be all caps.
     * @param string case insensitive
     * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if (c != null && string != null) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch (IllegalArgumentException ex) {
                ex.getMessage();
            }
        }
        return null;
    }

    public static SeatTypeEnum fromString(String seatTypeString) {
        return getEnumFromString(SeatTypeEnum.class, seatTypeString);
    }

    public String getSeatTypeString() {
        return seatTypeString;
    }

    public void setSeatTypeString(String seatTypeString) {
        this.seatTypeString = seatTypeString;
    }
    
    
}
