package command;

import command.commands.*;
import org.junit.jupiter.api.*;
import range.AgeGroup;
import range.IdentityToy;
import rooms.Room;
import toys.Toy;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;
    private DataBase database;
    private List<Toy> testToys;
    private List<Room> testRooms;

    @BeforeEach
    void setUp() {
        testToys = new ArrayList<>();
        testRooms = new ArrayList<>();

        Toy toy1 = new Toy(IdentityToy.CAR, "code123",10.0, 13, 15.5, AgeGroup.CHILDREN);
        Toy toy2 = new Toy(IdentityToy.CAR, "code321",20.0, 13, 15.5, AgeGroup.CHILDREN);

        testToys.add(toy1);
        testToys.add(toy2);

        Room room1 = new Room("Playroom", 50, testToys, 10, 1000);
        Room room2 = new Room("Learning room", 70, testToys, 10, 1000);
        testRooms.add(room1);
        testRooms.add(room2);

        FileUtils.saveToys(testToys, "toys.dat");
        FileUtils.saveRooms(testRooms, "rooms.dat");

        menu = new Menu();
        database = new DataBase();
    }

    @Test
    void testShowToys() {
        ShowToys showToys = new ShowToys(testToys);
        showToys.execute(database);

        assertEquals(2, testToys.size());
        assertEquals(IdentityToy.CAR, testToys.get(0).getIdentityToy());
        assertEquals(IdentityToy.CAR, testToys.get(1).getIdentityToy());
    }

    @Test
    void testShowRooms() {
        ShowRooms showRooms = new ShowRooms(testRooms);
        showRooms.execute(database);

        assertEquals(2, testRooms.size());
        assertEquals("Playroom", testRooms.get(0).getRoomName());
        assertEquals("Learning room", testRooms.get(1).getRoomName());
    }

    @Test
    void testExit() {
        String input = "0\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        assertDoesNotThrow(() -> menu.execute(database));
    }

}