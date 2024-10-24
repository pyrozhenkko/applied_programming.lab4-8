package command.commands;

import command.Command;
import command.DataBase;
import java.util.List;
import rooms.Room;

public class DeleteRoom implements Command {
    private List<Room> rooms;

    public DeleteRoom(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void execute(DataBase dataBase) {
        dataBase.deleteRoom(rooms);
    }
}
