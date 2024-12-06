package command;

import command.commands.*;
import toys.*;
import rooms.*;
import java.util.*;

public class Menu implements Command {
    protected DataBase database;
    protected List<Toy> toys = FileUtils.loadToys("toys.dat");
    protected List<Room> rooms = FileUtils.loadRooms("rooms.dat");
    protected Map<String, Command> menuActions = new HashMap<>();

    public Menu() {
        initializeMenuActions();
    }

    protected void initializeMenuActions() {
        menuActions.put("1", new ShowToys(toys));
        menuActions.put("2", new ShowRooms(rooms));
        menuActions.put("3", new PrepareRoomWithToys(toys, rooms));
        menuActions.put("4", new ToyMenu());
    }

    @Override
    public void execute(DataBase dataBase) {
        this.database = dataBase;
        menu();
        String choice = new Scanner(System.in).nextLine();
        System.out.println("\tYour Choice: " + choice);
        while (!choice.equals("0")) {
            Command action = menuActions.get(choice);
            if (action != null) {
                action.execute(database);
            } else {
                System.out.println("Invalid choice.");
            }
            menu();
            choice = new Scanner(System.in).nextLine();
            System.out.println("\tYour Choice: " + choice);
        }
    }


    protected void menu() {
        System.out.println("\n1. Show Available Toys");
        System.out.println("2. Show Available Rooms");
        System.out.println("3. Prepare Room With Toys");
        System.out.println("4. More Info");
        System.out.println("0. Exit\n");
    }
}