import java.awt.*;
import java.awt.event.*;

public class _07_ButtonExample {
    public static void main(String[] args) {
        Frame frame = new Frame("Event Handling Example");
        Button button = new Button("Click Me");

        // Register the event listener
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        frame.add(button);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
