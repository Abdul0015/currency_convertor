import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Currency_Converter extends JFrame {
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR"};
    private double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14};

    public Currency_Converter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Amount Label and Field
        amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(amountLabel, gbc);

        amountField = new JTextField();
        gbc.gridx = 1;
        add(amountField, gbc);

        // From Label and ComboBox
        fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromLabel, gbc);

        fromComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        add(fromComboBox, gbc);

        // To Label and ComboBox
        toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toLabel, gbc);

        toComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        add(toComboBox, gbc);

        // Convert Button
        convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(70, 130, 180));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(convertButton, gbc);

        // Result Label
        resultLabel = new JLabel("Converted amount will appear here");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setForeground(new Color(34, 139, 34));
        gbc.gridy = 4;
        add(resultLabel, gbc);

        // Convert Button Action Listener
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountText = amountField.getText();
                if (amountText.isEmpty()) {
                    resultLabel.setText("Please enter an amount");
                    return;
                }

                try {
                    double amount = Double.parseDouble(amountText);
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid amount format");
                }
            }
        });

        // Set background color
        getContentPane().setBackground(new Color(240, 248, 255));
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Currency_Converter().setVisible(true));
    }
}
