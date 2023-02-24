package repositories.interfaces;

import entities.Flight;

import java.util.List;

public interface IFlightRepository {
    boolean CreateFlight(Flight flight);
    List<Flight> getAllFlights();
}
