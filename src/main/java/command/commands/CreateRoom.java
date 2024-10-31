package command.commands;

import command.Command;
import command.DataBase;
import rooms.Room;
import java.util.List;

public class CreateRoom implements Command {
    private List<Room> rooms;

    public CreateRoom(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void execute(DataBase dataBase) {
        dataBase.createRoom(rooms);
    }
}
