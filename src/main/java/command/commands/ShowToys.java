package command.commands;

import command.Command;
import command.DataBase;
import toys.Toy;
import java.util.List;

public class ShowToys implements Command {
    private List<Toy> toys;

    public ShowToys(List<Toy> toys) {
        this.toys = toys;
    }

    public void execute(DataBase dataBase) {
        dataBase.showToys(toys);
    }
}
