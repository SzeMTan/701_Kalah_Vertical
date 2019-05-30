package kalah;

import com.qualitascorpus.testsupport.IO;

import java.util.ArrayList;

public class Board {
    private IO _io;
    private ArrayList<Player> _playersList;


   public void setIO(IO io){
       this._io = io;
   }

    public ArrayList<Player> getPlayersList() {
        return _playersList;
    }

    public void setPlayersList(ArrayList<Player> players){
        _playersList = players;
    }

//    public void displayBoard(ArrayList<House> p1Houses, ArrayList<House> p2Houses,  Store p1Store, Store p2Store){
//        _io.println("+---------------+");
//        _io.print("| P2 | ");
//        for (int i = p2Houses.size(); i > 0; i-- ){
//            if (p2Houses.get(i-1).getSeeds() > 9) {
//                _io.print(i + "[" + p2Houses.get(i - 1).getSeeds() + "] | ");
//            } else {
//                _io.print(i + "[ " + p2Houses.get(i - 1).getSeeds() + "] | ");
//            }
//        }
//        if (p1Store.getSeeds() > 9){
//            _io.println("" + p1Store.getSeeds() + " |");
//        } else {
//            _io.println(" " + p1Store.getSeeds() + " |");
//        }
//
//        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
//        if (p2Store.getSeeds() > 9){
//            _io.print("| " + p2Store.getSeeds() + " | ");
//        } else {
//            _io.print("|  " + p2Store.getSeeds() + " | ");
//        }
//        for (int i = 0; i < p1Houses.size(); i++){
//            if (p1Houses.get(i).getSeeds() > 9) {
//                _io.print(i+1 + "[" + p1Houses.get(i).getSeeds() + "] | ");
//            } else {
//                _io.print(i+1 + "[ " + p1Houses.get(i).getSeeds() + "] | ");
//            }
//        }
//        _io.println("P1 |");
//        _io.println("+---------------+");
//    }

    public void displayBoard(ArrayList<House> p1Houses, ArrayList<House> p2Houses,  Store p1Store, Store p2Store){
        _io.println("+---------------+");
        if (p2Store.getSeeds() < 10) {
            _io.println("|       | P2  " + p2Store.getSeeds() + " |");
        }
        else {
            _io.println("|       | P2 " + p2Store.getSeeds() + " |");
        }
        _io.println("+-------+-------+");
        int j = p2Houses.size();
        for (int i = 0; i < p1Houses.size(); i++ ){
            if (p1Houses.get(i).getSeeds() < 10) {
                _io.print("| " + (i + 1) + "[ " + p1Houses.get(i).getSeeds() + "] ");
                printP2(p2Houses, j);
            } else {
                _io.print("| " + (i + 1) + "[" + p1Houses.get(i).getSeeds() + "] ");
                printP2(p2Houses, j);
            }
            j--;
        }
        _io.println("+-------+-------+");
        if (p1Store.getSeeds() < 10) {
            _io.println("| P1  " + p1Store.getSeeds() +" |       |");
        }
        else {
            _io.println("| P1 " + p1Store.getSeeds() +" |       |");
        }

        _io.println("+---------------+");
    }

    private void printP2(ArrayList<House> p2Houses, int j){
        if (p2Houses.get(j - 1).getSeeds() < 10){
            _io.println("| " + j + "[ " + p2Houses.get(j - 1).getSeeds() + "] |");
        }
        else {
            _io.println("| " + j + "[" + p2Houses.get(j - 1).getSeeds() + "] |");
        }
    }

    public int askPlayerInput (Player player, int numberOfHouses){
        int houseSelected;
        houseSelected = _io.readInteger("Player P" + player.getId() + "'s turn - Specify house number or 'q' to quit: ", 1, numberOfHouses, -1, "q");
        return houseSelected;
    }

    public void handleGameOver(GameState gameState, Player player1, Player player2){
        switch (gameState){
            case QUIT:
                printGameOverBoard();
                break;
            case GAME_OVER:
                printGameOverBoard();

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

    public void houseEmptyMessage() {
        _io.println("House is empty. Move again.");
    }

    public void printGameOverBoard(){
        _io.println("Game over");
        displayBoard(_playersList.get(0).getHouses(),_playersList.get(1).getHouses(), _playersList.get(0).getStore(),_playersList.get(1).getStore());
    }
}
