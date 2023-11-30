public class Core {
    public static void main(String[] args) {
        /* Create the game */
        Game game = new Game();
        
        /*Create players and give them decks */
        Player pc = new Player(new Card[10], new Card[10]);
        Player player2 = new Player(new Card[10],new Card[10]);

        /*Set turn */
        game.setTurn(player2);

        /*Shuffle 
        Algorithm = Generate two random numbers
        set deck[n] to deck[n2]
        set deck[n2] to previous value of deck[n]
        Make it gamedeck.length/2 times
        */ 
        game.shuffleDeck();

    }
}
