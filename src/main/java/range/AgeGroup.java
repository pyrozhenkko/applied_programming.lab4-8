package range;

public enum AgeGroup {
    CHILDREN("Age 1-12", 1, 12),
    TEENAGERS("Age 13-17", 13, 17),
    YOUTH("Age 18-21", 18, 21);

    private String description;
    private int minAge;
    private int maxAge;

    private AgeGroup(String description, int minAge, int maxAge) {
        this.description = description;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getDescription() {
        return description;
    }

    public static AgeGroup getAgeGroupByAge(int age) {
        for (AgeGroup group : AgeGroup.values()) {
            if (age >= group.minAge && age <= group.maxAge) {
                return group;
            }
        }
        throw new IllegalArgumentException("No AgeGroup found for age: " + age);
    }
}
