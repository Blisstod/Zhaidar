package controllers;

import entities.Flight;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class BuyTicketController {
    private List<Flight> boughtTickets = new ArrayList<>();
    public BuyTicketController(){}

    public boolean buyTicket(Flight flight, User user){
        if(flight.getPrice()<= user.getBalance()) {
            user.pay(flight.getPrice());
            this.boughtTickets.add(flight);
            return true;
        }
        return false;
    }
    public boolean refundFlight(Flight flightToRefund, User user){
        boolean index = false;
        for (Flight flight : this.boughtTickets) {
            if (flight.getFlightPostCode().equals(flightToRefund.getFlightPostCode())){
                index = true;
                break;
            }
        }
        if(index) {
            user.refund(flightToRefund.getPrice());
            //        this.registeredEvents.remove(event);
            List <Flight> refundedFlights = new ArrayList<>();
            for (Flight validFlight : boughtTickets) {
                if (!validFlight.getFlightPostCode().equals(flightToRefund.getFlightPostCode())){
                    refundedFlights.add(flightToRefund);
                }
            }
            setBoughtTickets(refundedFlights);
            return true;
        }
        return false;
    }

    public void setBoughtTickets(List<Flight> boughtTickets) {
        this.boughtTickets = boughtTickets;
    }

    public List<Flight> getBoughtTickets() {
        return boughtTickets;
    }
}
