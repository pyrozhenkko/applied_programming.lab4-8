package command;
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.io.*;
import toys.*;
import rooms.*;
import range.*;
class FileUtilsTest {
    private static final String TEST_TOYS_FILE = "test_toys.dat";
    private static final String TEST_ROOMS_FILE = "test_rooms.dat";

    @Test
    void saveToys_SavesToysToFile() {
        List<Toy> toys = new ArrayList<>();
        toys.add(new Toy(IdentityToy.CAR, "C001", 10.0, 500, 15.0, AgeGroup.CHILDREN));

        FileUtils.saveToys(toys, TEST_TOYS_FILE);

        File file = new File(TEST_TOYS_FILE);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void saveRooms_SavesRoomsToFile() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("PlayRoom", 100.0, new ArrayList<>(), 10, 1000.0));

        FileUtils.saveRooms(rooms, TEST_ROOMS_FILE);

        File file = new File(TEST_ROOMS_FILE);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void loadToys_LoadsToysFromFile() {
        List<Toy> originalToys = new ArrayList<>();
        originalToys.add(new Toy(IdentityToy.CAR, "C001", 10.0, 500, 15.0, AgeGroup.CHILDREN));
        FileUtils.saveToys(originalToys, TEST_TOYS_FILE);

        List<Toy> loadedToys = FileUtils.loadToys(TEST_TOYS_FILE);

        assertNotNull(loadedToys);
        assertEquals(1, loadedToys.size());
        assertEquals("C001", loadedToys.get(0).getCode());

        new File(TEST_TOYS_FILE).delete();
    }

    @Test
    void loadRooms_LoadsRoomsFromFile() {
        List<Room> originalRooms = new ArrayList<>();
        originalRooms.add(new Room("PlayRoom", 100.0, new ArrayList<>(), 10, 1000.0));
        FileUtils.saveRooms(originalRooms, TEST_ROOMS_FILE);

        List<Room> loadedRooms = FileUtils.loadRooms(TEST_ROOMS_FILE);

        assertNotNull(loadedRooms);
        assertEquals(1, loadedRooms.size());
        assertEquals("PlayRoom", loadedRooms.get(0).getRoomName());

        new File(TEST_ROOMS_FILE).delete();
    }
}
