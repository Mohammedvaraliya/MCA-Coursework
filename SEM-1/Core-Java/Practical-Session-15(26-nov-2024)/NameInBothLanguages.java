import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NameInBothLanguages extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create a text element for your name in English
        Text englishText = new Text("Mohammed Varaliya");
        englishText.setFont(Font.font("Verdana", 50)); // Set font and size
        englishText.setFill(Color.DARKBLUE); // Text color
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.GRAY);
        englishText.setEffect(dropShadow); // Add shadow effect for styling

        // Create a text element for your name in Hindi
        Text hindiText = new Text("आपका नाम"); // Replace with your Hindi name
        hindiText.setFont(Font.font("Mangal", 50)); // Mangal font supports Hindi characters
        hindiText.setFill(Color.DARKRED); // Hindi text color
        hindiText.setEffect(dropShadow); // Apply the same shadow effect

        // Use a StackPane layout to stack the text elements
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #f0f8ff;"); // Light background color
        root.getChildren().addAll(englishText, hindiText);

        // Create the scene
        Scene scene = new Scene(root, 600, 400);

        // Set the title and scene for the stage (window)
        primaryStage.setTitle("Name in Both Languages");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}