import controllers.FlightController;
import entities.Flight;
import entities.User;
import controllers.BuyTicketController;
import controllers.UserController;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private final FlightController flightController;
    private final UserController userController;
    private final BuyTicketController buyTicketController;
    private final Scanner scanner;
    private int failedAuthorization = 0;
    public Application(FlightController flightController, BuyTicketController buyTicketController, UserController userController){
        this.userController = userController;
        this.flightController = flightController;
        this.buyTicketController = buyTicketController;
        scanner = new Scanner(System.in);
    }

    public void start(){
        User user = new User();
        System.out.println("Choose one of the option:");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        int option_reg = scanner.nextInt();

        if (option_reg == 1){
            user = SignIn(user);
            System.out.println(user.toString());
        }
        else if (option_reg == 2){
            System.out.println(SignUp(user));
        }
        while (true){
            System.out.println();
            System.out.println("Welcome to our Aviasales!");
            System.out.println("Select option:");
            System.out.println("1. Show my balance");
            System.out.println("2. List of all flights");
            System.out.println("3. Buy a ticket");
            System.out.println("4. Show my tickets");
            System.out.println("5. Refund the ticket");
            System.out.println("6. Create a flight");
            System.out.println("0. Exit");
            try {
                System.out.print("Enter option (1-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    showMyBalance(user);
                } else if(option == 2){
                    getAllFlightsMenu();
                } else if(option == 3){
                    buyFlightTicket(user);
                } else if(option == 4){
                    getBoughtTickets();
                } else if(option == 5){
                    refundEvent(user);
                } else if(option == 6){
                    createFlightMenu();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("___________________________________________");
        }
    }
    public String SignUp(User user){
        System.out.println("You need register first");
        System.out.println("Please enter your login");
        String login = scanner.next();
        System.out.println("Please enter your password");
        String password = scanner.next();
        System.out.println("Please enter your name");
        String name = scanner.next();
        System.out.println("Please enter your surname");
        String surname = scanner.next();
        System.out.println("Please enter your balance");
        Double balance = scanner.nextDouble();

        user = new User(login, password, name, surname, balance);

        String response = userController.CreateUser(user);
        return response;
    }
    public User SignIn(User user){
        System.out.println("You need sign in to your account!");
        System.out.println("Please write your login:");
        String login = scanner.next();
        System.out.println("Please write your password:");
        String password = scanner.next();

        user = new User(login, password);
        if(!userController.isExist(user)){
            System.out.println("Incorrect password or login.");
            if (failedAuthorization < 3) {
                System.out.println("Please try again!");
                failedAuthorization++;
                SignIn(user);
            } else if (failedAuthorization == 3) {
                System.exit(0);
            }
        }
        else {
            user = userController.SignIn(user);
        }
        return user;
    }
    public void showMyBalance(User user){
        System.out.println("Your balance is: ");
        System.out.println(user.getBalance());
    }
    public void createFlightMenu(){
        System.out.println("Please enter flight postcode:");
        String postcode = scanner.next();
        System.out.println("Please enter Departure City:");
        String fromCity = scanner.next();
        System.out.println("Please enter Arrival City:");
        String toCity = scanner.next();
        System.out.println("Please enter Departure Date and Departure Time:");
        String departureDate = scanner.next();
        String departureTime = scanner.next();
        System.out.println("Please enter Arrival Date and Arrival Time:");
        String arrivalDate = scanner.next();
        String arrivalTime = scanner.next();
        System.out.println("Please enter price for ticket:");
        Double price = scanner.nextDouble();
        String response = flightController.CreateFlight(postcode, fromCity, toCity, departureDate, departureTime, arrivalDate, arrivalTime, price);
        System.out.println(response);
    }
    public void getAllFlightsMenu(){
        String response = flightController.getAllFlights();
        System.out.println(response);
    }
    public void buyFlightTicket(User user){
        getAllFlightsMenu();
        System.out.println("Choose flight postcode:");
        String flight_postcode = scanner.next();
        Flight ticketToBuy = flightController.FindFlight(flight_postcode);
        if (buyTicketController.buyTicket(ticketToBuy, user))
            System.out.println("Successfully bought flight: " + ticketToBuy.getFromCity() + "-" + ticketToBuy.getToCity());
        else System.out.println("You do not have enough money to buy a ticket " + ticketToBuy.getToCity() + "-" + ticketToBuy.getFromCity() +"!");
    }
    public void getBoughtTickets(){
        List<Flight> boughtFlights = buyTicketController.getBoughtTickets();
        if(boughtFlights.isEmpty()){
            System.out.println("Nothing to show, buy a ticket first.");
        }
        else {
            for (Flight flight : boughtFlights) {
                System.out.println(flight.toString());
            }
        }
    }
    public void refundEvent(User user){
        if(buyTicketController.getBoughtTickets().isEmpty()){
            System.out.println("No registered flight to refund.");
        }
        else {
            getBoughtTickets();
            System.out.println("Choose flight postcode:");
            String flight_postcode = scanner.next();
            Flight flightToRefund = flightController.FindFlight(flight_postcode);
            System.out.println(buyTicketController.refundFlight(flightToRefund, user)?
                    "Successfully refunded flight: " +flightToRefund.getFromCity() + "-" + flightToRefund.getToCity():"Couldn't refund the flight:"+flightToRefund.getFromCity()+"-"+flightToRefund.getToCity());
        }
    }
}
