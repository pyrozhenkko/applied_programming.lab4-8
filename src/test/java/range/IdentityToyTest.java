package range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityToyTest {

    @Test
    void testEnumValues() {
        assertEquals(5, IdentityToy.values().length);
    }

    @Test
    void testValueOf() {
        assertEquals(IdentityToy.DOLL, IdentityToy.valueOf("DOLL"));
        assertEquals(IdentityToy.BALL, IdentityToy.valueOf("BALL"));
        assertEquals(IdentityToy.CAR, IdentityToy.valueOf("CAR"));
        assertEquals(IdentityToy.PUZZLE, IdentityToy.valueOf("PUZZLE"));
        assertEquals(IdentityToy.BLOCKS, IdentityToy.valueOf("BLOCKS"));
    }

    @Test
    void testEnumOrdinal() {
        assertEquals(0, IdentityToy.DOLL.ordinal());
        assertEquals(1, IdentityToy.BALL.ordinal());
        assertEquals(2, IdentityToy.CAR.ordinal());
        assertEquals(3, IdentityToy.PUZZLE.ordinal());
        assertEquals(4, IdentityToy.BLOCKS.ordinal());
    }
}
