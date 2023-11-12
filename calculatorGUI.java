import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculatorGUI extends JFrame implements ActionListener {
    private final JTextArea display;
    private final calculator Calculator;
    private JButton[] numbers; 
    private JButton[] operators; 
    private JButton equalsButton; 
    private JButton clearButton;
 

    public calculatorGUI() {
        super("Calculator");
        Calculator = new calculator(); 
        display = new JTextArea(3,20);
        display.setEditable(true); 
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        

        // ��l��?�r��?
        numbers = new JButton[10]; 
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(this);
            numbers[i].setActionCommand(String.valueOf(i));
        }


        // ��l�ƾާ@�ū�?
        operators = new JButton[4];
        String[] ops = {"+", "-", "*", "/"};
        for (int i = 0; i < ops.length; i++) {
            operators[i] = new JButton(ops[i]);
            operators[i].addActionListener(this);
            operators[i].setActionCommand(ops[i]);
        }

        equalsButton = new JButton("="); // �Ыص�����s
        equalsButton.addActionListener(this); // ��������s�K�[�ƥ��ť��
        clearButton = new JButton("C"); // �ЫزM�����s
        clearButton.setActionCommand("C"); 
        clearButton.addActionListener(this); // ���M�����s�K�[�ƥ��ť��

        // ?�إ����}�K�[?��
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5)); // �]�m��4��4�C�A�C�Ӥ����������j5����

        // �N�Ʀr�M�B��ū��s�K�[�쭱�O��
        for (int i = 7; i <= 9; i++) {   // �K�[�Ʀr7-9
            buttonPanel.add(numbers[i]);
        }
        buttonPanel.add(operators[3]);   // �K�[ '/'

        // �ĤG��G�Ʀr4-6�M���k�B���
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(numbers[i]);
        }
        buttonPanel.add(operators[2]);   // �K�[ '*'

        // �ĤT��G�Ʀr1-3�M��k�B���
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(numbers[i]);
        }
        buttonPanel.add(operators[1]);   // �K�[ '-'

        // �ĥ|��G�Ʀr0�B�p���I�B����M�[�k�B���
        buttonPanel.add(numbers[0]);     // �K�[ '0'
        buttonPanel.add(clearButton); 
        buttonPanel.add(equalsButton);   // �K�[ '='
        buttonPanel.add(operators[0]); 


        // �N�奻�ϰ�M���s���O�K�[�쵡�f��
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH); // �N��ܰϰ�K�[�쵡�f���W��
        this.add(buttonPanel, BorderLayout.CENTER);
        // �]�m���f�����ˤj�p
        this.setPreferredSize(new Dimension(300, 500)); // �o�̪� 300 �O�e�סA400 �O����

        // �]�m���f����ڤj�p
        this.setSize(300, 500);

        // ?�m���f��?��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // ?�㵡�f��X��j�p
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    
    if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {

        display.append(command);

    } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {

        Calculator.setCurrentValue(Double.parseDouble(display.getText())); 
        Calculator.setCurrentOperation(command.charAt(0)); 
        display.setText(""); 

    }else if (command.equals("C")) {
        // �p�G�O Clear ���s�A�M����ܰϰ�
        display.setText("");

    } else if (command.equals("=")) {

        try {
            double result = Calculator.calculate(Double.parseDouble(display.getText()));
            display.setText(String.valueOf(result));
        } catch (NumberFormatException nfe) {
            display.setText("Error");
        }

        Calculator.reset();
        
    }
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new calculatorGUI();
            }
        });
     }
}
