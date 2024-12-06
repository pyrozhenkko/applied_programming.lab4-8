package toys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import range.AgeGroup;
import range.IdentityToy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ToyBlocksTest {

    private ToyBlocks toyBlocks;

    @BeforeEach
    void setUp() {
        IdentityToy identityToy = IdentityToy.BLOCKS;
        toyBlocks = new ToyBlocks(1000, identityToy, "BLK123", 350.0, 400, 20.0, AgeGroup.CHILDREN);
    }

    @Test
    void testShow() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        toyBlocks.show();

        String output = outputStream.toString();
        assertTrue(output.contains("Type of Toy: BLOCKS"));
        assertTrue(output.contains("Code: BLK123"));
        assertTrue(output.contains("Price: 350.0 UAH"));
        assertTrue(output.contains("Weight: 400 g"));
        assertTrue(output.contains("Size: 20.0"));
        assertTrue(output.contains("Age Group: CHILDREN"));
        assertTrue(output.contains("Puzzle Details:"));
        assertTrue(output.contains("Number of Pieces: 1000"));
        assertTrue(output.contains("ToyBlocks 1000 pieces"));

        System.setOut(System.out);
    }

    @Test
    void testConstructor() {
        assertEquals("BLK123", toyBlocks.getCode());
        assertEquals(350.0, toyBlocks.getPrice());
        assertEquals(400, toyBlocks.getWeight());
        assertEquals(20.0, toyBlocks.getSize());
        assertEquals(AgeGroup.CHILDREN, toyBlocks.getAgeGroup());
        assertEquals(1000, toyBlocks.getPiecesCount());
    }
}