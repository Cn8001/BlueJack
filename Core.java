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
        game.shuffleDeck();

        /*Initialize hands of player and pc */ //IMPORTANT: Gamedeck contains null elements now.
        game.initializePlayerDecks(player2, true);
        game.initializePlayerDecks(pc, false);

        for(int i=0;i<player2.getDeck().length;i++){
            System.out.printf("Color: %d\nSign: %d\nValue: %d\n\n",player2.getDeck()[i].getColor(),player2.getDeck()[i].getSign()
            ,player2.getDeck()[i].getValue());
        }

    }
}
