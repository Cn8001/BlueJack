public class Player {
    private Card[] hand;
    private Card[] board;
    private Card[] deck;
    private Card[] playedCards;
    private int score;
    private boolean standed;
    public final int HAND_NUM = 4;
    
    public Player(Card[] hand, Card[] board, Card[] deck){
        this.hand = hand;
        this.board = board;
        this.deck = deck;
        this.score = 0;
        this.playedCards = new Card[40];
        this.standed = false;
    }

    /* This function is used for setting a single card inside a Card array, either hand,board or deck */
    public void setSingleCard(Card card, int index,Card[] d){
        d[index] = card;
    }
    public void refreshBoard(){
        for(int i=0;i<board.length;i++){
            board[i] = null;
        }
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
    public int getScore(){
        return score;
    }
    public void incrementScore(){
        score++;
    }
    public Card[] getPlayedCards(){
        return playedCards;
    }
    public boolean isStanded(){
        return standed;
    }
    public void setStanded(boolean val){
        standed = val;
    }
}
