package command;

public interface Command{
    void execute(DataBase dataBase);
    default String getInfo(){
        return "";
    }
}
