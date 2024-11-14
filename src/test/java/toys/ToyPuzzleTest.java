package toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import range.AgeGroup;
import range.IdentityToy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ToyPuzzleTest {

    private ToyPuzzle toyPuzzle;

    @BeforeEach
    void setUp() {
        // Підготовка тестового об'єкта перед кожним тестом
        IdentityToy identityToy = IdentityToy.PUZZLE;
        toyPuzzle = new ToyPuzzle(500, identityToy, "PZL123", 300.0, 200, 25.0, AgeGroup.TEENAGERS);
    }

    @Test
    void testGetPiecesCount() {
        assertEquals(500, toyPuzzle.getPiecesCount());
    }

    @Test
    void testSetPiecesCount() {
        toyPuzzle.setPiecesCount(600);
        assertEquals(600, toyPuzzle.getPiecesCount());
    }

    @Test
    void testShow() {
        // Перенаправляємо стандартний вивід
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        toyPuzzle.show();

        String output = outputStream.toString();
        assertTrue(output.contains("Type of Toy: PUZZLE"));
        assertTrue(output.contains("Code: PZL123"));
        assertTrue(output.contains("Price: 300.0 UAH"));
        assertTrue(output.contains("Weight: 200 g"));
        assertTrue(output.contains("Size: 25.0"));
        assertTrue(output.contains("Age Group: TEENAGERS"));
        assertTrue(output.contains("Puzzle Details:"));
        assertTrue(output.contains("Number of Pieces: 500"));

        // Відновлюємо стандартний вивід
        System.setOut(System.out);
    }

    @Test
    void testConstructor() {
        // Перевірка ініціалізації об'єкта через конструктор
        assertEquals("PZL123", toyPuzzle.getCode());
        assertEquals(300.0, toyPuzzle.getPrice());
        assertEquals(200, toyPuzzle.getWeight());
        assertEquals(25.0, toyPuzzle.getSize());
        assertEquals(AgeGroup.TEENAGERS, toyPuzzle.getAgeGroup());
        assertEquals(500, toyPuzzle.getPiecesCount());
    }
}
