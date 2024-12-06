package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import command.Command;
import command.DataBase;
import rooms.Room;
import java.util.*;

class DeleteRoomTest {
    private DataBase mockDatabase;
    private List<Room> rooms;
    private Command deleteRoomCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        rooms = new ArrayList<>();
        deleteRoomCommand = new DeleteRoom(rooms);
    }

    @Test
    void testDeleteRoomCommandExecution() {
        deleteRoomCommand.execute(mockDatabase);
        verify(mockDatabase).deleteRoom(rooms);
    }

    @Test
    void testDeleteRoomWithNullList() {
        Command deleteRoomCommand = new DeleteRoom(null);
        assertDoesNotThrow(() -> deleteRoomCommand.execute(mockDatabase));
        verify(mockDatabase).deleteRoom(null);
    }

    @Test
    void testDeleteRoomCommandState() {
        rooms.add(new Room("TestRoom", 100.0, new ArrayList<>(), 10, 1000.0));
        deleteRoomCommand.execute(mockDatabase);
        verify(mockDatabase).deleteRoom(same(rooms));
        assertEquals(1, rooms.size());
    }

    @Test
    void testDeleteRoomWithEmptyList() {
        assertTrue(rooms.isEmpty());
        deleteRoomCommand.execute(mockDatabase);
        verify(mockDatabase).deleteRoom(rooms);
    }
}