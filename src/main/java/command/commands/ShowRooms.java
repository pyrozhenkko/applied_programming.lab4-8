package command.commands;

import command.Command;
import command.DataBase;
import rooms.Room;
import java.util.List;

public class ShowRooms implements Command {
    private List<Room> rooms;
    private boolean backToTheMenu;

    public ShowRooms(List<Room> rooms, boolean backToTheMenu) {
        this.rooms = rooms;
        this.backToTheMenu = backToTheMenu;
    }

    @Override
    public void execute(DataBase dataBase) {
        dataBase.showRooms(rooms, backToTheMenu);
    }
}
