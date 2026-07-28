import java.awt.*;
import javax.swing.*;

public class CalculatorGUI extends JFrame {

    private JTextField display;

    private double firstNumber = 0;
    private String operator = "";

    Calculator calculator = new Calculator();
    UnitConverter converter = new UnitConverter();

    public CalculatorGUI() {

        setTitle("Calculator & Temperature Converter");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {

            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));

            button.addActionListener(e -> {

                switch (text) {

                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":

                        display.setText(display.getText() + text);
                        break;

                    case "+":

                    case "-":

                    case "*":

                    case "/":

                        if (!display.getText().isEmpty()) {

                            firstNumber = Double.parseDouble(display.getText());
                            operator = text;
                            display.setText("");

                        }

                        break;

                    case "=":

                        if (display.getText().isEmpty())
                            return;

                        double secondNumber = Double.parseDouble(display.getText());

                        try {

                            double result = 0;

                            switch (operator) {

                                case "+":
                                    result = calculator.add(firstNumber, secondNumber);
                                    break;

                                case "-":
                                    result = calculator.subtract(firstNumber, secondNumber);
                                    break;

                                case "*":
                                    result = calculator.multiply(firstNumber, secondNumber);
                                    break;

                                case "/":
                                    result = calculator.divide(firstNumber, secondNumber);
                                    break;

                            }

                            display.setText(String.valueOf(result));

                        } catch (ArithmeticException ex) {

                            display.setText(ex.getMessage());

                        }

                        break;

                    case "C":

                        display.setText("");
                        firstNumber = 0;
                        operator = "";

                        break;
                }

            });

            panel.add(button);

        }

        add(panel, BorderLayout.CENTER);

        JPanel converterPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField tempInput = new JTextField();

        JButton cToF = new JButton("C → F");

        JButton fToC = new JButton("F → C");

        JLabel result = new JLabel("Result");

        converterPanel.setBorder(BorderFactory.createTitledBorder("Temperature Converter"));

        converterPanel.add(new JLabel("Temperature"));
        converterPanel.add(tempInput);

        converterPanel.add(cToF);
        converterPanel.add(fToC);

        converterPanel.add(new JLabel("Output"));
        converterPanel.add(result);

        cToF.addActionListener(e -> {

            try {

                double c = Double.parseDouble(tempInput.getText());

                result.setText(converter.celsiusToFahrenheit(c) + " °F");

            } catch (Exception ex) {

                result.setText("Invalid Input");

            }

        });

        fToC.addActionListener(e -> {

            try {

                double f = Double.parseDouble(tempInput.getText());

                result.setText(converter.fahrenheitToCelsius(f) + " °C");

            } catch (Exception ex) {

                result.setText("Invalid Input");

            }

        });

        add(converterPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

}