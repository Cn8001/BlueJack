import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private Card[] hand;
    private Card[] board;
    private Card[] deck;
    private Card[] playedCards;
    private int score;
    private boolean standed;
    private boolean playedACard;
    public final int HAND_NUM = 4;
    
    public Player(Card[] hand, Card[] board, Card[] deck){
        this.hand = hand;
        this.board = board;
        this.deck = deck;
        this.score = 0;
        this.playedCards = new Card[40];
        this.standed = false;
        this.playedACard = false;
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
    public boolean getPlayedACard(){
        return this.playedACard;
    }
    public void setPlayedACard(boolean val){
        this.playedACard = val;
    }

    /*This function returns playerhand index */
    public int selectACard(Scanner sc){
        int j=1;
        int choice=0;
        int[] temp2 = new int[hand.length];
        for(int i=0;i<hand.length;i++){
            if(hand[i] != null){
                System.out.println("("+j+")- " + hand[i].toString());
                temp2[j-1] = i;
                j++;
            }
        }
        System.out.print("Please choose a card >");
        try{
            choice = sc.nextInt();
        }catch(InputMismatchException e){
            return -1;
        }
        if(choice > 0 && choice < j){
            choice--; //Indexes start from 0
            return temp2[choice];
        }
        return -1;
    }
}
