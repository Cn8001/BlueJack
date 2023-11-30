public class Player {
    private Card[] hand;
    private Card[] board;
    private Card[] deck;
    public final int HAND_NUM = 4;
    
    public Player(Card[] hand, Card[] board, Card[] deck){
        this.hand = hand;
        this.board = board;
        this.deck = deck;
    }

    /* This function is used for setting a single card inside a Card array, either hand,board or deck */
    public void setSingleCard(Card card, int index,Card[] d){
        d[index] = card;
    }
    public Card[] getHand() {
        return hand;
    }
    public Card[] getBoard() {
        return board;
    }
    public Card[] getDeck(){
        return deck;
    }
}
