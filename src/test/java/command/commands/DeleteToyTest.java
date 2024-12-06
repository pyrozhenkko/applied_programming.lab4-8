package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import toys.*;
import range.*;
import java.util.*;

class DeleteToyTest {
    private DataBase mockDatabase;
    private List<Toy> toys;
    private Command deleteToyCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        toys = new ArrayList<>();
        deleteToyCommand = new DeleteToy(toys);
    }

    @Test
    void testDeleteToyCommandExecution() {
        deleteToyCommand.execute(mockDatabase);
        verify(mockDatabase).showToys(toys);
    }

    @Test
    void testDeleteToyWithNullList() {
        Command deleteToyCommand = new DeleteToy(null);
        assertDoesNotThrow(() -> deleteToyCommand.execute(mockDatabase));
        verify(mockDatabase).showToys(null);
    }

    @Test
    void testDeleteToyCommandState() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5,
                AgeGroup.CHILDREN, "Red", true));
        deleteToyCommand.execute(mockDatabase);
        verify(mockDatabase).showToys(same(toys));
        assertEquals(1, toys.size());
    }

    @Test
    void testDeleteToyWithEmptyList() {
        assertTrue(toys.isEmpty());
        deleteToyCommand.execute(mockDatabase);
        verify(mockDatabase).showToys(toys);
    }
}