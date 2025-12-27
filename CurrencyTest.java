import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

public class CurrencyTest {

    // UI Components
    private static JTextField fromField, toField;
    private static JComboBox<String> fromCurrency, toCurrency;

    // Exchange rates (with respect to USD)
    private static HashMap<String, Double> rates = new HashMap<>();

    public static void main(String[] args) {

        // ----------- Exchange Rates -----------
        addRate("United States (USD)", 1.0);
        addRate("Eurozone (EUR)", 0.85);
        addRate("India (INR)", 90.40);
        addRate("United Kingdom (GBP)", 0.75);
        addRate("Australia (AUD)", 1.51);
        addRate("Canada (CAD)", 1.38);
        addRate("United Arab Emirates (AED)", 3.67);
        addRate("Haiti (HTG)", 130.90);
        addRate("Japan (JPY)", 155.60);
        addRate("Nepal (NPR)", 144.61);
        addRate("Bangladesh (BDT)", 122.12);

        // ----------- Frame -----------
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(null);
        panel.setBackground(Color.LIGHT_GRAY);
        frame.add(panel);

        // ----------- Title -----------
        JLabel title = createLabel("Currency Converter", 28, 220, 30, 300, 40);
        panel.add(title);

        // ----------- From Section -----------
        panel.add(createLabel("From Currency Of", 16, 80, 100, 200, 25));

        fromCurrency = createComboBox(80, 130);
        panel.add(fromCurrency);

        fromField = createTextField(80, 180, true);
        panel.add(fromField);

        // ----------- To Section -----------
        panel.add(createLabel("To Currency Of", 16, 420, 100, 200, 25));

        toCurrency = createComboBox(420, 130);
        panel.add(toCurrency);

        toField = createTextField(420, 180, false);
        panel.add(toField);

        // ----------- Buttons -----------
        JButton convertBtn = new JButton("Convert");
        JButton resetBtn = new JButton("Reset");
        JButton exitBtn = new JButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 300, 750, 60);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        buttonPanel.add(convertBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(exitBtn);
        panel.add(buttonPanel);

        // ----------- Convert Logic -----------
        convertBtn.addActionListener(e -> convertCurrency(frame));

        // ----------- Reset Logic -----------
        resetBtn.addActionListener(e -> {
            fromField.setText("");
            toField.setText("");
        });

        // ----------- Exit Logic -----------
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // ================= Helper Methods =================

    private static void addRate(String currency, double value) {
        rates.put(currency, value);
    }

    private static JLabel createLabel(String text, int size, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, size));
        label.setBounds(x, y, w, h);
        return label;
    }

    private static JComboBox<String> createComboBox(int x, int y) {
        String[] currencies = rates.keySet().toArray(new String[0]);
        JComboBox<String> box = new JComboBox<>(currencies);
        box.setBounds(x, y, 220, 30);
        return box;
    }

    private static JTextField createTextField(int x, int y, boolean editable) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 220, 30);
        field.setEditable(editable);
        return field;
    }

    private static void convertCurrency(JFrame frame) {
        try {
            double amount = Double.parseDouble(fromField.getText());

            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            double result = (amount / rates.get(from)) * rates.get(to);

            toField.setText(String.format("%.2f", result));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Please enter a valid number",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
