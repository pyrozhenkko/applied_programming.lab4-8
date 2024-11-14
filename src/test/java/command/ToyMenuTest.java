package command;
import command.commands.ToyMenu;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;


class ToyMenuTest {
    private ToyMenu toyMenu;
    private DataBase dataBase;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        toyMenu = new ToyMenu();
        dataBase = new DataBase();
    }

    @Test
    void testMenuDisplay() {
        toyMenu.menu();
        String output = outContent.toString();
        assertTrue(output.contains("1. Create Toy"));
        assertTrue(output.contains("0. Back to the main menu"));
    }
}