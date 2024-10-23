import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Item {
    private String name;
    private int id;
    private double price;
    private int quantity;

    public Item(String name, int id, double price, int quantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public void display() {
        System.out.println("Item Name: " + name);
        System.out.println("Item ID: " + id);
        System.out.println("Item Price: " + price);
        System.out.println("Item Quantity: " + quantity);
        System.out.println("----------------------------");
    }
}

public class ItemFileReader {
    public static void main(String[] args) {
        String filePath = "assets\\items.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] itemDetails = line.split(","); // Split by comma

                String name = itemDetails[0];
                int id = Integer.parseInt(itemDetails[1]);
                double price = Double.parseDouble(itemDetails[2]);
                int quantity = Integer.parseInt(itemDetails[3]);

                Item item = new Item(name, id, price, quantity);
                item.display();
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}