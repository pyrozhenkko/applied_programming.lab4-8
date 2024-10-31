package command.commands;

import command.Command;
import command.DataBase;
import rooms.Room;
import java.util.List;

public class ShowRooms implements Command {
    private List<Room> rooms;

    public ShowRooms(List<Room> rooms, boolean backToTheMenu) {
        this.rooms = rooms;
    }

    @Override
    public void execute(DataBase dataBase) {
        dataBase.showRooms(rooms);
    }
}
