package command.commands;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import command.Command;
import command.DataBase;
import command.commands.FindToysByFeatures;
import toys.*;
import range.*;
import java.util.*;

class FindToysByFeaturesCommandTest {
    private DataBase mockDatabase;
    private List<Toy> toys;
    private Command findToysCommand;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(DataBase.class);
        toys = new ArrayList<>();
        findToysCommand = new FindToysByFeatures(toys);
    }

    @Test
    void testFindToysByFeaturesExecution() {
        findToysCommand.execute(mockDatabase);
        verify(mockDatabase).findToyByFeatures(toys);
    }

    @Test
    void testFindToysWithNullList() {
        Command findToysCommand = new FindToysByFeatures(null);
        assertDoesNotThrow(() -> findToysCommand.execute(mockDatabase));
        verify(mockDatabase).findToyByFeatures(null);
    }

    @Test
    void testFindToysWithPopulatedList() {
        toys.add(new ToyCar(IdentityToy.CAR, "C001", 199.99, 500, 30.5,
                AgeGroup.CHILDREN, "Red", true));
        findToysCommand.execute(mockDatabase);
        verify(mockDatabase).findToyByFeatures(same(toys));
        assertEquals(1, toys.size());
    }

    @Test
    void testFindToysWithEmptyList() {
        assertTrue(toys.isEmpty());
        findToysCommand.execute(mockDatabase);
        verify(mockDatabase).findToyByFeatures(toys);
    }
}