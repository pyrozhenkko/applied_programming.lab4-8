package command.commands;

import command.Command;
import command.DataBase;
import toys.Toy;
import java.util.List;

public class ShowToys implements Command {
    private List<Toy> toys;
    private boolean backToTheMenu;

    public ShowToys(List<Toy> toys, boolean backToTheMenu) {
        this.toys = toys;
        this.backToTheMenu = backToTheMenu;
    }

    public void execute(DataBase dataBase) {
        dataBase.showToys(toys, backToTheMenu);
    }
}
