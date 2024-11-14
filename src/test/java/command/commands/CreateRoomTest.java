package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.commands.CreateRoom;
import rooms.Room;
import java.util.*;

class CreateRoomCommandTest {
    private DataBase mockDatabase;
    private List<Room> rooms;
    private Command createRoomCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        rooms = new ArrayList<>();
        createRoomCommand = new CreateRoom(rooms);
    }

    @Test
    void testCreateRoomCommandExecution() {
        createRoomCommand.execute(mockDatabase);
        verify(mockDatabase).createRoom(rooms);
    }

    @Test
    void testCreateRoomWithNullList() {
        Command createRoomCommand = new CreateRoom(null);
        assertDoesNotThrow(() -> createRoomCommand.execute(mockDatabase));
        verify(mockDatabase).createRoom(null);
    }

    @Test
    void testCreateRoomCommandState() {
        rooms.add(new Room("TestRoom", 100.0, new ArrayList<>(), 10, 1000.0));
        createRoomCommand.execute(mockDatabase);
        verify(mockDatabase).createRoom(same(rooms));
        assertEquals(1, rooms.size());
    }

    @Test
    void testCreateRoomWithEmptyList() {
        assertTrue(rooms.isEmpty());
        createRoomCommand.execute(mockDatabase);
        verify(mockDatabase).createRoom(rooms);
    }
}