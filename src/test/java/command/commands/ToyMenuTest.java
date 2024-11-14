package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.Menu;
import command.commands.*;
import java.io.*;
import java.util.*;

class ToyMenuTest {
    private ToyMenu toyMenu;
    private DataBase mockDatabase;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        toyMenu = new ToyMenu();
        mockDatabase = mock(DataBase.class);
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testMenuInitialization() {
        toyMenu.initializeMenuActions();
        assertNotNull(toyMenu.menuActions);
        assertEquals(4, toyMenu.menuActions.size());
        assertTrue(toyMenu.menuActions.get("1") instanceof CreateToy);
        assertTrue(toyMenu.menuActions.get("2") instanceof CreateRoom);
        assertTrue(toyMenu.menuActions.get("3") instanceof DeleteToy);
        assertTrue(toyMenu.menuActions.get("4") instanceof DeleteRoom);
    }

    @Test
    void testMenuDisplay() {
        toyMenu.menu();
        String output = outputStream.toString();
        assertTrue(output.contains("1. Create Toy"));
        assertTrue(output.contains("2. Create Room"));
        assertTrue(output.contains("3. Delete Toy"));
        assertTrue(output.contains("4. Delete Room"));
        assertTrue(output.contains("0. Back to the main menu"));
    }

    @Test
    void testMenuInheritance() {
        assertTrue(toyMenu instanceof Menu);
    }

    @Test
    void testMenuActionExecution() {
        toyMenu.initializeMenuActions();
        Command command = toyMenu.menuActions.get("1");
        assertNotNull(command);
        command.execute(mockDatabase);
        verify(mockDatabase).createToy(any());
    }
}