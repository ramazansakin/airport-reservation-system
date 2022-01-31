package com.sakinr.patika.airportreservatinsystem.exception;

public class QuotaIsFullException extends RuntimeException {
    public QuotaIsFullException(String flightCode) {
        super("Sorry ticket not found. Quota is full for flight : " + flightCode);
    }
}
