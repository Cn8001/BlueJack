import java.util.Scanner;

public class Computer {
    private Game game;
    private Player player;
    private Player self;
    private int choice=0;
    private boolean isPlayed;
    private Scanner sc;
    public Computer(Scanner sc,Game game, Player self, Player opponent){
        this.game = game;
        this.self = self;
        this.player = opponent;
        this.isPlayed = false;
        this.sc = sc;
    }

    public int play(){
        if(!self.isStanded()){
            /*Think */
            //If we are going to bust
            if(game.calculateSum(self) > 20){
                for(int i=0;i<self.getHand().length;i++){
                    Card c = self.getHand()[i];
                    if(c != null){
                        if(c.getSign() == 2 && !isPlayed){
                            choice = 3;
                            game.throwCardAsComputer(self,i);
                            self.setPlayedACard(true);
                            game.setTurn(player);
                            isPlayed = true;
                            System.out.println("\nComputer played a card.");
                        }
                    }
                }
            }
            //Choose to stand
            else if(game.calculateSum(self) >= 17 && !isPlayed){
                self.setStanded(true);
                self.setPlayedACard(false);
                game.setTurn(player);
                isPlayed = true;
                System.out.println("\nComputer chose to stand.");
            }
            //Choose to throw +
            else if(game.calculateSum(self) < 10){
                choice = 3;
                for(int i=0;i<self.getHand().length;i++){
                    Card c = self.getHand()[i];
                    if(c != null){
                        if(c.getSign() != 2 && !isPlayed){
                            choice = 3;
                            game.throwCardAsComputer(self,i);
                            self.setPlayedACard(true);
                            game.setTurn(player);
                            isPlayed = true;
                            System.out.println("\nComputer played a card.");
                        }
                    }
                }
            }
            //If computer didnt make any movement, it choose to end.
            if(!isPlayed){
                choice = 1;
                self.setPlayedACard(false);
                game.setTurn(player);
                isPlayed = false;
                System.out.println("\nComputer ended the turn.");
            }
            
            /*End thinking */
            if(!self.getPlayedACard()){
                Core.addACard(game,self);
            }
        }
        return choice;
    }

}
