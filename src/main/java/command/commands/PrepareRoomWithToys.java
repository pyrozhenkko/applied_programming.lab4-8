package command.commands;

import command.Command;
import command.DataBase;
import java.util.List;
import rooms.Room;
import toys.Toy;

public class PrepareRoomWithToys implements Command {
    private List<Toy>toys;
    private List<Room>rooms;
    public PrepareRoomWithToys(List<Toy> toys, List<Room> rooms) {
        this.toys = toys;
        this.rooms = rooms;
    }
    @Override
    public void execute(DataBase dataBase) {
        dataBase.prepareRoomWithToys(toys, rooms);
    }
}
