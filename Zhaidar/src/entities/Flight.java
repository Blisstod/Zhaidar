package entities;

public class Flight {
    private String fromCity;
    private String toCity;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String flightPostCode;
    private double price;
    public Flight(Flight.FlightBuilder flightBuilder) {
        this.flightPostCode = flightPostCode;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public Flight(){};
    public Flight(String flightPostCode, String fromCity, String toCity, String departureDate, String departureTime, String arrivalDate, String arrivalTime, double price){
        setFromCity(fromCity);
        setToCity(toCity);
        setDepartureDate(departureDate);
        setDepartureTime(departureTime);
        setArrivalDate(arrivalDate);
        setArrivalTime(arrivalTime);
        setFlightPostCode(flightPostCode);
        setPrice(price);
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setFlightPostCode(String flightPostCode) {
        this.flightPostCode = flightPostCode;
    }

    public String getFlightPostCode() {
        return flightPostCode;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {

        return '\n' + "|" + "FROM:" + fromCity + "   " + "TO:" + toCity + "|" + '\n' +
                "|"+ "DEPARTURE TIME: " + departureDate + " " + departureTime + "|" + '\n' +
                "|"+"ARRIVAL TIME: " + arrivalDate + " " + arrivalTime +"|" + '\n' +
                "|"+"FLIGHT POSTCODE: " + flightPostCode+ "|" + '\n';
    }
    public static class FlightBuilder{
        private String fromCity;
        private String toCity;
        private String departureDate;
        private String departureTime;
        private String arrivalDate;
        private String arrivalTime;
        private String flightPostCode;
        private double price;

        public FlightBuilder (String flightPostCode, String fromCity, String toCity, String departureDate, String departureTime, String arrivalDate, String arrivalTime, double price){
            this.flightPostCode = flightPostCode;
            this.fromCity = fromCity;
            this.toCity = toCity;
            this.departureDate = departureDate;
            this.departureTime = departureTime;
            this.arrivalDate = arrivalDate;
            this.arrivalTime = arrivalTime;
            this.price = price;
        }

        public Flight build(){
            return new Flight(this);
        }
    }
}
