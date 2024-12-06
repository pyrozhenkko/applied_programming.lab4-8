package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import command.Command;
import command.DataBase;
import toys.*;
import range.*;
import java.util.*;

class ShowToysTest {
    private DataBase mockDatabase;
    private List<Toy> toys;
    private Command showToysCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        toys = new ArrayList<>();
        showToysCommand = new ShowToys(toys);
    }

    @Test
    void testShowToysExecution() {
        showToysCommand.execute(mockDatabase);
        verify(mockDatabase).showToys(toys);
    }

    @Test
    void testShowToysWithNullList() {
        Command showToysCommand = new ShowToys(null);
        assertDoesNotThrow(() -> showToysCommand.execute(mockDatabase));
        verify(mockDatabase).showToys(null);
    }

    @Test
    void testShowToysWithPopulatedList() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5,
                AgeGroup.CHILDREN, "Red", true));
        showToysCommand.execute(mockDatabase);
        verify(mockDatabase).showToys(same(toys));
        assertEquals(1, toys.size());
    }

    @Test
    void testShowToysWithEmptyList() {
        assertTrue(toys.isEmpty());
        showToysCommand.execute(mockDatabase);
        verify(mockDatabase).showToys(toys);
    }
}