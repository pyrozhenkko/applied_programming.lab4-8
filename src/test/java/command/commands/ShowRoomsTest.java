package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.commands.ShowRooms;
import rooms.Room;
import java.util.*;

class ShowRoomsCommandTest {
    private DataBase mockDatabase;
    private List<Room> rooms;
    private Command showRoomsCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        rooms = new ArrayList<>();
        showRoomsCommand = new ShowRooms(rooms);
    }

    @Test
    void testShowRoomsExecution() {
        showRoomsCommand.execute(mockDatabase);
        verify(mockDatabase).showRooms(rooms);
    }

    @Test
    void testShowRoomsWithNullList() {
        Command showRoomsCommand = new ShowRooms(null);
        assertDoesNotThrow(() -> showRoomsCommand.execute(mockDatabase));
        verify(mockDatabase).showRooms(null);
    }

    @Test
    void testShowRoomsWithPopulatedList() {
        rooms.add(new Room("TestRoom", 100.0, new ArrayList<>(), 10, 1000.0));
        showRoomsCommand.execute(mockDatabase);
        verify(mockDatabase).showRooms(same(rooms));
        assertEquals(1, rooms.size());
    }

    @Test
    void testShowRoomsWithEmptyList() {
        assertTrue(rooms.isEmpty());
        showRoomsCommand.execute(mockDatabase);
        verify(mockDatabase).showRooms(rooms);
    }
}