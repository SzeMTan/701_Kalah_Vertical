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

//		numHouseSeeds = p1Houses.get(houseNum-1).getSeeds();
//		p1Houses.get(houseNum-1).emptyHouse();
//		numStoreSeeds = p1Store.getSeeds();
//		while (numHouseSeeds > 0){
//
//		}
		sowSeeds(isPlayerOne, houseNum);
		board.displayBoard(p1Houses,p2Houses,p1Store,p2Store);
	}

	void setUp() {
		for (int i = 0; i < NUMBER_OF_HOUSES; i++){
			House house1 = new House();
			House house2 = new House();
			house1.setupHouse(INITIAL_SEEDS);
			house2.setupHouse(INITIAL_SEEDS);
			p1Houses.add(house1);
			p2Houses.add(house2);
		}
		p1Store = new Store();
		p1Store.setupStore(0);
		p2Store = new Store();
		p2Store.setupStore(0);
	}

	void sowSeeds(boolean isPlayerOne, int houseNum) {
		int houseSelected = houseNum - 1; // 0 indexed
		int houseSeeds = p1Houses.get(houseSelected).getSeeds(); // pickup seeds
		ArrayList<Pit> allPits = new ArrayList<>();
		p1Houses.get(houseSelected).emptyHouse(); // pickup seeds part 2

		// add everything
		allPits.addAll(p1Houses);
		allPits.add(p1Store);
		allPits.addAll(p2Houses);
		allPits.add(p2Store);

		// keep going until no more seeds
		while (houseSeeds > 0){
			if (houseNum < allPits.size()){ // still in list?
				allPits.get(houseNum).addSeeds(1);
			}
			else {
				houseNum = 0;
			}
			houseNum++;
			houseSeeds--;
		}
		if (allPits.get(houseNum) instanceof Store){

		}
	}
}
