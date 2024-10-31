import command.DataBase;
import command.Menu;

public class Main {
    public static void main(String[] args) {
        DataBase database = new DataBase();
        System.out.println("\n\tWelcome to the GAME-ROOM!\n");
        Menu databaseRunner = new Menu();
        databaseRunner.execute(database);
    }
}
