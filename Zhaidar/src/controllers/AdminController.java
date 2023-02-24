package controllers;

import entities.Flight;
import repositories.interfaces.IFlightRepository;

public class AdminController {
    private final IFlightRepository flightRepository;
    public AdminController(IFlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }
    public String CreateFlight(String flightPostCode, String fromCity, String toCity, String departureDate, String departureTime, String arrivalDate, String arrivalTime, double price){
        Flight flight = new Flight(flightPostCode, fromCity, toCity, departureDate, departureTime, arrivalDate, arrivalTime, price);
        boolean created = flightRepository.CreateFlight(flight);
        return (created ? "Flight was created" : "Flight creation was failed!");
    }
}