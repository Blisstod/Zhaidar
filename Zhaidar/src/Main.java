import controllers.BuyTicketController;
import controllers.FlightController;
import controllers.UserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.FlightRepository;
import repositories.UsersRepositories;
import repositories.interfaces.IFlightRepository;
import repositories.interfaces.IUsersRepositories;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IFlightRepository flightRepository = new FlightRepository(db);
        UsersRepositories usersRepositories = new UsersRepositories(db);
        FlightController flightController = new FlightController(flightRepository);
        UserController userController = new UserController(usersRepositories);
        BuyTicketController buyTicketController = new BuyTicketController();
        Application app = new Application(flightController, buyTicketController, userController);
        app.start();
    }
}