public class Player {
    private Card[] hand;
    private Card[] board;
    private Card[] deck;
    public Player(Card[] hand, Card[] board, Card[] deck){
        this.hand = hand;
        this.board = board;
        this.deck = deck;
    }

    public void setSingleCard(Card card, int index){
        deck[index] = card;
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
