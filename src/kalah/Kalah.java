package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.ArrayList;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */

enum GameState{
	GAME_OVER,
	END_GAME
}
public class Kalah {

	int NUMBER_OF_HOUSES = 6;
	int INITIAL_SEEDS = 4;
	int houseNum = 0;
	Pit lastPit;
	Player player1 = new Player();
	Player player2 = new Player();
	Player currentPlayer = player1;
	Player otherPlayer = player2;
	GameState gameState;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		Board board = new Board();
		board.setIO(io);
		setUp();

		while (true) {
			board.displayBoard(player1._houses,player2._houses, player1._store,player2._store);

			// check if houses of the current player is empty
			if (checkifHousesAreEmpty()){
				gameState = GameState.END_GAME;
				break;
			}
			if (currentPlayer == player1){
				houseNum = io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ", 1, NUMBER_OF_HOUSES, -1, "q");
			} else {
				houseNum = io.readInteger("Player P2's turn - Specify house number or 'q' to quit: ", 1, NUMBER_OF_HOUSES, -1, "q");
			}

			if (houseNum == -1){
				gameState = GameState.GAME_OVER;
				break;
			}
			if (currentPlayer._houses.get(houseNum-1)._seeds == 0){
				io.println("House is empty. Move again.");
			}
			else{
				playGame(board, io);
			}
		}

		switch (gameState){
			case GAME_OVER:
				io.println("Game over");
				board.displayBoard(player1._houses,player2._houses, player1._store,player2._store);
				break;
			case END_GAME:
				io.println("Game over");
				board.displayBoard(player1._houses,player2._houses, player1._store,player2._store);
				int player1Score = player1.getFinalScore();
				int player2Score = player2.getFinalScore();
				io.println("	player 1:" + player1Score);
				io.println("	player 2:" + player2Score);
				if (player1Score > player2Score){
					io.println("Player 1 wins!");
				} else if (player1Score < player2Score){
					io.println("Player 2 wins!");
				} else {
					io.println("A tie!");
				}
				break;
		}

	}

	void setUp() {

		ArrayList<Player> playerList = new ArrayList<>();
		playerList.add(player1);
		playerList.add(player2);

//		ArrayList<House> p1Houses = new ArrayList<>();
//		ArrayList<House> p2Houses = new ArrayList<>();
//		for (int i = 0; i < NUMBER_OF_HOUSES; i++){
//			House house1 = new House();
//			House house2 = new House();
//			house1.setupHouse(INITIAL_SEEDS);
//			house2.setupHouse(INITIAL_SEEDS);
//			p1Houses.add(house1);
//			p2Houses.add(house2);
//		}

		for (Player player : playerList){
			ArrayList<House> houses = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_HOUSES; i++){
			House house = new House();
			house.setupHouse(INITIAL_SEEDS);
			houses.add(house);
			}
			player.setHouses(houses);
		}
		Store p1Store = new Store();
		p1Store.setupStore(0);
		player1.setStore(p1Store);
		//player1.setHouses(p1Houses);
		Store p2Store = new Store();
		p2Store.setupStore(0);
		player2.setStore(p2Store);
		//player2.setHouses(p2Houses);


	}

	void sowSeeds(Player currentPlayer, int houseNum,IO io) {
		int houseSelected = houseNum - 1; // 0 indexed
		int houseSeeds;
		ArrayList<Pit> allPits = new ArrayList<>();

		// add everything
		if (currentPlayer == player1) {
			houseSeeds = player1._houses.get(houseSelected).getSeeds(); // pickup seeds
			player1._houses.get(houseSelected).emptyHouse(); // pickup seeds part 2
			allPits.addAll(player1._houses);
			allPits.add(player1._store);
			allPits.addAll(player2._houses);
		} else {
			houseSeeds = player2._houses.get(houseSelected).getSeeds(); // pickup seeds
			player2._houses.get(houseSelected).emptyHouse(); // pickup seeds part 2
			allPits.addAll(player2._houses);
			allPits.add(player2._store);
			allPits.addAll(player1._houses);
		}
		// keep going until no more seeds
		while (houseSeeds > 0){
			if (houseNum < allPits.size()){ // still in list?
				allPits.get(houseNum).addSeeds(1);
			}
			else {
				houseNum = 0;
				allPits.get(houseNum).addSeeds(1);
			}
			houseNum++;
			houseSeeds--;
		}
		lastPit = allPits.get(houseNum-1);

	}

	void playGame(Board board, IO io){
		sowSeeds(currentPlayer, houseNum,io);
//		board.displayBoard(player1._houses,player2._houses, player1._store,player2._store);

		if (!(lastPit instanceof Store)) {
			// lands in the opposite players house
			if (!currentPlayer._houses.contains(lastPit)){
				// set opposite player
				setNewPlayer(getOppositePlayer());
			} else { //lands in own house
				if((lastPit._seeds != 1)) { // house contains other seeds
					setNewPlayer(getOppositePlayer());
				} else { // house was empty
					// check if opponents house has seeds
					int oppHouseIndex = ((NUMBER_OF_HOUSES - 1) - currentPlayer._houses.indexOf(lastPit));
					Player oppositePlayer = getOppositePlayer();
					int oppositeHouseSeeds = oppositePlayer._houses.get(oppHouseIndex)._seeds;

					if (oppositeHouseSeeds > 0){
						// add own seed to store
						currentPlayer._store.addSeeds(1);
						((House) lastPit).emptyHouse();

						currentPlayer._store.addSeeds(oppositeHouseSeeds);
						oppositePlayer._houses.get(oppHouseIndex).emptyHouse();
					}

					//swap player
					setNewPlayer(getOppositePlayer());
				}
			}
		}
	}

	void setNewPlayer(Player player){
		currentPlayer = player;
	}

	Player getOppositePlayer(){
		if (currentPlayer == player1){
			return player2;
		} else {
			return player1;
		}
	}

	boolean checkifHousesAreEmpty(){
		boolean isEmpty = false;
		int allSeeds = 0;
		for (House h: currentPlayer._houses){
			allSeeds += h.getSeeds();
		}
		if (allSeeds == 0){
			isEmpty = true;
		}
		return isEmpty;
	}
}
