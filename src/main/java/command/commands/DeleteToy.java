package command.commands;

import command.Command;
import command.DataBase;
import toys.Toy;

import java.util.List;
import java.util.Scanner;

public class DeleteToy implements Command {
    private List<Toy> toys;

    public DeleteToy(List<Toy> toys) {
        this.toys = toys;
    }

    public void execute(DataBase dataBase) {
        dataBase.deleteToy(toys);
        // dataBase.showToys(toys);
    }
}
