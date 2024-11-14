package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.commands.PrepareRoomWithToys;
import rooms.Room;
import toys.*;
import range.*;
import java.util.*;

class PrepareRoomWithToysCommandTest {
    private DataBase mockDatabase;
    private List<Toy> toys;
    private List<Room> rooms;
    private Command prepareRoomCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        toys = new ArrayList<>();
        rooms = new ArrayList<>();
        prepareRoomCommand = new PrepareRoomWithToys(toys, rooms);
    }

    @Test
    void testPrepareRoomWithToysExecution() {
        prepareRoomCommand.execute(mockDatabase);
        verify(mockDatabase).prepareRoomWithToys(toys, rooms);
    }

    @Test
    void testPrepareRoomWithNullLists() {
        Command prepareRoomCommand = new PrepareRoomWithToys(null, null);
        assertDoesNotThrow(() -> prepareRoomCommand.execute(mockDatabase));
        verify(mockDatabase).prepareRoomWithToys(null, null);
    }

    @Test
    void testPrepareRoomWithPopulatedLists() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5,
                AgeGroup.CHILDREN, "Red", true));
        rooms.add(new Room("TestRoom", 100.0, new ArrayList<>(), 10, 1000.0));

        prepareRoomCommand.execute(mockDatabase);
        verify(mockDatabase).prepareRoomWithToys(same(toys), same(rooms));
        assertEquals(1, toys.size());
        assertEquals(1, rooms.size());
    }

    @Test
    void testPrepareRoomWithEmptyLists() {
        assertTrue(toys.isEmpty());
        assertTrue(rooms.isEmpty());
        prepareRoomCommand.execute(mockDatabase);
        verify(mockDatabase).prepareRoomWithToys(toys, rooms);
    }
}