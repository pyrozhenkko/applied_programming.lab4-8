package range;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgeGroupTest {

    @Test
    void testGetDescription() {
        assertEquals("Age 1-12", AgeGroup.CHILDREN.getDescription());
        assertEquals("Age 13-17", AgeGroup.TEENAGERS.getDescription());
        assertEquals("Age 18-21", AgeGroup.YOUTH.getDescription());
    }

    @Test
    void testGetAgeGroupByAge() {
        assertEquals(AgeGroup.CHILDREN, AgeGroup.getAgeGroupByAge(5));
        assertEquals(AgeGroup.TEENAGERS, AgeGroup.getAgeGroupByAge(15));
        assertEquals(AgeGroup.YOUTH, AgeGroup.getAgeGroupByAge(19));
    }

    @Test
    void testGetAgeGroupByAgeInvalid() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            AgeGroup.getAgeGroupByAge(25);
        });
        assertEquals("No AgeGroup found for age: 25", thrown.getMessage());
    }
}
