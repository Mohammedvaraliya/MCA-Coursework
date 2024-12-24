import java.io.*;
import java.util.*;

class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void writeToFile(BufferedWriter writer) throws IOException {
        writer.write(name + "," + price + "," + quantity);
        writer.newLine();
    }

    public static Item readFromFile(String line) {
        String[] parts = line.split(",");
        String name = parts[1].trim();
        double price = Double.parseDouble(parts[2].trim());
        int quantity = Integer.parseInt(parts[3].trim());
        return new Item(name, price, quantity);
    }

    @Override
    public String toString() {
        return "Item Name: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}

public class ItemManager {

    private static final String FILE_NAME = "assets/item.dat";

    public static void searchItem(String searchName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        boolean found = false;

        reader.readLine(); // Read and discard the header line

        while ((line = reader.readLine()) != null) {
            Item item = Item.readFromFile(line);
            if (item.name.equalsIgnoreCase(searchName)) {
                System.out.println(item);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
        reader.close();
    }

    public static void displayItems() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        double totalCost = 0;

        // Skip the header line
        reader.readLine(); // Read and discard the header line

        System.out.println("Items in the file:");
        while ((line = reader.readLine()) != null) {
            Item item = Item.readFromFile(line);
            System.out.println(item);
            totalCost += item.price * item.quantity;
        }

        System.out.println("Total cost of all items: " + totalCost);
        reader.close();
    }

    public static void findCostliestItem() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        Item costliestItem = null;

        // Skip the header line
        reader.readLine(); // Read and discard the header line

        while ((line = reader.readLine()) != null) {
            Item item = Item.readFromFile(line);
            if (costliestItem == null || item.price > costliestItem.price) {
                costliestItem = item;
            }
        }

        if (costliestItem != null) {
            System.out.println("Costliest item: " + costliestItem);
        } else {
            System.out.println("No items found.");
        }
        reader.close();
    }

    public static void addItem(String name, double price, int quantity) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        int lastId = 0;

        reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0].trim());
            lastId = id;
        }

        reader.close(); // Close the reader after reading the file

        int newId = lastId + 1;

        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));

        File file = new File(FILE_NAME);
        if (file.length() > 0) {
            writer.newLine();
        }

        writer.write(newId + "," + name + "," + price + "," + quantity);
        writer.close(); // Close the writer

        System.out.println("Item added successfully with ID: " + newId);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add a new item");
            System.out.println("2. Search for an item by name");
            System.out.println("3. Display all items and total cost");
            System.out.println("4. Find the costliest item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter item quantity: ");
                    int quantity = scanner.nextInt();
                    addItem(name, price, quantity);
                    break;

                case 2:
                    System.out.print("Enter item name to search: ");
                    String searchName = scanner.nextLine();
                    searchItem(searchName);
                    break;

                case 3:
                    displayItems();
                    break;

                case 4:
                    findCostliestItem();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}