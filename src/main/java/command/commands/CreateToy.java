package command.commands;

import command.Command;
import command.DataBase;
import toys.Toy;
import java.util.List;

public class CreateToy implements Command {
    private List<Toy> toys;

    public CreateToy(List<Toy> toys) {
        this.toys = toys;
    }

    @Override
    public void execute(DataBase dataBase) {
        dataBase.createToy(toys);
    }
}
