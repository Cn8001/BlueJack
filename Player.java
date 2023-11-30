public class Player {
    private Card[] hand;
    private Card[] board;
    public Player(Card[] hand, Card[] board){
        this.hand = hand;
        this.board = board;
    }

    public void setSingleCard(Card card, int index){
        hand[index] = card;
    }
    public Card[] getHand() {
        return hand;
    }
    public Card[] getBoard() {
        return board;
    }
}
