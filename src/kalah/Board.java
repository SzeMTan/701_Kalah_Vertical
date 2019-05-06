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
//        _io.println(" " + p1Store.getSeeds() + " |");
        if (p1Store.getSeeds() > 9){
            _io.println("" + p1Store.getSeeds() + " |");
        } else {
            _io.println(" " + p1Store.getSeeds() + " |");
        }

        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
//        _io.print("|  " + p2Store.getSeeds() + " | ");
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

}
