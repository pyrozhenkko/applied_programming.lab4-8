package command.commands;

import command.Command;
import command.DataBase;
import toys.Toy;
import java.util.List;

public class SortingToysBy implements Command {
    private List<Toy> toys;

    public SortingToysBy(List<Toy> toys) {
        this.toys = toys;
    }
    @Override
    public void execute(DataBase dataBase) {
        dataBase.sortToysBy(toys);
    }
}
