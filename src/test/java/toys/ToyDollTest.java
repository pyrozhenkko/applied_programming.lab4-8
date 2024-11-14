package toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import range.AgeGroup;
import range.IdentityToy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ToyDollTest {

    private ToyDoll toyDoll;

    @BeforeEach
    void setUp() {
        // Підготовка тестового об'єкта перед кожним тестом
        IdentityToy identityToy = IdentityToy.DOLL;
        toyDoll = new ToyDoll(identityToy, "DOLL123", 500.0, 350, 30.0, AgeGroup.CHILDREN, "Blonde", "Blue", true, "Female");
    }

    @Test
    void testGetColorHair() {
        assertEquals("Blonde", toyDoll.getColorHair());
    }

    @Test
    void testSetColorHair() {
        toyDoll.setColorHair("Brown");
        assertEquals("Brown", toyDoll.getColorHair());
    }

    @Test
    void testGetColorEye() {
        assertEquals("Blue", toyDoll.getColorEye());
    }

    @Test
    void testSetColorEye() {
        toyDoll.setColorEye("Green");
        assertEquals("Green", toyDoll.getColorEye());
    }

    @Test
    void testGetSex() {
        assertEquals("Female", toyDoll.getSex());
    }

    @Test
    void testSetSex() {
        toyDoll.setSex("Male");
        assertEquals("Male", toyDoll.getSex());
    }

    @Test
    void testGetHasAccessories() {
        assertTrue(toyDoll.getHasAccessories());
    }

    @Test
    void testSetHasAccessories() {
        toyDoll.setHasAccessories(false);
        assertFalse(toyDoll.getHasAccessories());
    }

    @Test
    void testShow() {
        // Перенаправляємо стандартний вивід
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        toyDoll.show();

        String output = outputStream.toString();
        assertTrue(output.contains("Type of Toy: DOLL"));
        assertTrue(output.contains("Code: DOLL123"));
        assertTrue(output.contains("Price: 500.0 UAH"));
        assertTrue(output.contains("Weight: 350 g"));
        assertTrue(output.contains("Size: 30.0"));
        assertTrue(output.contains("Age Group: CHILDREN"));
        assertTrue(output.contains("Doll Details:"));
        assertTrue(output.contains("Hair Color: Blonde"));
        assertTrue(output.contains("Eye Color: Blue"));
        assertTrue(output.contains("Sex: Female"));
        assertTrue(output.contains("Has Accessories: Yes"));

        // Відновлюємо стандартний вивід
        System.setOut(System.out);
    }

    @Test
    void testConstructor() {
        // Перевірка ініціалізації об'єкта через конструктор
        assertEquals("DOLL123", toyDoll.getCode());
        assertEquals(500.0, toyDoll.getPrice());
        assertEquals(350, toyDoll.getWeight());
        assertEquals(30.0, toyDoll.getSize());
        assertEquals(AgeGroup.CHILDREN, toyDoll.getAgeGroup());
        assertEquals("Blonde", toyDoll.getColorHair());
        assertEquals("Blue", toyDoll.getColorEye());
        assertTrue(toyDoll.getHasAccessories());
        assertEquals("Female", toyDoll.getSex());
    }
}
