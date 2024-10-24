package toys;

import range.AgeGroup;
import range.IdentityToy;

public class ToyDoll extends  Toy {
    private String colorHair;
    private String colorEye;
    private String sex;
    private Boolean hasAccessories;



    public ToyDoll(IdentityToy identityToy, String code, double price, int weight, double size, AgeGroup ageGroup,
                   String colorHair, String colorEye, Boolean hasAccessories, String sex
    ) {
        super(identityToy, code, price, weight, size, ageGroup);
        this.colorHair = colorHair;
        this.colorEye = colorEye;
        this.hasAccessories = hasAccessories;
        this.sex = sex;
    }

    public String getColorHair() {
        return colorHair;
    }

    public void setColorHair(String colorHair) {
        this.colorHair = colorHair;
    }

    public String getColorEye() {
        return colorEye;
    }

    public void setColorEye(String colorEye) {
        this.colorEye = colorEye;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getHasAccessories() {
        return hasAccessories;
    }

    public void setHasAccessories(Boolean hasAccessories) {
        this.hasAccessories = hasAccessories;
    }
    public void show() {
        super.show();
        System.out.println("Doll Details:");
        System.out.println("Hair Color: " + colorHair);
        System.out.println("Eye Color: " + colorEye);
        System.out.println("Sex: " + sex);
        System.out.println("Has Accessories: " + (hasAccessories ? "Yes" : "No"));
    }
}
