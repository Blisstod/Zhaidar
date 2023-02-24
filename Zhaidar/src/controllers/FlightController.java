package controllers;

import entities.Flight;
import repositories.interfaces.IFlightRepository;

import java.time.LocalDate;
import java.util.List;

public class FlightController {
    private final IFlightRepository flightRepository;
    public FlightController(IFlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }
    public String getAllFlights(){
        List<Flight> flights = flightRepository.getAllFlights();
        if (flights.size() == 0)
            return "There is no any flights!";
        return flights.toString();
    }
    public String CreateFlight(String flightPostCode, String fromCity, String toCity, String departureDate, String departureTime, String arrivalDate, String arrivalTime, double price){
        Flight flight = new Flight(flightPostCode, fromCity, toCity, departureDate, departureTime, arrivalDate, arrivalTime, price);
        boolean created = flightRepository.CreateFlight(flight);
        return (created ? "Flight was created" : "FLight creation was failed!");
    }
    public Flight FindFlight(String flight_postcode){
        List<Flight> flights = flightRepository.getAllFlights();
        Flight buyTicket = new Flight();
        for (Flight flight : flights) {
            if (flight.getFlightPostCode().equals(flight_postcode)){
                buyTicket = flight;
                break;
            }
        }
        return buyTicket;
    }

}
