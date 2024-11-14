package toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import range.AgeGroup;
import range.IdentityToy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ToyCarTest {

    private ToyCar toyCar;

    @BeforeEach
    void setUp() {
        // Підготовка тестового об'єкта перед кожним тестом
        IdentityToy identityToy = IdentityToy.CAR;
        toyCar = new ToyCar(identityToy, "XYZ789", 350.0, 500, 20.0, AgeGroup.TEENAGERS, "Red", true);
    }

    @Test
    void testGetColor() {
        assertEquals("Red", toyCar.getColor());
    }

    @Test
    void testSetColor() {
        toyCar.setColor("Blue");
        assertEquals("Blue", toyCar.getColor());
    }

    @Test
    void testIsElectric() {
        assertTrue(toyCar.isElectric());
    }

    @Test
    void testSetElectric() {
        toyCar.setElectric(false);
        assertFalse(toyCar.isElectric());
    }

    @Test
    void testShow() {
        // Перенаправляємо стандартний вивід
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        toyCar.show();

        String output = outputStream.toString();
        assertTrue(output.contains("Type of Toy: CAR"));
        assertTrue(output.contains("Code: XYZ789"));
        assertTrue(output.contains("Price: 350.0 UAH"));
        assertTrue(output.contains("Weight: 500 g"));
        assertTrue(output.contains("Size: 20.0"));
        assertTrue(output.contains("Age Group: TEENAGERS"));
        assertTrue(output.contains("Color: Red"));
        assertTrue(output.contains("isElectric: true"));

        // Відновлюємо стандартний вивід
        System.setOut(System.out);
    }

    @Test
    void testConstructor() {
        // Перевірка ініціалізації об'єкта за допомогою конструктора
        assertEquals("XYZ789", toyCar.getCode());
        assertEquals(350.0, toyCar.getPrice());
        assertEquals(500, toyCar.getWeight());
        assertEquals(20.0, toyCar.getSize());
        assertEquals(AgeGroup.TEENAGERS, toyCar.getAgeGroup());
        assertEquals("Red", toyCar.getColor());
        assertTrue(toyCar.isElectric());
    }
}
