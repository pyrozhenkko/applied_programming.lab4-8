package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import range.AgeGroup;
import range.IdentityToy;
import rooms.Room;
import toys.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTest {
    private DataBase dataBase;
    private List<Room> rooms;
    private List<Toy> toys;
    private final InputStream systemIn = System.in;

    @BeforeEach
    public void setUp() {
        dataBase = new DataBase();
        rooms = new ArrayList<>();
        toys = new ArrayList<>();

        toys.add(new Toy(IdentityToy.CAR, "CAR001", 150.0, 300, 15.0, AgeGroup.CHILDREN));
        toys.add(new Toy(IdentityToy.DOLL, "DOLL001", 100.0, 200, 10.0, AgeGroup.CHILDREN));
        toys.add(new Toy(IdentityToy.PUZZLE, "PZ001", 50.0, 100, 5.0, AgeGroup.CHILDREN));
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }
    private void setSystemIn(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        dataBase.scan = new Scanner(System.in);
    }

    @Test
    public void testDeleteToy() {
        String input = "CAR001\n";
        provideInput(input);
        int initialSize = toys.size();
        dataBase.deleteToy(toys);
        assertEquals(initialSize - 1, toys.size());

        input = "NOTEXIST\n";
        provideInput(input);
        initialSize = toys.size();
        dataBase.deleteToy(toys);
        assertEquals(initialSize, toys.size());
    }

    @Test
    public void testDeleteRoom() {
        rooms.add(new Room("Room1", 25.0, new ArrayList<>(), 10, 500));

        String input = "Room1\n";
        provideInput(input);
        dataBase.deleteRoom(rooms);
        assertTrue(rooms.isEmpty());

        input = "NonExistent\n";
        provideInput(input);
        dataBase.deleteRoom(rooms);
        assertTrue(rooms.isEmpty());
    }

    @Test
    public void testPrepareRoomWithToys() {
        Room room = new Room("TestRoom", 100.0, new ArrayList<>(), 5, 1000.0);
        rooms.add(room);


        String input = "TestRoom\nCAR001 DOLL001\n3\n";
        provideInput(input);
        dataBase.prepareRoomWithToys(toys, rooms);
        assertFalse(room.getToys().isEmpty());
        assertEquals(2, room.getToys().size());

        input = "NonExistent\nCAR001\n3\n";
        provideInput(input);
        dataBase.prepareRoomWithToys(toys, rooms);
    }

    @Test
    public void testPrepareRoomWithToysAdding() {
        String input = "1\n0 200\n0 300\n3\n";
        provideInput(input);
        dataBase.prepareRoomWithToysAdding(toys);

        input = "2\n1\n3\n";
        provideInput(input);
        dataBase.prepareRoomWithToysAdding(toys);

        input = "2\n2\n3\n";
        provideInput(input);
        dataBase.prepareRoomWithToysAdding(toys);

        input = "3\n";
        provideInput(input);
        dataBase.prepareRoomWithToysAdding(toys);
    }

    @Test
    public void testChooseToys() {
        String input = "CAR001 DOLL001\n";
        provideInput(input);
        List<Toy> chosen = dataBase.chooseToys(toys);
        assertEquals(2, chosen.size());

        input = "INVALID1 INVALID2\n";
        provideInput(input);
        chosen = dataBase.chooseToys(toys);
        assertTrue(chosen.isEmpty());

        input = "\n";
        provideInput(input);
        chosen = dataBase.chooseToys(toys);
        assertTrue(chosen.isEmpty());
    }

    @Test
    public void testSortingFunctionality() {
        DataBase.sortingToysByPrice(toys);
        assertEquals(50.0, toys.get(0).getPrice());
        assertEquals(150.0, toys.get(toys.size() - 1).getPrice());

        DataBase.sortingToysByWeight(toys);
        assertEquals(100, toys.get(0).getWeight());
        assertEquals(300, toys.get(toys.size() - 1).getWeight());
    }


    @Test
    public void testCalculateTotalPrice() {
        assertEquals(300.0, dataBase.calculateTotalPrice(toys));

        assertEquals(0.0, dataBase.calculateTotalPrice(new ArrayList<>()));

        List<Toy> singleToy = new ArrayList<>();
        singleToy.add(toys.get(0));
        assertEquals(150.0, dataBase.calculateTotalPrice(singleToy));
    }
}

