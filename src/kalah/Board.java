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

    void displayBoard(ArrayList p1Houses, ArrayList p2Houses,  Store p1Store, Store p2Store){
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        _io.print("| P2 | ");
        for (int i = p2Houses.size(); i > 0; i-- ){
            _io.print(i + "[ " + p2Houses.size() + "] | ");
        }
        _io.println(" " + p2Store.getSeeds() + " |");
        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        _io.print("|  " + p1Store.getSeeds() + " | ");
        for (int i = 0; i < p1Houses.size(); i++){
            _io.print((i + 1) + "[ " + p1Houses.size() + "] | ");
        }
        _io.println("P1 |");
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

}
