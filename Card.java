public class Card{
    private int color; // 1- Blue 2-Yellow 3-Red 4-Green 5-Special
    private int sign; // + -> 1, - -> 2, Special -> 3;
    private int value; // 11 -> flip, 12 -> double

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
}