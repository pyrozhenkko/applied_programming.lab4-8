package command.commands;

import command.Command;
import command.DataBase;
import java.util.List;

import command.DatabaseRunner;
import rooms.Room;
import toys.Toy;

public class MenuPanel implements Command {
    @Override
    public void execute(DataBase dataBase) {
        DatabaseRunner.menuPanel();
    }
}