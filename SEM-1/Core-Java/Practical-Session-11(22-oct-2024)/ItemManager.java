import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Item {
    String name;
    int id;
    double price;
    int quantity;

    public Item(String name, int id, double price, int quantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }
}

public class ItemManager {

    private static final String FILE_NAME = "items.dat"; // Random access file name

    public static void writeItem(RandomAccessFile file, Item item) throws IOException {
        file.writeUTF(item.name);
        file.writeInt(item.id);
        file.writeDouble(item.price);
        file.writeInt(item.quantity);
    }

    // Function to read an item from the RandomAccessFile
    public static Item readItem(RandomAccessFile file) throws IOException {
        String name = file.readUTF();
        int id = file.readInt();
        double price = file.readDouble();
        int quantity = file.readInt();
        return new Item(name, id, price, quantity);
    }

    // 1. Search for a specific item by name
    public static void searchItemByName(String searchName) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            boolean found = false;
            while (file.getFilePointer() < file.length()) {
                Item item = readItem(file);
                if (item.name.equalsIgnoreCase(searchName)) {
                    System.out.println("Item Found: " + item.name + " | ID: " + item.id + " | Price: " + item.price
                            + " | Quantity: " + item.quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item not found.");
            }
        }
    }

    // 2. Display all items and total cost
    public static void displayAllItemsAndTotalCost() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            double totalCost = 0;
            System.out.println("All items:");
            while (file.getFilePointer() < file.length()) {
                Item item = readItem(file);
                System.out.println("Name: " + item.name + " | ID: " + item.id + " | Price: " + item.price
                        + " | Quantity: " + item.quantity);
                totalCost += (item.price * item.quantity);
            }
            System.out.println("Total cost of all items: " + totalCost);
        }
    }

    // 3. Find the costliest item
    public static void findCostliestItem() throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r")) {
            double maxPrice = -1;
            Item costliestItem = null;
            while (file.getFilePointer() < file.length()) {
                Item item = readItem(file);
                if (item.price > maxPrice) {
                    maxPrice = item.price;
                    costliestItem = item;
                }
            }
            if (costliestItem != null) {
                System.out.println("Costliest Item: " + costliestItem.name + " | ID: " + costliestItem.id + " | Price: "
                        + costliestItem.price);
            } else {
                System.out.println("No items available.");
            }
        }
    }

    // Main method to show the menu and handle operations
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Search for an item by name");
            System.out.println("2. Display all items and total cost");
            System.out.println("3. Find the costliest item");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter the name of the item to search: ");
                        String searchName = scanner.next();
                        searchItemByName(searchName);
                        break;
                    case 2:
                        displayAllItemsAndTotalCost();
                        break;
                    case 3:
                        findCostliestItem();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (IOException e) {
                System.out.println("Error occurred while performing the operation: " + e.getMessage());
            }
        }
    }
}