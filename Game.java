import java.util.Random;
import java.util.Scanner;
public class Game {
    private Player turn;
    private Card[] gameDeck;
    public final int INIT_DECK_NUM = 5;
    

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
            gameDeck[i] = new Card(color, 1, i%10+1);
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
    public void shuffleDeck(Card[] deck){
        Random r = new Random(System.currentTimeMillis());
        for(int i=0;i<deck.length/2;i++){
            int n = r.nextInt(deck.length);
            int k = r.nextInt(deck.length);
            Card temp = deck[n];
            deck[n] = deck[k];
            deck[k] = temp;
        }
    }

    public void initializePlayerDeck(Player player, boolean reverse){
        int i=0;
        if(reverse){
            for(i=0;i<INIT_DECK_NUM;i++){
                player.setSingleCard(gameDeck[i],i,player.getDeck());
                gameDeck[i] = null;
            }

        }else{
            for(i=0;i<INIT_DECK_NUM;i++){
                player.setSingleCard(gameDeck[gameDeck.length-i-1],i,player.getDeck());
                gameDeck[gameDeck.length-i-1] = null;
            }
        }
        
        /*
        Three cards with random colors and values between 1 to 6, with an additional sign,
        that is either a plus (+) or a minus (-). These cards are either positive or negative, (sign 1 or 2)
        depending on the random sign.
        Color range 1-4 both included
        Sign range 1-2 both included
        Num range 1-6 both included
        */
        Random r = new Random(System.currentTimeMillis());
        for(int k=0;k<3;k++){
            player.setSingleCard(new Card(r.nextInt(4)+1,r.nextInt(2)+1,r.nextInt(6)+1),i++,player.getDeck());
        }
        /* The remaining two cards have an 80 percent chance of being a signed card. However,
        if the user is lucky (20 percent!), then: 
        Color range 1-4 both included
        Sign range 1-2 both included
        Num range 1-6 both included
        */
        for(int k=0;k<2;k++){
            int percent = r.nextInt(10)+1;
            //Percent <=8 this is a signed card
            if(percent <= 8){
                player.setSingleCard(new Card(r.nextInt(4)+1,r.nextInt(2)+1,r.nextInt(6)+1),i++,player.getDeck());
            }else{
                //Percent 9 -> flip (+/-) card, Percent 10 -> double (*2) card
                if(percent == 9){
                    player.setSingleCard(new Card(5,3,11), i++,player.getDeck());
                }else{
                    player.setSingleCard(new Card(5,3,12), i++,player.getDeck());
                }
            }
        }
    }

    public void initializePlayerHand(Player player){
        Card[] playerDeck = player.getDeck();
        Card[] playerHand = player.getHand();

        /* Shuffle the deck to get random 4 elements */
        shuffleDeck(playerDeck);

        /* Get random 4 elements from playerdeck to the playerhand*/
        System.arraycopy(playerDeck, 0, playerHand, 0, player.HAND_NUM);
    }


    //Returns true if p busted
    public boolean checkBust(Player p){
        return calculateSum(p) > 20;
    }

    //Calculates the sum of boarded cards and returns
    public int calculateSum(Player p){
        int sum = 0;
        for(int i=0;i<p.getBoard().length;i++){
            Card d = p.getBoard()[i];
            if(d != null){
                //If the card is negative
                if(d.getSign() == 2){
                    sum -= d.getValue();
                }else if(d.getSign() == 1){
                    sum += d.getValue();
                //If this card is a flip card
                }else if(d.getSign() == 3 && d.getValue() == 11){
                        if(p.getBoard().length >= i-1 && i-1 >= 0){
                            if(p.getBoard()[i-1] != null){
                                //if previous card is -
                                if(p.getBoard()[i-1].getSign() == 2){
                                    sum += p.getBoard()[i-1].getValue()*2;
                                //If previous card is +
                                }else if(p.getBoard()[i-1].getSign() == 1){
                                    sum -= p.getBoard()[i-1].getValue()*2;
                                }
                            }
                    }
                }
                /*If card is a double card */
                else if(d.getSign() == 3 && d.getValue() == 12){
                    if(p.getBoard().length >= i-1 && i-1 >= 0){
                        if(p.getBoard()[i-1] != null){
                            //If prev card is -
                            if(p.getBoard()[i-1].getSign() == 2){
                                sum -= p.getBoard()[i-1].getValue();
                            }
                            else if(p.getBoard()[i-1].getSign() == 1){
                                sum += p.getBoard()[i-1].getValue();
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    public void drawBoard(Player computer,Player p2){
        System.out.println("\n\n----------------------------------------\n");
        System.out.print("Computer hand:        ");
        Card[] computerHand = computer.getHand();
        Card[] playerHand = p2.getHand();
        Card[] computerBoard = computer.getBoard();
        Card[] playerBoard = p2.getBoard();
        for(int i=0;i<computerHand.length;i++){
            if(computerHand[i] == null){
                System.out.print("O ");
            }else{
                System.out.print("X ");
            }
        }
        System.out.println();
        System.out.print("Computer board:      ");
        if(computerBoard[0] == null){
            System.out.println("Empty");
        }else{
            for(Card c : computerBoard){
                if(c != null){
                    System.out.print(c.toString() + " ");
                }
            }
        }
        System.out.println("\n");
        System.out.print("Player board:        ");
        if(playerBoard[0] == null){
            System.out.println("Empty");
        }else{
            for(Card c : playerBoard){
                if(c != null){
                    System.out.print(" " +c.toString() + " ");
                }
            }
            System.out.println();
        }
        System.out.print("Player hand:          ");
        for(int i=0;i<playerHand.length;i++){
            if(playerHand[i] != null){
                System.out.print(playerHand[i].toString() + " ");
            }
        }
        System.out.println("\n\n\n----------------------------------------\n\n");
    }
    /*Return -1 on error */
    public int throwCard(Scanner sc,Player p){
        int c = p.selectACard(sc);
        int index;
        if(c<0){
            return -1;
        }
        for(index=0;index<p.getBoard().length;index++){
            if(p.getBoard()[index] == null)
                break;
        }
        /*Set the card which hand of the player null */
        p.setSingleCard(p.getHand()[c], index, p.getBoard());
        p.setSingleCard(null, c, p.getHand());

        return 0;
    }

    /*Return -1 on error */
    public int throwCardAsComputer(Player p,int c){
        int index;
        if(c<0){
            return -1;
        }
        for(index=0;index<p.getBoard().length;index++){
            if(p.getBoard()[index] == null)
                break;
        }
        /*Set the card which hand of the player null */
        p.setSingleCard(p.getHand()[c], index, p.getBoard());
        p.setSingleCard(null, c, p.getHand());

        return 0;
    }
    
}
