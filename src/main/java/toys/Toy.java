package toys;

import range.AgeGroup;
import range.IdentityToy;

import java.io.Serializable;

public class Toy implements Serializable {
    protected double size;
    protected IdentityToy identityToy;
    protected AgeGroup ageGroup;
    protected String code;
    protected double price;
    protected int weight;

    public Toy(IdentityToy identityToy, String code, double price, int weight, double size, AgeGroup ageGroup) {
        this.identityToy = identityToy;
        this.code = code;
        this.size = size;
        this.price = price;
        this.weight = weight;
        this.ageGroup = ageGroup;
    }

    public IdentityToy getIdentityToy() {
        return identityToy;
    }

    public void setIdentityToy(IdentityToy identityToy) {
        this.identityToy = identityToy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void show() {
        System.out.println("Type of Toy: " + identityToy);
        System.out.println("Code: " + code);
        System.out.println("Price: " + price + " UAH");
        System.out.println("Weight: " + weight + " g");
        System.out.println("Size: " + size);
        System.out.println("Age Group: " + ageGroup);
    }

}
