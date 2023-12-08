public class Core {
    public static void main(String[] args) {
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

        /*Main game loop */
        while(true){
            /*Draw the gameBoard */
            game.drawBoard(pc, player2);
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
    
}
