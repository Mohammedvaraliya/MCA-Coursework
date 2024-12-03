import javax.swing.*;

public class _18_ScrollPaneComboBoxExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JScrollPane & JComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(
                "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...\n"
                        + "This is a long text that will make the JScrollPane scroll.\nKeep adding more lines to see scroll bar...");

        JScrollPane scrollPane = new JScrollPane(textArea);

        String[] options = { "Option 1", "Option 2", "Option 3" };
        JComboBox<String> comboBox = new JComboBox<>(options);

        frame.add(scrollPane, "North");
        frame.add(comboBox, "South");

        frame.setSize(500, 400);
        frame.setVisible(true);
    }
}