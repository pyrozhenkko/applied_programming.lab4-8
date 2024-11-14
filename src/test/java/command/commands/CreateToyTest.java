package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.commands.CreateToy;
import toys.*;
import range.*;
import java.util.*;

class CreateToyCommandTest {
    private DataBase mockDatabase;
    private List<Toy> toys;
    private Command createToyCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        toys = new ArrayList<>();
        createToyCommand = new CreateToy(toys);
    }

    @Test
    void testCreateToyCommandExecution() {
        createToyCommand.execute(mockDatabase);
        verify(mockDatabase).createToy(toys);
    }

    @Test
    void testCreateToyWithNullList() {
        Command createToyCommand = new CreateToy(null);
        assertDoesNotThrow(() -> createToyCommand.execute(mockDatabase));
        verify(mockDatabase).createToy(null);
    }

    @Test
    void testCreateToyCommandState() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5,
                AgeGroup.CHILDREN, "Red", true));
        createToyCommand.execute(mockDatabase);
        verify(mockDatabase).createToy(same(toys));
        assertEquals(1, toys.size());
    }

    @Test
    void testCreateToyWithEmptyList() {
        assertTrue(toys.isEmpty());
        createToyCommand.execute(mockDatabase);
        verify(mockDatabase).createToy(toys);
    }
}