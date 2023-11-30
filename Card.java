import javax.imageio.plugins.tiff.GeoTIFFTagSet;

public class Card{
    private int color; // 1- Blue 2-Yellow 3-Red 4-Green 5-Special
    private boolean sign; // + -> false, - -> true
    private int value;

    public Card(int color, boolean sign, int value){
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
    public boolean getSign(){
        return sign;
    }
}