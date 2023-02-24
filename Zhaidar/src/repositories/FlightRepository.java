package repositories;

import data.interfaces.IDB;
import entities.Flight;
import repositories.interfaces.IFlightRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FlightRepository implements IFlightRepository {
    private final IDB db;
    public FlightRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean CreateFlight(Flight flight) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO flights(postcode, fromcity, tocity, departuredate, departuretime, arrivaldate, arrivaltime, price) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, flight.getFlightPostCode());
            st.setString(2, flight.getFromCity());
            st.setString(3, flight.getToCity());
            st.setString(4, flight.getDepartureDate());
            st.setString(5, flight.getDepartureTime());
            st.setString(6, flight.getArrivalDate());
            st.setString(7, flight.getArrivalTime());
            st.setDouble(8, flight.getPrice());
            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    public List<Flight> getAllFlights(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT postcode,fromcity,tocity,departuredate,departuretime,arrivaldate,arrivaltime,price FROM flights";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Flight> flights = new LinkedList<>();
            while (rs.next()) {
                Flight flight = new Flight(rs.getString("postcode"),
                        rs.getString("fromcity"),
                        rs.getString("tocity"),
                        rs.getString("departuredate"),
                        rs.getString("departuretime"),
                        rs.getString("arrivaldate"),
                        rs.getString("arrivaltime"),
                        rs.getDouble("price")
                );

                flights.add(flight);
            }

            return flights;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
