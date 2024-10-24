package toys;

import range.AgeGroup;
import range.IdentityToy;

public class ToyCar extends Toy{
    private String color;
    private boolean isElectric;

    public ToyCar(IdentityToy identityToy, String code, double price, int weight, double size, AgeGroup ageGroup,
                  String color, boolean isElectric) {
        super(identityToy, code, price, weight, size, ageGroup);
        this.color = color;
        this.isElectric = isElectric;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }
    public void show(){
        super.show();
        System.out.println("Color: " + color);
        System.out.println("isElectric: " + isElectric);
    }
}
