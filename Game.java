import java.util.Random;
public class Game {
    private Player turn;
    private Card[] gameDeck;

    public Game(){
        gameDeck = new Card[40];
        for(int i=0;i<gameDeck.length;i++){
            int color = 0;
            if(i<10)
                color = 1;
            else if(i>9 && i<20)
                color = 2;
            else if(i>19 && i<30)
                color = 3;
            else if(i>29 && i<40)
                color = 4;
            gameDeck[i] = new Card(color, false, i%10+1);
        }
    }

    public Card[] getGameDeck() {
        return gameDeck;
    }
    public void setTurn(Player turn) {
        this.turn = turn;
    }
    public Player getTurn() {
        return turn;
    }
    public void shuffleDeck(){
        Random r = new Random(System.currentTimeMillis());
        for(int i=0;i<gameDeck.length/2;i++){
            int n = r.nextInt(gameDeck.length);
            int k = r.nextInt(gameDeck.length);
            Card temp = gameDeck[n];
            gameDeck[n] = gameDeck[k];
            gameDeck[k] = temp;
        }
    }

}
