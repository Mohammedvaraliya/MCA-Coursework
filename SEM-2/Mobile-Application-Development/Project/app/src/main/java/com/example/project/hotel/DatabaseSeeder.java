package com.example.project.hotel;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DatabaseSeeder extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        addTables();
        addMenuCategories();
    }

    private void addTables() {
        DatabaseReference tablesRef = databaseReference.child("Tables");

        String[] tableNames = {
                "Couple Table 1", "Couple Table 2", "Couple Table 3", "Couple Table 4", "Couple Table 5",
                "Family Table 1", "Family Table 2", "Family Table 3", "Family Table 4", "Family Table 5",
                "Friends Table 1", "Friends Table 2", "Friends Table 3", "Friends Table 4", "Friends Table 5",
                "Outdoor Table 1", "Outdoor Table 2", "Outdoor Table 3", "Outdoor Table 4", "Outdoor Table 5",
                "VIP Table 1", "VIP Table 2", "VIP Table 3", "VIP Table 4", "VIP Table 5",
                "Private Cabin 1", "Private Cabin 2", "Private Cabin 3", "Private Cabin 4", "Private Cabin 5"
        };

        for (int i = 0; i < tableNames.length; i++) {
            Map<String, Object> tableData = new HashMap<>();
            tableData.put("tableNumber", i + 1);
            tableData.put("tableName", tableNames[i]);
            tableData.put("status", "available");  // initially available

            tablesRef.child("Table" + (i + 1)).setValue(tableData);
        }

        Log.d("DatabaseSeeder", "Tables added successfully!");
    }

    private void addMenuCategories() {
        DatabaseReference menuRef = databaseReference.child("Menu");

        Map<String, String[]> menuData = new HashMap<>();

        menuData.put("Indian", new String[]{
                "Butter Chicken", "Paneer Tikka", "Chicken Biryani", "Dal Makhani", "Tandoori Roti",
                "Mutton Rogan Josh", "Malai Kofta", "Chole Bhature", "Palak Paneer", "Vegetable Biryani",
                "Kadhai Chicken", "Jeera Rice", "Rajma Chawal", "Aloo Paratha", "Fish Curry",
                "Dosa", "Idli Sambhar", "Uttapam", "Masala Dosa", "Medu Vada"
        });

        menuData.put("Chinese", new String[]{
                "Hakka Noodles", "Chicken Manchurian", "Veg Spring Rolls", "Fried Rice", "Schezwan Noodles",
                "Chilli Chicken", "Hot and Sour Soup", "Crispy Corn", "Veg Manchurian", "Kung Pao Chicken",
                "Sweet and Sour Pork", "Prawn Crackers", "Dimsums", "Chow Mein", "Lemon Chicken",
                "Shanghai Tofu", "Chicken Lollipop", "Mongolian Lamb", "Beef Stir Fry", "Veg Fried Rice"
        });

        menuData.put("Italian", new String[]{
                "Margherita Pizza", "Pasta Alfredo", "Lasagna", "Pasta Arrabbiata", "Bruschetta",
                "Fettuccine Alfredo", "Tiramisu", "Minestrone Soup", "Pesto Pasta", "Garlic Bread",
                "Calzone", "Ravioli", "Gnocchi", "Caprese Salad", "Risotto",
                "Spaghetti Carbonara", "Chicken Parmesan", "Vegetarian Pizza", "Penne Alla Vodka", "Cannoli"
        });

        menuData.put("Mexican", new String[]{
                "Tacos", "Nachos", "Quesadillas", "Burritos", "Guacamole",
                "Enchiladas", "Fajitas", "Mexican Rice", "Chicken Mole", "Churros",
                "Tamales", "Elote (Corn)", "Pozole", "Tostadas", "Salsa Verde",
                "Carne Asada", "Pico de Gallo", "Shrimp Ceviche", "Chilaquiles", "Sopa de Lima"
        });

        menuData.put("Mughlai", new String[]{
                "Mutton Korma", "Chicken Korma", "Nihari", "Shahi Paneer", "Mughlai Paratha",
                "Keema Naan", "Chicken Rezala", "Mutton Seekh Kebab", "Badami Chicken", "Murgh Musallam",
                "Nargisi Kofta", "Beef Kebab", "Chicken Changezi", "Paneer Malai Tikka", "Raan",
                "Galouti Kebab", "Chicken Tikka", "Roomali Roti", "Saffron Rice", "Mughlai Biryani"
        });

        menuData.put("South Indian", new String[]{
                "Plain Dosa", "Masala Dosa", "Pesarattu", "Uttapam", "Medu Vada",
                "Idli Sambhar", "Neer Dosa", "Appam", "Puttu", "Rasam",
                "Avial", "Chettinad Chicken", "Kerala Fish Curry", "Mysore Pak", "Pongal",
                "Kuzhi Paniyaram", "Vazhaipoo Vadai", "Chicken 65", "Tomato Rasam", "Curd Rice"
        });

        menuData.put("Thai", new String[]{
                "Pad Thai", "Green Curry", "Red Curry", "Tom Yum Soup", "Papaya Salad",
                "Massaman Curry", "Thai Fried Rice", "Sticky Rice with Mango", "Thai Satay", "Panang Curry",
                "Thai Fish Cakes", "Spring Rolls", "Glass Noodle Salad", "Thai Chicken Wings", "Coconut Soup",
                "Thai Basil Chicken", "Pumpkin Curry", "Thai Dumplings", "Larb", "Fried Banana"
        });

        menuData.put("Japanese", new String[]{
                "Sushi", "Ramen", "Tempura", "Teriyaki Chicken", "Miso Soup",
                "Udon Noodles", "Takoyaki", "Katsu Curry", "Onigiri", "Sashimi",
                "Gyoza", "Okonomiyaki", "Yakitori", "Unagi", "Tonkatsu",
                "Matcha Ice Cream", "Mochi", "Tamagoyaki", "Shabu Shabu", "Soba Noodles"
        });

        menuData.put("Korean", new String[]{
                "Bibimbap", "Kimchi", "Bulgogi", "Japchae", "Samgyeopsal",
                "Tteokbokki", "Kimchi Fried Rice", "Sundubu Jjigae", "Jajangmyeon", "Dakgangjeong",
                "Gimbap", "Hotteok", "Haemul Pajeon", "Galbi", "Naengmyeon",
                "Soondae", "Banchan", "Soy Sauce Crab", "Mandu", "Yukgaejang"
        });

        menuData.put("American", new String[]{
                "Cheeseburger", "Hot Dog", "Fried Chicken", "BBQ Ribs", "Mac and Cheese",
                "Buffalo Wings", "Pancakes", "Apple Pie", "Grilled Cheese Sandwich", "Steak",
                "Mashed Potatoes", "Meatloaf", "Cobb Salad", "Pulled Pork Sandwich", "Clam Chowder",
                "Chicken Pot Pie", "Cornbread", "Chili", "Sloppy Joes", "Tuna Melt"
        });

        menuData.put("Continental", new String[]{
                "Grilled Chicken", "Roast Beef", "Baked Salmon", "Caesar Salad", "French Fries",
                "Beef Stroganoff", "Spaghetti Bolognese", "Chicken Cordon Bleu", "Vegetable Gratin", "Quiche Lorraine",
                "Sausages", "Hash Browns", "Fish and Chips", "Roast Turkey", "Shrimp Cocktail",
                "Grilled Vegetables", "Garlic Butter Shrimp", "Potato Wedges", "Chicken Kiev", "Pork Chops"
        });

        menuData.put("French", new String[]{
                "Croissant", "Quiche", "French Onion Soup", "Coq au Vin", "Ratatouille",
                "Bouillabaisse", "Beef Bourguignon", "Crème Brûlée", "Macaron", "Baguette",
                "Duck Confit", "Tarte Tatin", "Soufflé", "Cassoulet", "Nicoise Salad",
                "Escargots", "Pain Perdu", "Pot-au-feu", "Pissaladière", "Mousse au Chocolat"
        });

        menuData.put("Spanish", new String[]{
                "Paella", "Tortilla Española", "Churros", "Gazpacho", "Patatas Bravas",
                "Croquetas", "Pisto", "Pulpo a la Gallega", "Fabada", "Albóndigas",
                "Empanadas", "Tostada", "Jamón Ibérico", "Crema Catalana", "Calamares",
                "Bocadillo", "Escalivada", "Morcilla", "Salmorejo", "Gambas al Ajillo"
        });

        menuData.put("Turkish", new String[]{
                "Doner Kebab", "Lahmacun", "Pide", "Baklava", "Menemen",
                "Kofte", "Simit", "Iskender Kebab", "Manti", "Shish Kebab",
                "Borek", "Turkish Delight", "Gozleme", "Adana Kebab", "Mercimek Soup",
                "Imam Bayildi", "Kunefe", "Patlican Kebab", "Cig Kofte", "Hunkar Begendi"
        });

        menuData.put("Beverages", new String[]{
                "Coffee", "Tea", "Lemonade", "Mojito", "Orange Juice",
                "Mango Smoothie", "Iced Tea", "Cold Coffee", "Hot Chocolate", "Milkshake",
                "Fruit Punch", "Watermelon Juice", "Lassi", "Pina Colada", "Green Tea",
                "Cappuccino", "Latte", "Espresso", "Strawberry Shake", "Buttermilk"
        });

        for (Map.Entry<String, String[]> entry : menuData.entrySet()) {
            String category = entry.getKey();
            String[] items = entry.getValue();

            Map<String, Object> itemsMap = new HashMap<>();
            for (int i = 0; i < items.length; i++) {
                Map<String, Object> itemDetails = new HashMap<>();
                itemDetails.put("itemName", items[i]);
                itemDetails.put("price", 100 + (i * 10));  // random price
                itemsMap.put("Item" + (i + 1), itemDetails);
            }

            menuRef.child(category).setValue(itemsMap);
        }

        Log.d("DatabaseSeeder", "Menu Categories added successfully!");
    }
}