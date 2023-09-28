package homesafe;

import homesafe.dao.DAOFactory;
import homesafe.dao.UserDAO;
import homesafe.event.AbstractSafeEvent;
import homesafe.event.DoorEvent;
import homesafe.event.ExampleSafeEvent;
import homesafe.entity.User;
import homesafe.service.EventService;
import homesafe.service.ExampleEventConsumer;
import homesafe.service.ExampleEventProducer;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeSafe {

    private static ExecutorService es;

    /**
     * The main starting point of the Home Safe software.
     * TODO: If any startup arguments are required, define them here.
     * @param args command line input arguments
     */
    public static void main(String[] args) {
        initialize();
        manualTestDatabase();
        manualTestEvents();

        // adding hard stop so system doesn't keep running forever
        try {
            es.shutdown();
            es.awaitTermination(1000, TimeUnit.MILLISECONDS);
            EventService.getInstance().stop();
        } catch (Exception e) {
            // don't care
        }

    }

    /**
     * This method will initialize and spin up all threads for sensors,
     * and other thread services.
     */
    private static void initialize() {
        // create services
        EventService eventService = EventService.getInstance();

        // Create thread executor service and dispatch threads
        es = Executors.newCachedThreadPool();
        es.execute(eventService);

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
        // create listener
        ExampleEventConsumer consumer = new ExampleEventConsumer();
        // spawn lots of threads and test event system
        for (int i = 0; i < 100; i++) {
            es.execute(() -> {
                ExampleEventProducer prod = new ExampleEventProducer("mike");
                for (int i1 = 0; i1 < 50; i1++) {
                    prod.setName(String.valueOf(i1));
                }
            });
        }

    }

}
