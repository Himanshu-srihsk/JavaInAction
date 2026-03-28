package LowLevelDesign.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.initializeGame();
        System.out.println("game winner is: " + game.startGame());
    }
}
/*
     |      |      |
     |      |      |
     |      |      |
Player:Player1 Enter row,column: 1,1
     |      |      |
     | X    |      |
     |      |      |
Player:Player2 Enter row,column: 2,2
     |      |      |
     | X    |      |
     |      | O    |
Player:Player1 Enter row,column: 0,0
X    |      |      |
     | X    |      |
     |      | O    |
Player:Player2 Enter row,column: 0,1
X    | O    |      |
     | X    |      |
     |      | O    |
Player:Player1 Enter row,column: 2,0
X    | O    |      |
     | X    |      |
X    |      | O    |
Player:Player2 Enter row,column: 1,2
X    | O    |      |
     | X    | O    |
X    |      | O    |
Player:Player1 Enter row,column: 0,1
Incorredt possition chosen, try again
X    | O    |      |
     | X    | O    |
X    |      | O    |
Player:Player1 Enter row,column: 1,0
game winner is: Player1
 */