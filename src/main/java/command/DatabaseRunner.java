package command;

import command.commands.*;
import toys.*;
import rooms.*;
import java.util.*;

public abstract class DatabaseRunner implements Command{
    private static DataBase database;
    private static List<Room> rooms = FileUtils.loadRooms("rooms.dat");
    private static List<Toy> toys = FileUtils.loadToys("toys.dat");
    private static final Map<String, Command> menuActions = new HashMap<>();
    private static final Map<String, Command> moreInfoActions = new HashMap<>();

    public static void main(String[] args) {
        database = new DataBase();
        initializeMenuActions();
        System.out.println("\n\tWelcome to the GAME-ROOM!\n");
        menuPanel();
    }

    private static void initializeMenuActions() {
        menuActions.put("1", new ShowToys(toys, true));
        menuActions.put("2", new ShowRooms(rooms,true));
        menuActions.put("3", new PrepareRoomWithToys(toys, rooms));
        menuActions.put("4", new MenuMoreInfo());
        menuActions.put("5", (database) -> {});
    }

    private static void initializeMoreInfoActions() {
        moreInfoActions.put("1", new CreateToy(toys));
        moreInfoActions.put("2", new CreateRoom(rooms));
        moreInfoActions.put("3", new DeleteToy(toys));
        moreInfoActions.put("4", new DeleteRoom(rooms));
        moreInfoActions.put("5", new MenuPanel());
    }

    public static void menuPanel() {
        menu();
        String choice = new Scanner(System.in).nextLine();
        System.out.println("\tYour Choice: " + choice);
        Command action = menuActions.getOrDefault(choice, (dataBase) -> {
            System.out.println("\tInvalid choice!");
            menuPanel();
        });
        action.execute(database);
    }

    public static void menuMoreInfoPanel() {
        menuMoreInfo();
        String choice = new Scanner(System.in).nextLine();
        System.out.println("\tYour Choice: " + choice);
        Command action = moreInfoActions.getOrDefault(choice, (database) -> {
            System.out.println("\tInvalid choice!");
            menuMoreInfoPanel();
        });
        action.execute(database);
    }

    public static void menu() {
        System.out.println("\n1. Show Available Toys");
        System.out.println("2. Show Available Rooms");
        System.out.println("3. Prepare Room With Toys");
        System.out.println("4. More Info");
        System.out.println("5. Exit\n");
    }

    public static void menuMoreInfo() {
        System.out.println("\n1. Create Toy");
        System.out.println("2. Create Room");
        System.out.println("3. Delete Toy");
        System.out.println("4. Delete Room");
        System.out.println("5. Back to the main menu\n");
    }
}