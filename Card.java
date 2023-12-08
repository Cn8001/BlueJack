public class Card{
    private int color; // 1- Blue 2-Yellow 3-Red 4-Green 5-Special
    private int sign; // + -> 1, - -> 2, Special -> 3;
    private int value; // 11 -> flip, 12 -> double

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Card(int color, int sign, int value){
        this.color = color;
        this.sign = sign;
        this.value = value;
    }

    public int getColor() {
        return color;
    }
    public int getValue() {
        return value;
    }
    public int getSign(){
        return sign;
    }

    public String toString() {
        String sgn;
        String clr;
        String num;
        switch(sign){
            case 1:
                sgn = "+";
            break;
            case 2:
                sgn = "-";
            break;
            default:
                sgn = "";
            break;
        }
        switch(color){
            case 1:
                clr = ANSI_BLUE;
            break;
            case 2:
                clr = ANSI_YELLOW;
                break;
            case 3:
                clr = ANSI_RED;
            break;
            case 4:
                clr = ANSI_GREEN;
            break;
            default:
                clr = ANSI_BLACK;
            break;
        }
        if(value <= 10){
            num = Integer.toString(value); 
        }
        else if(value == 11){
            num = "+/-";
        }
        else if(value==12){
            num = "x2";
        }else{
            num = "";
        }
        return clr + sgn  + num + ANSI_RESET;
    }
}