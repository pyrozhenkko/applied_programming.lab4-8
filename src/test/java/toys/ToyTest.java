package toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import range.AgeGroup;
import range.IdentityToy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ToyTest {

    private Toy toy;

    @BeforeEach
    void setUp() {
        IdentityToy identityToy = IdentityToy.DOLL;
        toy = new Toy(identityToy, "ABC123", 200.0, 300, 15.5, AgeGroup.CHILDREN);
    }

    @Test
    void testGetIdentityToy() {
        assertNotNull(toy.getIdentityToy());
        assertEquals("DOLL", toy.getIdentityToy().name());
    }

    @Test
    void testSetIdentityToy() {
        IdentityToy newIdentityToy = IdentityToy.BALL;
        toy.setIdentityToy(newIdentityToy);
        assertEquals("BALL", toy.getIdentityToy().name());
    }

    @Test
    void testGetCode() {
        assertEquals("ABC123", toy.getCode());
    }

    @Test
    void testSetCode() {
        toy.setCode("XYZ456");
        assertEquals("XYZ456", toy.getCode());
    }

    @Test
    void testGetPrice() {
        assertEquals(200.0, toy.getPrice());
    }

    @Test
    void testSetPrice() {
        toy.setPrice(250.0);
        assertEquals(250.0, toy.getPrice());
    }

    @Test
    void testGetWeight() {
        assertEquals(300, toy.getWeight());
    }

    @Test
    void testSetWeight() {
        toy.setWeight(350);
        assertEquals(350, toy.getWeight());
    }

    @Test
    void testGetAgeGroup() {
        assertEquals(AgeGroup.CHILDREN, toy.getAgeGroup());
    }

    @Test
    void testSetAgeGroup() {
        toy.setAgeGroup(AgeGroup.TEENAGERS);
        assertEquals(AgeGroup.TEENAGERS, toy.getAgeGroup());
    }

    @Test
    void testGetSize() {
        assertEquals(15.5, toy.getSize());
    }

    @Test
    void testSetSize() {
        toy.setSize(18.0);
        assertEquals(18.0, toy.getSize());
    }

    @Test
    void testShow() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        toy.show();

        String output = outputStream.toString();
        assertTrue(output.contains("Type of Toy: DOLL"));
        assertTrue(output.contains("Code: ABC123"));
        assertTrue(output.contains("Price: 200.0 UAH"));
        assertTrue(output.contains("Weight: 300 g"));
        assertTrue(output.contains("Size: 15.5"));
        assertTrue(output.contains("Age Group: CHILDREN"));

        System.setOut(System.out);
    }

    @Test
    void testGetAgeGroupByAge() {
        assertEquals(AgeGroup.CHILDREN, AgeGroup.getAgeGroupByAge(8));
        assertEquals(AgeGroup.TEENAGERS, AgeGroup.getAgeGroupByAge(15));
        assertEquals(AgeGroup.YOUTH, AgeGroup.getAgeGroupByAge(20));
    }

    @Test
    void testGetAgeGroupByAge_Invalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            AgeGroup.getAgeGroupByAge(25);
        });
    }
}