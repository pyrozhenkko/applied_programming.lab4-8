package command;

import java.io.IOException;
import java.util.List;
import rooms.*;
import toys.*;
import java.io.*;

public class FileUtils{

    public static List<Room> loadRooms(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Room>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Toy> loadToys(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Toy>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void saveRooms(List<Room> rooms, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(rooms);
            System.out.println("Rooms have been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToys(List<Toy> toys, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(toys);
            System.out.println("Toys have been saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
