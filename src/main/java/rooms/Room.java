package rooms;

import java.io.Serializable;
import java.util.List;
import toys.Toy;

public class Room implements Serializable {
    private int maxToyQuantity;
    private String roomName;
    private double size;
    private List<Toy> toys;
    private double maxPriceForToys;

    public Room( String roomName, double size, List<Toy> toys, int maxToyQuantity, double maxPriceForToys) {
        this.roomName = roomName;
        this.size = size;
        this.toys = toys;
        this.maxPriceForToys = maxPriceForToys;
        this.maxToyQuantity = maxToyQuantity;
    }


    public int getMaxToyQuantity() {
        return maxToyQuantity;
    }

    public void setMaxToyQuantity(int maxToyQuantity) {
        this.maxToyQuantity = maxToyQuantity;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    public double getMaxPriceForToys() {
        return maxPriceForToys;
    }

    public void setMaxPriceForToys(double maxPriceForToys) {
        this.maxPriceForToys = maxPriceForToys;
    }
    public void show(){
        System.out.println("Room Name: " + roomName);
        System.out.println("Room Size: " + size);
        System.out.println("Max Toy Quantity: " + maxToyQuantity);
        System.out.println("Max Price for Toys: " + maxPriceForToys);
    }
}
