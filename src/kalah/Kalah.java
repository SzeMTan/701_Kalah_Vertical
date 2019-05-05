package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.ArrayList;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	int NUMBER_OF_HOUSES = 6;
	int INITIAL_SEEDS = 5;
	boolean isPlayerOne = true;
	ArrayList<House> p1Houses = new ArrayList<>();
	ArrayList<House> p2Houses = new ArrayList<>();
	ArrayList<Pit> p1Pits = new ArrayList<>();
	ArrayList<Pit> p2Pits = new ArrayList<>();
	Store p1Store;
	Store p2Store;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		Board board = new Board();
		board.setIO(io);
		setUp();
		//board.setupBoard(NUMBER_OF_HOUSES, INITIAL_SEEDS);
		board.displayBoard(p1Houses,p2Houses,p1Store,p2Store);



		int houseNum = io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ", 0, NUMBER_OF_HOUSES,-1, "q" );

		if (houseNum == -1){
			io.println("Game over");
		}
		//board.setupBoard(NUMBER_OF_HOUSES, INITIAL_SEEDS);
		board.displayBoard(p1Houses,p2Houses,p1Store,p2Store);
		//displayBoard(io);


	}
	void displayBoard(IO io){
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	//	io.println("Player P1's turn - Specify house number or 'q' to quit: ");
	}

	void setUp() {
		for (int i = 0; i < NUMBER_OF_HOUSES; i++){
			House house = new House();
			house.setupHouse(INITIAL_SEEDS);
			p1Houses.add(house);
			p2Houses.add(house);
		}
		p1Store = new Store();
		p1Store.setupStore(0);
		p2Store = new Store();
		p2Store.setupStore(0);
	}
}
