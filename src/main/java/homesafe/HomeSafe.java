package homesafe;

import homesafe.dao.DAOFactory;
import homesafe.dao.UserDAO;
import homesafe.entity.AbstractSafeEvent;
import homesafe.entity.DoorEvent;
import homesafe.entity.ExampleSafeEvent;
import homesafe.entity.User;
import homesafe.service.EventService;
import homesafe.service.ExampleEventConsumer;
import homesafe.service.ExampleEventProducer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeSafe {

    /**
     * The main starting point of the Home Safe software.
     * TODO: If any startup arguments are required, define them here.
     * @param args command line input arguments
     */
    public static void main(String[] args) {
        manualTestDatabase();
        manualTestEvents();

        AbstractSafeEvent test = new DoorEvent("door_closed", false);
        System.out.println(test.getClass().getName());
    }

    /**
     * This method will initialize and spin up all threads for sensors,
     * and other thread services.
     */
    private static void initialize() {

        // Create thread executor service and dispatch threads
        ExecutorService executorService = Executors.newCachedThreadPool();

    }

    private static Logger getLogger() {
        return Logger.getLogger(HomeSafe.class.getName());
    }

    private static void manualTestDatabase() {
        User user = new User("Mike");
        user.setHashedPIN("Mike:123456");
        user.setAdmin(true);

        UserDAO userDAO = DAOFactory.create(UserDAO.class);

        try {
            userDAO.addUser(user);
            List<User> users = userDAO.listAllUsers();
            for (User u : users) {
                getLogger().log(Level.INFO, "{0}", u);
            }
            user.setExpiration(LocalDateTime.now());
            userDAO.updateUser(user);
            Optional<User> u = userDAO.getUserByName("Mike");
            if (u.isPresent()) {
                getLogger().log(Level.INFO, "{0}", u);
                userDAO.removeUser(u.get());
            } else {
                getLogger().log(Level.SEVERE, "FAILED TO GET USER!");
            }
            users = userDAO.listAllUsers();
            for (User x : users) {
                getLogger().log(Level.INFO, "{0}", x);
            }
        } catch (SQLException e) {
            getLogger().log(Level.WARNING, "Failed to add user to db.");
            e.printStackTrace();
        }
    }

    private static void manualTestEvents() {
        // create event producer
        ExampleEventProducer producer = new ExampleEventProducer("mike");
        // create event consumer, who subscribes on construction
        ExampleEventConsumer consumer = new ExampleEventConsumer();

        // updated producer name should emit a name change event and log it
        producer.setName("joe");
        producer.setName("abigail");

        EventService.getInstance()
                .unsubscribe(consumer, ExampleSafeEvent.class);
        producer.setName("bob");
    }

}
