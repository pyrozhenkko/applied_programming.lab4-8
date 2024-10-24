package command.commands;

import command.Command;
import command.DataBase;
import toys.Toy;
import java.util.List;

public class FindToysByFeatures implements Command {
    private DataBase dataBase;
    private List<Toy> toys;

    public FindToysByFeatures(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void execute(DataBase dataBase) {
        dataBase.findToyByFeatures(toys);
    }
}
