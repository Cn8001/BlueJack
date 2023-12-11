import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Core {
    public static void main(String[] args) {
        String playerName = new String();
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
        System.out.print("\nPlease enter your name >");
        try{
        if(sc.hasNext())
            playerName = sc.next().trim();
            sc.nextLine();
        }catch(Exception e){
            e.printStackTrace();
        }

        /*Main game loop */
        int status = 0;
        while(true){
            if(game.getTurn() == player2){



                //If turn is ours and we are standed, that means the game is over
                if(player2.isStanded()){
                    if(game.calculateSum(player2) == game.calculateSum(pc)){
                        game.drawBoard(pc, player2);
                        System.out.println("\n\nTIE\n");
                        refresh(game, pc, player2);
                        continue;
                    }else{
                        int distanceP2 = Math.abs(game.calculateSum(player2) - 20);
                        int distancePC = Math.abs(game.calculateSum(pc) - 20);
                        if(distanceP2 < distancePC){
                            player2.incrementScore();
                            if(player2.getScore() >= 3){
                                game.drawBoard(pc, player2);
                                System.out.println(playerName+" win");
                                break;
                            }else{
                                game.drawBoard(pc, player2);
                                System.out.println("\n\n"+playerName+" won that tour\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                                if(sc.hasNextLine()){
                                    sc.nextLine();
                                }
                                refresh(game,pc,player2);
                                continue;
                            }
                        }
                        else if(distancePC < distanceP2){
                            pc.incrementScore();
                            if(pc.getScore() >= 3){
                                game.drawBoard(pc, player2);
                                System.out.println("PC win");
                                break;
                            }else{
                                game.drawBoard(pc, player2);
                                System.out.println("\n\nPC won that tour\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                                if(sc.hasNextLine()){
                                    sc.nextLine();
                                }
                                refresh(game,pc,player2);
                                continue;
                            }
                        }
                    }
                }
                status = playP2(game,pc);
                if(status < 0){
                    System.out.println("You have entered a forbidden thing. Please restart the game.");
                    return;
                }

            }else{


                /*Check for stand,If turn is on pc and pc is standed, that means the game is over */
                if(pc.isStanded()){
                    if(game.calculateSum(player2) == game.calculateSum(pc)){
                        game.drawBoard(pc, player2);
                        System.out.println("\n\nTIE\n");
                        refresh(game, pc, player2);
                        continue;
                    }else{
                        int distanceP2 = Math.abs(game.calculateSum(player2) - 20);
                        int distancePC = Math.abs(game.calculateSum(pc) - 20);
                        if(distanceP2 < distancePC){
                            player2.incrementScore();
                            if(player2.getScore() >= 3){
                                game.drawBoard(pc, player2);
                                System.out.println(playerName+" win");
                                break;
                            }else{
                                game.drawBoard(pc, player2);
                                System.out.println("\n\n"+playerName+" won that tour\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                                if(sc.hasNextLine()){
                                    sc.nextLine();
                                }
                                refresh(game,pc,player2);
                                continue;
                            }
                        }
                        else if(distancePC < distanceP2){
                            pc.incrementScore();
                            if(pc.getScore() >= 3){
                                game.drawBoard(pc, player2);
                                System.out.println("PC win");
                                break;
                            }else{
                                game.drawBoard(pc, player2);
                                System.out.println("\n\nPC won that tour\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                                if(sc.hasNextLine()){
                                    sc.nextLine();
                                }
                                refresh(game,pc,player2);
                                continue;
                            }
                        }
                    }
                    game.setTurn(player2);
                }
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
                    System.out.println("\n\nPc won that round\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
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
                    System.out.println(playerName+" win, reached 20");
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\n"+playerName+" won that tour\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
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
                    System.out.println(playerName+" busts, PC WIN");
                    
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\n"+playerName+" busts\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
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
                    System.out.println("PC busts, "+playerName+" win");
                    break;
                }else{
                    game.drawBoard(pc, player2);
                    System.out.println("\n\nPC busts\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                    if(sc.hasNextLine()){
                        sc.nextLine();
                    }
                    refresh(game,pc,player2);
                    continue;
                }
            }
            /*Check for whether placed 9 cards*/
            if(player2.getBoard().length == 9){
                int plSum = game.calculateSum(player2);
                if(pc.getBoard().length != 9){
                    //Player wins
                    if((plSum-20)<=0){
                        player2.incrementScore();
                        game.drawBoard(pc, player2);
                        System.out.println("\n\n"+playerName+" won that tour.\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                        if(sc.hasNextLine()){
                            sc.nextLine();
                        }
                        refresh(game,pc,player2);
                        continue;
                    }
                }
            }
            if(pc.getBoard().length == 9){
                int pcSum = game.calculateSum(pc);
                if(player2.getBoard().length != 9){
                    //Player wins
                    if((pcSum-20)<=0){
                        pc.incrementScore();
                        game.drawBoard(pc, player2);
                        System.out.println("\n\nPC won that tour.\n\n\n"+playerName+": "+player2.getScore() + "\nPC: " + pc.getScore() + "\n\nPlease enter to continue...");
                        if(sc.hasNextLine()){
                            sc.nextLine();
                        }
                        refresh(game,pc,player2);
                        continue;
                    }
                }
            }
        }
        sc.close();
    }
    public static void refresh(Game game,Player pc,Player p2){
        game.setTurn(p2);

        pc.setStanded(false);
        pc.refreshBoard();
        pc.setPlayedACard(false);

        p2.setStanded(false);
        p2.refreshBoard();
        p2.setPlayedACard(false);
    }
    //Return -1 in error
    public static int playP2(Game game,Player pc){
        Scanner sc = new Scanner(System.in);
        /*If the player is not standed,draw a card */
        if(!game.getTurn().isStanded()){
            if(!game.getTurn().getPlayedACard()){
                addACard(game);
            }
            game.drawBoard(pc,game.getTurn());
            int choice = 0;
            System.out.print("(1-)End\n(2-)Stand\n(3-)play a card\nPlease enter a choice > ");
            try{
                choice = sc.nextInt();
                sc.nextLine();
            }catch(InputMismatchException e){
                sc.close();
                return -1;
            }
            switch(choice){
                /*End the turn */
                case 1:
                    game.getTurn().setPlayedACard(false);
                break;
                /* Stand */
                case 2:
                    game.getTurn().setStanded(true);
                    game.getTurn().setPlayedACard(false);
                    break;
                /* Throw a card */
                case 3:
                    game.throwCard(sc, game.getTurn());
                    game.getTurn().setPlayedACard(true);
                    break;
                default:
                break;

            }
            game.setTurn(pc);
        }
        
        return 0;
    }
    public static void writeFile(){

    }
    public static void addACard(Game game){
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
    }


    
}
