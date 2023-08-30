package Java.Calculator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private JTextField input;
    private JTextField output;

    public CalculatorFrame() {
        super("Calculator");
        input = new JTextField();
        add(input, BorderLayout.NORTH);
        output = new JTextField();
        output.setEditable(false);
        add(output, BorderLayout.SOUTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(0, 3));
        for(int i = 1; i < 10; i++) {
            JButton b = new JButton(i + "");
            int x = i;
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    input.setText(input.getText() + x);
                }
            });
            buttons.add(b);
        }

        JButton zero = new JButton("0");
        zero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText(input.getText() + 0);
            }
        });
        
        JButton clear = new JButton("clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText("");
            }
        });
        
        JButton okay = new JButton("calc");  
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    calculate();
                } catch (InvalidOperationException e1) {
                    JOptionPane.showMessageDialog(okay, "Check Syntax!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(okay, "Check input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttons.add(clear);
        buttons.add(zero);
        buttons.add(okay);

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText(input.getText() + "+");
            }
        });

        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText(input.getText() + "-");
            }
        });

        JButton multiply = new JButton("*");
        multiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText(input.getText() + "*");
            }
        });

        JButton divide = new JButton("/");
        divide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setText(input.getText() + "/");
            }
        });

        JPanel operators = new JPanel();
        operators.setLayout(new GridLayout(0, 4));
        operators.add(plus);
        operators.add(minus);
        operators.add(multiply);
        operators.add(divide);
        add(operators, BorderLayout.EAST);

        add(buttons, BorderLayout.WEST);
        //setSize(400, 600);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void calculate() throws InvalidOperationException, NumberFormatException {
        String input = this.input.getText();
        Calculation c = new Calculation(input);
        c.calculate();
        double result = c.getResult();
        this.output.setText("Result: " + result);
    }
}
