package command;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.DataBase;
import command.FileUtils;
import rooms.Room;
import toys.*;
import range.*;

import java.io.*;
import java.util.*;

class DataBaseTest {
    private DataBase database;
    private List<Toy> toys;
    private List<Room> rooms;
    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        database = new DataBase();
        toys = new ArrayList<>();
        rooms = new ArrayList<>();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        database.scan = new Scanner(testIn);
    }

    @Test
    void testCreateToyWithInvalidChoice() {
        provideInput("6\n5\n");
        database.createToy(toys);
        assertTrue(outputStream.toString().contains("Invalid choice"));
        assertEquals(0, toys.size());
    }

//    @Test
//    void testCreateToyWithCar() {
//        provideInput("2\nC001\n30.5\n199.99\n500\n10\nRed\nyes\n");
//        database.createToy(toys);
//        assertEquals(1, toys.size());
//        assertTrue(toys.get(0) instanceof ToyCar);
//        ToyCar car = (ToyCar) toys.get(0);
//        assertEquals("Red", car.getColor());
//        assertTrue(car.isElectric());
//    }
//
//    @Test
//    void testCreateToyWithNonElectricCar() {
//        provideInput("2\nC002\n25.5\n149.99\n450\n10\nBlue\nno\n");
//        database.createToy(toys);
//        ToyCar car = (ToyCar) toys.get(0);
//        assertFalse(car.isElectric());
//    }
//
//    @Test
//    void testCreateToyWithDoll() {
//        provideInput("3\nD001\n25.0\n149.99\n300\n8\nBlonde\nBlue\nFemale\nyes\n");
//        database.createToy(toys);
//        ToyDoll doll = (ToyDoll) toys.get(0);
//        assertEquals("Blonde", doll.getColorHair());
//        assertEquals("Blue", doll.getColorEye());
//        assertTrue(doll.getHasAccessories());
//    }
//
//    @Test
//    void testCreateToyWithDollNoAccessories() {
//        provideInput("3\nD002\n25.0\n149.99\n300\n8\nBrown\nGreen\nMale\nno\n");
//        database.createToy(toys);
//        ToyDoll doll = (ToyDoll) toys.get(0);
//        assertFalse(doll.getHasAccessories());
//    }
//
//    @Test
//    void testCreateToyWithBlocks() {
//        provideInput("1\nB001\n15.0\n79.99\n250\n5\n50\n");
//        database.createToy(toys);
//        assertTrue(toys.get(0) instanceof ToyBlocks);
//        assertEquals(50, ((ToyBlocks)toys.get(0)).getPiecesCount());
//    }
//
//    @Test
//    void testCreateToyWithPuzzle() {
//        provideInput("4\nP001\n20.0\n99.99\n200\n6\n100\n");
//        database.createToy(toys);
//        assertTrue(toys.get(0) instanceof ToyPuzzle);
//        assertEquals(100, ((ToyPuzzle)toys.get(0)).getPiecesCount());
//    }

    @Test
    void testDeleteToyNotFound() {
        provideInput("NOTFOUND\n");
        database.deleteToy(toys);
        assertTrue(outputStream.toString().contains("Toy not found"));
    }

    @Test
    void testDeleteRoomNotFound() {
        provideInput("NOTFOUND\n");
        database.deleteRoom(rooms);
        assertTrue(outputStream.toString().contains("Room not found"));
    }

    @Test
    void testShowEmptyToys() {
        database.showToys(toys);
        assertTrue(outputStream.toString().contains("Toys list is empty"));
    }

    @Test
    void testShowEmptyRooms() {
        database.showRooms(rooms);
        assertTrue(outputStream.toString().contains("Rooms list is empty"));
    }
//    @Test
//    void testPrepareRoomWithToysExceedingPrice() {
//        Room room = new Room("TestRoom", 100.0, new ArrayList<>(), 10, 100.0);
//        rooms.add(room);
//        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5, AgeGroup.CHILDREN, "Red", true));
//
//        provideInput("TestRoom\nC001\n3\n");
//        database.prepareRoomWithToys(toys, rooms);
//        assertTrue(outputStream.toString().contains("Total price or quantity exceeds"));
//    }

    @Test
    void testPrepareRoomNotFound() {
        provideInput("NonExistentRoom\n");
        database.prepareRoomWithToys(toys, rooms);
        assertTrue(outputStream.toString().contains("Room not found"));
    }

    @Test
    void testSortToysByPrice() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 200.0, 500, 30.5, AgeGroup.CHILDREN, "Red", true));
        toys.add(new ToyCar(IdentityToy.CAR, "C002", 150.0, 400, 28.5, AgeGroup.CHILDREN, "Blue", false));
        provideInput("1\n3\n");
        database.sortToysBy(toys);
        assertEquals(150.0, toys.get(0).getPrice());
    }

    @Test
    void testSortToysByWeight() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 200.0, 500, 30.5, AgeGroup.CHILDREN, "Red", true));
        toys.add(new ToyCar(IdentityToy.CAR, "C002", 150.0, 400, 28.5, AgeGroup.CHILDREN, "Blue", false));
        provideInput("2\n3\n");
        database.sortToysBy(toys);
        assertEquals(400, toys.get(0).getWeight());
    }

    @Test
    void testSortToysByInvalidChoice() {
        provideInput("5\n3\n");
        database.sortToysBy(toys);
        assertTrue(outputStream.toString().contains("Invalid choice"));
    }

    @Test
    void testFindToysByFeaturesNoMatch() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 200.0, 500, 30.5, AgeGroup.CHILDREN, "Red", true));
        provideInput("300 400\n600 700\n3\n");
        database.findToyByFeatures(toys);
        assertTrue(outputStream.toString().contains("No toys found"));
    }

    @Test
    void testChooseToysEmptyInput() {
        provideInput("\n");
        List<Toy> selected = database.chooseToys(toys);
        assertTrue(selected.isEmpty());
        assertTrue(outputStream.toString().contains("No toys were selected"));
    }
//
//    @Test
//    void testCreateToyWithInvalidAge() {
//        // Test each toy type with invalid age
//        String[] inputs = {
//                // Car with invalid age
//                "2\nC001\n30.5\n199.99\n500\n22\nRed\nyes\n5\n",
//                // Doll with invalid age
//                "3\nD001\n25.0\n149.99\n300\n0\nBlonde\nBlue\nFemale\nyes\n5\n",
//                // Blocks with invalid age
//                "1\nB001\n15.0\n79.99\n250\n30\n50\n5\n",
//                // Puzzle with invalid age
//                "4\nP001\n20.0\n99.99\n200\n25\n100\n5\n"
//        };
//
//        for (String input : inputs) {
//            provideInput(input);
//            database = new DataBase(); // Reset database for fresh Scanner
//            try {
//                database.createToy(toys);
//                fail("Should throw IllegalArgumentException for invalid age");
//            } catch (IllegalArgumentException e) {
//                assertTrue(e.getMessage().contains("No AgeGroup found for age"));
//            }
//        }
//    }
//    @Test
//    void testToyInputValidation() {
//        // Test positive values validation
//        String input = "2\nC001\n30.5\n199.99\n500\n10\nRed\nyes\n5\n";
//        provideInput(input);
//        database.createToy(toys);
//
//        Toy toy = toys.get(0);
//        assertTrue(toy.getPrice() > 0, "Price should be positive");
//        assertTrue(toy.getWeight() > 0, "Weight should be positive");
//        assertTrue(toy.getSize() > 0, "Size should be positive");
//    }
}