package command.commands;

import command.Menu;

public class ToyMenu extends Menu {

    public void initializeMenuActions() {
        menuActions.put("1", new CreateToy(toys));
        menuActions.put("2", new CreateRoom(rooms));
        menuActions.put("3", new DeleteToy(toys));
        menuActions.put("4", new DeleteRoom(rooms));
    }

    public void menu() {
        System.out.println("\n1. Create Toy");
        System.out.println("2. Create Room");
        System.out.println("3. Delete Toy");
        System.out.println("4. Delete Room");
        System.out.println("0. Back to the main menu\n");
    }
}
