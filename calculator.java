public class calculator{
    private double currentValue = 0;
    private char currentOperation;

    public static double add(double num1, double num2){
        return num1 + num2;
    }

    public static double subtract(double num1, double num2){
        return num1 - num2;
    }

    public static double multiply(double num1, double num2){
        return num1 * num2;
    }

    public static double divide(double num1, double num2){
        return num2 != 0 ? num1 / num2 : 0;
    }

    public void  setCurrentValue(double value){
        this.currentValue = value;
    }

    public double calculate(double value){
        switch (currentOperation){
            case '+' : return add(currentValue,value);
            case '-' : return subtract(currentValue,value);
            case '*' : return multiply(currentValue,value);
            case '/' : return divide(currentValue,value);
            default: return value;
        }
    }

    public void setCurrentOperation(char operation){
        this.currentOperation = operation;
    }

    public void reset(){
        currentValue =0;
        currentOperation ='\0';
    }
}