package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.commands.SortingToysBy;
import toys.*;
import range.*;
import java.util.*;

class SortingToysByCommandTest {
    private DataBase mockDatabase;
    private List<Toy> toys;
    private Command sortingToysCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        toys = new ArrayList<>();
        sortingToysCommand = new SortingToysBy(toys);
    }

    @Test
    void testSortingToysExecution() {
        sortingToysCommand.execute(mockDatabase);
        verify(mockDatabase).sortToysBy(toys);
    }

    @Test
    void testSortingToysWithNullList() {
        Command sortingToysCommand = new SortingToysBy(null);
        assertDoesNotThrow(() -> sortingToysCommand.execute(mockDatabase));
        verify(mockDatabase).sortToysBy(null);
    }

    @Test
    void testSortingToysWithPopulatedList() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5,
                AgeGroup.CHILDREN, "Red", true));
        sortingToysCommand.execute(mockDatabase);
        verify(mockDatabase).sortToysBy(same(toys));
        assertEquals(1, toys.size());
    }

    @Test
    void testSortingToysWithEmptyList() {
        assertTrue(toys.isEmpty());
        sortingToysCommand.execute(mockDatabase);
        verify(mockDatabase).sortToysBy(toys);
    }
}