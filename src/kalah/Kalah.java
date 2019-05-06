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
	int numHouseSeeds;
	int numStoreSeeds;
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



		int houseNum = io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ", 1, NUMBER_OF_HOUSES,-1, "q" );

		if (houseNum == -1){
			io.println("Game over");
			board.displayBoard(p1Houses,p2Houses,p1Store,p2Store);
		}

		numHouseSeeds = p1Houses.get(houseNum-1).getSeeds();
		p1Houses.get(houseNum-1).emptyHouse();
		numStoreSeeds = p1Store.getSeeds();
		makePlayerPits();
		while (numHouseSeeds > 0){

		}
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

	int getSeedsFromHouse(int selectedHouse){
		if(isPlayerOne){
			return p1Houses.get(selectedHouse).getSeeds();
		}
		else {
			return p2Houses.get(selectedHouse).getSeeds();
		}
	}

	void makePlayerPits(){
		p1Pits.addAll(p1Houses);
		p1Pits.add(p1Store);
		p2Pits.addAll(p2Houses);
		p2Pits.add(p2Store);
	}
}
