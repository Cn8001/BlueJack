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
            if(game.getTurn() == player2){
                status = playP2(game,pc);
                if(status < 0){
                    System.out.println("You have entered a forbidden thing. Please restart the game.");
                    return;
                }

            }else{
                game.setTurn(player2);
            }

            /*Check for reaching 20 */
            if(game.calculateSum(pc) == 20){
                pc.incrementScore();
                if(pc.getScore() >= 3){
                    game.drawBoard(pc, player2);
                    System.out.println("PC win, reached 20");
                    
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\nPc won that round\n\n\nPlayer: "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    if(sc.hasNextLine()){
                        sc.nextLine();
                    }
                    refresh(game,pc,player2);
                    continue;
                }
            }else if(game.calculateSum(player2) == 20){
                player2.incrementScore();
                if(player2.getScore() >= 3){
                    game.drawBoard(pc, player2);
                    System.out.println("Player win, reached 20");
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\nPlayer won that tour\n\n\nPlayer: "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    if(sc.hasNextLine()){
                        sc.nextLine();
                    }
                    refresh(game,pc,player2);
                    continue;
                }
            }



            /*Check for busts */
            if(game.checkBust(player2)){
                pc.incrementScore();
                if(pc.getScore() >= 3){
                    game.drawBoard(pc, player2);
                    System.out.println("Player busts, PC WIN");
                    
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\nPlayer busts\n\n\nPlayer: "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    if(sc.hasNextLine()){
                        sc.nextLine();
                    }
                    refresh(game,pc,player2);
                    continue;
                }
            }else if(game.checkBust(pc)){
                player2.incrementScore();
                if(player2.getScore() >= 3){
                    game.drawBoard(pc, player2);
                    System.out.println("PC busts, player win");
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\nPC busts\n\n\nPlayer: "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    if(sc.hasNextLine()){
                        sc.nextLine();
                    }
                    refresh(game,pc,player2);
                    continue;
                }
            }
        }
    }
    public static void refresh(Game game,Player pc,Player p2){
        game.setTurn(p2);

        pc.setStanded(false);
        pc.refreshBoard();

        p2.setStanded(false);
        p2.refreshBoard();
        
    }
    //Return -1 in error
    public static int playP2(Game game,Player pc){
        Scanner sc = new Scanner(System.in);
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
            game.drawBoard(pc,game.getTurn());
            int choice = 0;
            System.out.print("(1-)End\n(2-)Stand\n(3-)play a card\nPlease enter a choice > ");
            try{
                choice = sc.nextInt();
            }catch(InputMismatchException e){
                return -1;
            }
            switch(choice){
                /*End the turn */
                case 1:
                break;
                /* Stand */
                case 2:
                    game.getTurn().setStanded(true);
                default:
                break;

            }
            game.setTurn(pc);
        }
        
        return 0;
    }
    
}
