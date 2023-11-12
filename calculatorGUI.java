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
        

        // 初始化?字按?
        numbers = new JButton[10]; 
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbers[i].addActionListener(this);
            numbers[i].setActionCommand(String.valueOf(i));
        }


        // 初始化操作符按?
        operators = new JButton[4];
        String[] ops = {"+", "-", "*", "/"};
        for (int i = 0; i < ops.length; i++) {
            operators[i] = new JButton(ops[i]);
            operators[i].addActionListener(this);
            operators[i].setActionCommand(ops[i]);
        }

        equalsButton = new JButton("="); // 創建等於按鈕
        equalsButton.addActionListener(this); // 為等於按鈕添加事件監聽器
        clearButton = new JButton("C"); // 創建清除按鈕
        clearButton.setActionCommand("C"); 
        clearButton.addActionListener(this); // 為清除按鈕添加事件監聽器

        // ?建布局并添加?件
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5)); // 設置為4行4列，每個元素之間間隔5像素

        // 將數字和運算符按鈕添加到面板中
        for (int i = 7; i <= 9; i++) {   // 添加數字7-9
            buttonPanel.add(numbers[i]);
        }
        buttonPanel.add(operators[3]);   // 添加 '/'

        // 第二行：數字4-6和乘法運算符
        for (int i = 4; i <= 6; i++) {
            buttonPanel.add(numbers[i]);
        }
        buttonPanel.add(operators[2]);   // 添加 '*'

        // 第三行：數字1-3和減法運算符
        for (int i = 1; i <= 3; i++) {
            buttonPanel.add(numbers[i]);
        }
        buttonPanel.add(operators[1]);   // 添加 '-'

        // 第四行：數字0、小數點、等於和加法運算符
        buttonPanel.add(numbers[0]);     // 添加 '0'
        buttonPanel.add(clearButton); 
        buttonPanel.add(equalsButton);   // 添加 '='
        buttonPanel.add(operators[0]); 


        // 將文本區域和按鈕面板添加到窗口中
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH); // 將顯示區域添加到窗口的上方
        this.add(buttonPanel, BorderLayout.CENTER);
        // 設置窗口的推薦大小
        this.setPreferredSize(new Dimension(300, 500)); // 這裡的 300 是寬度，400 是高度

        // 設置窗口的實際大小
        this.setSize(300, 500);

        // ?置窗口的?性
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // ?整窗口到合适大小
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
        // 如果是 Clear 按鈕，清空顯示區域
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
