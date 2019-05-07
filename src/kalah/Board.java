package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;

public class Board {
    IO _io;

   public void setIO(IO io){
       this._io = io;
   }
    void setupBoard(int numOfHouses, int numOfSeeds){
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        _io.print("| P2 | ");
        for (int i = numOfHouses; i > 0; i-- ){
            _io.print(i + "[ " + numOfSeeds + "] | ");
        }
        _io.println(" 0 |");
        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        _io.print("|  0 | ");
        for (int i = 0; i < numOfHouses; i++){
            _io.print((i + 1) + "[ " + numOfSeeds + "] | ");
        }
        _io.println("P1 |");
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    void displayBoard(ArrayList<House> p1Houses, ArrayList<House> p2Houses,  Store p1Store, Store p2Store){
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        _io.print("| P2 | ");
        for (int i = p2Houses.size(); i > 0; i-- ){
            if (p2Houses.get(i-1).getSeeds() > 9) {
                _io.print(i + "[" + p2Houses.get(i - 1).getSeeds() + "] | ");
            } else {
                _io.print(i + "[ " + p2Houses.get(i - 1).getSeeds() + "] | ");
            }
        }
        if (p1Store.getSeeds() > 9){
            _io.println("" + p1Store.getSeeds() + " |");
        } else {
            _io.println(" " + p1Store.getSeeds() + " |");
        }

        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        if (p2Store.getSeeds() > 9){
            _io.print("| " + p2Store.getSeeds() + " | ");
        } else {
            _io.print("|  " + p2Store.getSeeds() + " | ");
        }
        for (int i = 0; i < p1Houses.size(); i++){
            if (p1Houses.get(i).getSeeds() > 9) {
                _io.print(i+1 + "[" + p1Houses.get(i).getSeeds() + "] | ");
            } else {
                _io.print(i+1 + "[ " + p1Houses.get(i).getSeeds() + "] | ");
            }
        }
        _io.println("P1 |");
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    int askPlayerInput (Player player, int numberOfHouses){
        int houseSelected;
        houseSelected = _io.readInteger("Player P" + player.getId() + "'s turn - Specify house number or 'q' to quit: ", 1, numberOfHouses, -1, "q");
        return houseSelected;
    }

    void displayFinalState(GameState gameState, Player player1, Player player2){
        switch (gameState){
            case GAME_OVER:
                _io.println("Game over");
                displayBoard(player1.getHouses(),player2.getHouses(), player1.getStore(),player2.getStore());
                break;
            case END_GAME:
                _io.println("Game over");
                displayBoard(player1.getHouses(),player2.getHouses(), player1.getStore(),player2.getStore());
                int player1Score = player1.getFinalScore();
                int player2Score = player2.getFinalScore();
                _io.println("	player 1:" + player1Score);
                _io.println("	player 2:" + player2Score);
                if (player1Score > player2Score){
                    _io.println("Player 1 wins!");
                } else if (player1Score < player2Score){
                    _io.println("Player 2 wins!");
                } else {
                    _io.println("A tie!");
                }
                break;
        }
    }
}
