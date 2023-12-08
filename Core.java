import java.util.InputMismatchException;
import java.util.Scanner;
public class Core {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /* Create the game */
        Game game = new Game();
        
        /*Create players and give them decks */
        Player pc = new Player(new Card[4], new Card[20],new Card[10]);
        Player player2 = new Player(new Card[4],new Card[20], new Card[10]);

        /*Set turn */
        game.setTurn(player2);

        /*Shuffle 
        Algorithm = Generate two random numbers
        set deck[n] to deck[n2]
        set deck[n2] to previous value of deck[n]
        Make it gamedeck.length/2 times
        */ 
        game.shuffleDeck(game.getGameDeck());

        /*Initialize decks of player and pc */ //IMPORTANT: Gamedeck contains null elements now.
        game.initializePlayerDeck(player2, true);
        game.initializePlayerDeck(pc, false);

        /* Initialize hands of player and pc */
        game.initializePlayerHand(player2);
        game.initializePlayerHand(pc);

        /*Draw the gameBoard */
            game.drawBoard(pc, player2);

        /*Start the game*/
        System.out.println("\nPress enter to start");
        sc.nextLine();

        /*Main game loop */
        int status = 0;
        while(true){
            if(game.checkBust(player2)){
                pc.incrementScore();
                if(pc.getScore() >= 3){
                    System.out.println("PC WIN");
                    break;
                }else{
                    System.out.println("\n\nPlayer busts\n\n\nPlayer: "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    sc.nextLine();
                    refresh(game,pc,player2);
                    continue;
                }
            }else if(game.checkBust(pc)){
                player2.incrementScore();
                if(player2.getScore() >= 3){
                    System.out.println("Player win");
                    break;
                }else{
                    System.out.println("\n\nPC busts\n\n\nPlayer: "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    sc.nextLine();
                    refresh(game,pc,player2);
                    continue;
                }
            }
            game.drawBoard(pc, player2);
            if(game.getTurn() == player2){
                status = playP2(game,sc,pc);
                if(status < 0){
                    System.out.println("You have entered a forbidden thing. Please restart the game.");
                    return;
                }

            }else{
                game.setTurn(player2);
            }
        }
        /* Test code
        for(int i=0;i<pc.getHand().length;i++){
            String color = "";
            String sign = "";
            switch(pc.getHand()[i].getColor()){
                case 1:
                    color = "Blue";
                break;
                case 2:
                    color = "Yellow";
                break;
                case 3:
                    color = "Red";
                break;
                case 4:
                    color = "Green";
                break;
                case 5:
                    color = "Special";
                break;
                default:
                    break;
            }
            switch(pc.getHand()[i].getSign()){
                case 1:
                    sign = "+";
                break;
                case 2:
                    sign ="-";
                break;
                default:
                    break;
            }
            System.out.printf("(%s) %s%d\n",color,sign,pc.getHand()[i].getValue());
        }
        */
    }
    public static void refresh(Game game,Player pc,Player p2){
        game.setTurn(p2);

        pc.setStanded(false);
        pc.refreshBoard();

        p2.setStanded(false);
        p2.refreshBoard();
        
    }
    //Return -1 in error
    public static int playP2(Game game,Scanner sc,Player pc){
        /*If the player is not standed,draw a card */
        if(!game.getTurn().isStanded()){
            int counter =0;
            for(Card c: game.getGameDeck()){
                /*If it is null continue with the new */
                if(c != null){
                    int place = 0;
                    /*get where to place the card */
                    for(int i=0;i<game.getTurn().getBoard().length; i++){
                        if(game.getTurn().getBoard()[i] == null){
                            place = i;
                            break;
                        }
                    }
                    game.getTurn().setSingleCard(c, place, game.getTurn().getBoard());
                    game.getGameDeck()[counter++] = null;
                    break;
                }else{
                    counter++;
                    continue;
                    
                }
            }
            int choice = 0;
            System.out.print("(1-)End\n(2-)Stand\n(3-play a card)\nPlease enter a choice > ");
            try{
                choice = sc.nextInt();
            }catch(InputMismatchException e){
                return -1;
            }
            switch(choice){
                /*End the turn */
                case 1:
                break;
                case 2:
                /* Stand*/
                    game.getTurn().setStanded(true);

            }
            game.setTurn(pc);
        }
        
        return 0;
    }
    
}
