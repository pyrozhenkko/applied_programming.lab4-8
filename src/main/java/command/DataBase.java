package command;

import rooms.Room;
import toys.*;
import range.*;
import java.util.*;
import static java.util.Collections.*;

public class DataBase {
    public Scanner scan = new Scanner(System.in);
    public void createToy(List<Toy> toys) {
        System.out.println("Enter identity of the toy: \n");
        System.out.print("1 Blocks\n");
        System.out.print("2 Car\n");
        System.out.print("3 Doll\n");
        System.out.print("4 Puzzles\n");
        System.out.print("5 Back to the menu\n");
        String choice = scan.nextLine();
        switch (choice) {
            case "1":
                createBlocks(toys);
                break;
            case "2":
                createCar(toys);
                break;
            case "3":
                createDoll(toys);
                break;
            case "4":
                createPuzzles(toys);
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                createToy(toys);
                break;
        }

    }

    private Toy getToyDetails(IdentityToy identityToy) {
        System.out.println("Enter toy code: ");
        String code = scan.nextLine();

        System.out.println("Enter toy size: ");
        double size = scan.nextDouble();

        System.out.println("Enter toy price: ");
        double price = scan.nextDouble();

        System.out.println("Enter toy weight (in grams): ");
        int weight = scan.nextInt();

        System.out.println("Enter age this toy expected to use: ");
        int age = scan.nextInt();
        AgeGroup ageGroup = AgeGroup.getAgeGroupByAge(age);

        scan.nextLine();
        return new Toy(identityToy, code, price, weight, size, ageGroup);
    }

    public void createCar(List<Toy> toys) {
        Toy toyDetails = getToyDetails(IdentityToy.CAR);

        System.out.println("Enter color of the car: ");
        String color = scan.nextLine();

        System.out.println("Is it electric?(yes/no): ");
        String isElectricStr = scan.nextLine();
        boolean isElectric = isElectricStr.equalsIgnoreCase("yes");

        ToyCar car = new ToyCar(toyDetails.getIdentityToy(), toyDetails.getCode(),
                toyDetails.getPrice(), toyDetails.getWeight(),
                toyDetails.getSize(), toyDetails.getAgeGroup(),
                color, isElectric);
        toys.add(car);
        FileUtils.saveToys(toys, "toys.dat");
    }

    public void createDoll(List<Toy> toys) {
        Toy toyDetails = getToyDetails(IdentityToy.DOLL);

        System.out.println("Enter color of the doll's hair: \n");
        String colorHair = scan.nextLine();

        System.out.println("Enter color of the eyes: \n");
        String colorEyes = scan.nextLine();

        System.out.println("Sex: \n");
        String sex = scan.nextLine();

        System.out.println("Has accessories (yes/no): \n");
        String hasAccessories = scan.nextLine();
        boolean accessories = hasAccessories.equalsIgnoreCase("yes");

        ToyDoll doll = new ToyDoll(toyDetails.getIdentityToy(), toyDetails.getCode(),
                toyDetails.getPrice(), toyDetails.getWeight(),
                toyDetails.getSize(), toyDetails.getAgeGroup(),
                colorHair, colorEyes, accessories, sex);

        toys.add(doll);
        FileUtils.saveToys(toys, "toys.dat");
    }

    public void createBlocks(List<Toy> toys) {
        Toy toyDetails = getToyDetails(IdentityToy.BLOCKS);

        System.out.println("Enter quantity of pieces: \n");
        int quantity = scan.nextInt();
        scan.nextLine();

        ToyBlocks blocks = new ToyBlocks(quantity,toyDetails.getIdentityToy(), toyDetails.getCode(),
                toyDetails.getPrice(), toyDetails.getWeight(),
                toyDetails.getSize(), toyDetails.getAgeGroup());

        toys.add(blocks);
        FileUtils.saveToys(toys, "toys.dat");

    }

    public void createPuzzles(List<Toy> toys) {
        Toy toyDetails = getToyDetails(IdentityToy.PUZZLE);

        System.out.println("Enter quantity of the pieces: \n");
        int quantity = scan.nextInt();
        scan.nextLine();

        ToyPuzzle puzzle = new ToyPuzzle(quantity,
                toyDetails.getIdentityToy(),
                toyDetails.getCode(),
                toyDetails.getPrice(),
                toyDetails.getWeight(),
                toyDetails.getSize(),
                toyDetails.getAgeGroup());

        toys.add(puzzle);
        FileUtils.saveToys(toys, "toys.dat");

    }

    public void deleteToy(List<Toy> toys) {
        boolean found = false;
        System.out.println("Enter code of the toy to delete");
        String code = scan.nextLine();

        Iterator<Toy> iterator = toys.iterator();
        while (iterator.hasNext()) {
            Toy toy = iterator.next();
            if (toy.getCode().equals(code)) {
                found = true;
                iterator.remove();
                System.out.println("Successfully deleted toy");
                break;
            }
        }

        if (!found) {
            System.out.println("Toy not found");
        }
        FileUtils.saveToys(toys, "toys.dat");
    }

    public void createRoom(List<Room> rooms) {

        System.out.print("Enter the name of the room: ");
        String roomName = scan.nextLine();

        System.out.print("Enter the size of the room: ");
        double size = scan.nextDouble();

        System.out.print("Enter the maximum number of toys allowed in the room: ");
        int maxToyQuantity = scan.nextInt();

        System.out.print("Enter the maximum price for toys in the room: ");
        double maxPriceForToys = scan.nextDouble();

        scan.nextLine();

        Room newRoom = new Room(roomName, size, new ArrayList<>(), maxToyQuantity, maxPriceForToys);

        rooms.add(newRoom);
        FileUtils.saveRooms(rooms, "rooms.dat");

        System.out.println("Room created successfully!");
    }


    public void deleteRoom(List<Room> rooms) {
        showRooms(rooms);
        boolean found = false;
        System.out.println("Enter name of the room to delete");
        String roomName = scan.nextLine();

        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            Room room = iterator.next();
            if (room.getRoomName().equals(roomName)) {
                found = true;
                iterator.remove();
                System.out.println("Successfully deleted room");
                break;
            }
        }

        if (!found) {
            System.out.println("Room not found");
        }
        FileUtils.saveRooms(rooms, "rooms.dat");
    }

    public void showToys(List<Toy> toys) {
        int temp = 1;
        if (toys.isEmpty()) {
            System.out.println("\tToys list is empty");
        } else {
            System.out.println("\tToys list:");
            for (Toy toy : toys) {
                System.out.println("\nToy № " + temp + "\n");
                toy.show();
                temp++;
            }
        }
    }

    public void showRooms(List<Room> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("\tRooms list is empty");
        } else {
            int temp = 1;
            for (Room room : rooms) {
                System.out.println("\nRoom № " + temp + "\n");
                room.show();
                temp++;
            }
        }
    }

    public void prepareRoomWithToys(List<Toy> toys, List<Room> rooms) {
        showRooms(rooms);
        System.out.println("Choose Room(name of the room):");
        String roomName = scan.nextLine();
        Room selectedRoom = null;

        for (Room room : rooms) {
            if (room.getRoomName().equals(roomName)) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        showToys(toys);
        System.out.println("Enter toy codes separated by space:");
        List<Toy> chosenToys = chooseToys(toys);
        double totalPrice = calculateTotalPrice(chosenToys);

        if (totalPrice <= selectedRoom.getMaxPriceForToys() && toys.size()<= selectedRoom.getMaxToyQuantity()) {
            selectedRoom.setToys(chosenToys);
        } else {
            System.out.println("Total price or quantity exceeds not allowed.");
            prepareRoomWithToys(toys, rooms);
        }

        System.out.println("Your prepared room:\n");
        selectedRoom.show();
        List<Toy> toysInRoom = selectedRoom.getToys();
        if (!toysInRoom.isEmpty()) {
            int temp =1;
            for (Toy toy : toysInRoom) {
                System.out.println("\nToy № " + temp + "\n");
                toy.show();
                temp++;
            }
        } else {
            System.out.println("No toys added to the room.");
        }
        prepareRoomWithToysAdding(chosenToys);
    }
    public double calculateTotalPrice(List<Toy> chosenToys) {
        double totalPrice = 0;
        for (Toy toy : chosenToys) {
            totalPrice += toy.getPrice();
        }
        return totalPrice;
    }

    public void prepareRoomWithToysAdding(List<Toy> toysInRoom) {
        System.out.println("\t\nEnter an option\n");
        menuAdding();
        String choce = scan.nextLine();
        switch (choce) {
            case "1":findToyByFeatures(toysInRoom);
                break;
            case "2":sortToysBy(toysInRoom);
                break;
            case "3":
                return;
            default:System.out.println("Invalid option");
                prepareRoomWithToysAdding(toysInRoom);
        }
    }
    public void menuAdding(){
        System.out.println("1 Find toys by features");
        System.out.println("2 Sort toys by...");
        System.out.println("3 Back to main menu");
    }

    public List<Toy> chooseToys(List<Toy> toys) {
        String[] input = scan.nextLine().split(" ");
        List<Toy> toyList = new ArrayList<>();

        for (String code : input) {
            for (Toy toy : toys) {
                if (toy.getCode().equals(code)) {
                    toyList.add(toy);
                }
            }
        }

        if (toyList.isEmpty()) {
            System.out.println("No toys were selected.");
        }

        return toyList;
    }

    public void sortToysBy(List<Toy> toys) {
        showSortToysBy();
        String choice = scan.nextLine();
        switch (choice) {
            case "1":
                sortingToysByPrice(toys);
                break;
            case "2":
                sortingToysByWeight(toys);
                break;
            default:
                System.out.println("Invalid choice");
        }
        prepareRoomWithToysAdding(toys);
    }

    public static void sortingToysByPrice(List<Toy> toys) {
        sort(toys, Comparator.comparing(Toy::getPrice));
        int temp = 1;
        for (Toy toy : toys) {
            System.out.println("\nToy № " + temp + "\n");
            toy.show();
            temp++;
        }
    }

    public static void sortingToysByWeight(List<Toy> toys) {
        sort(toys, Comparator.comparing(Toy::getWeight));
        int temp = 1;
        for (Toy toy : toys) {
            System.out.println("\nToy № " + temp + "\n");
            toy.show();
            temp++;
        }
    }

    public static void showSortToysBy() {
        System.out.println("Choose feature to sort toys:\n");
        System.out.println("1 Price");
        System.out.println("2 Weight");
        System.out.println("3 Return to the main menu\n");
    }

    public void findToyByFeatures(List<Toy> toys) {
        System.out.println("Enter price range (min max) separated by space:");
        String[] priceRange = scan.nextLine().split(" ");
        double minPrice = Double.parseDouble(priceRange[0]);
        double maxPrice = Double.parseDouble(priceRange[1]);

        System.out.println("Enter weight range (min max) separated by space:");
        String[] weightRange = scan.nextLine().split(" ");
        double minWeight = Double.parseDouble(weightRange[0]);
        double maxWeight = Double.parseDouble(weightRange[1]);

        List<Toy> foundToys = new ArrayList<>();

        for (Toy toy : toys) {
            if (toy.getPrice() >= minPrice && toy.getPrice() <= maxPrice &&
                    toy.getWeight() >= minWeight && toy.getWeight() <= maxWeight) {
                foundToys.add(toy);
            }
        }

        if (foundToys.isEmpty()) {
            System.out.println("No toys found in the specified range.");
        } else {
            System.out.println("Found toys:");
            int temp = 1;
            for (Toy toy : foundToys) {
                System.out.println("\nToy № " + temp + "\n");
                toy.show();
                temp++;
            }
        }
        prepareRoomWithToysAdding(toys);
    }
}
