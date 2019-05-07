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
	END_GAME,
	EMPTY_HOUSE,
	PLAY_GAME
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
	GameState gameState = GameState.PLAY_GAME;
	ArrayList<Player> playerList;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		Board board = new Board();
		board.setIO(io);
		setUp();
		board.setPlayersList(playerList);

		while (true) {
			board.displayBoard(player1.getHouses(),player2.getHouses(), player1.getStore(),player2.getStore());

			// check if houses of the current player is empty
			if (currentPlayer.checkifHousesAreEmpty()){
				gameState = GameState.END_GAME;
				break;
			}
			houseNum = board.askPlayerInput(currentPlayer, NUMBER_OF_HOUSES);

			if (houseNum == -1){
				gameState = GameState.GAME_OVER;
				break;
			}
			if (isHouseEmpty()){
				gameState = GameState.EMPTY_HOUSE;
				board.displayFromState(gameState,player1,player2);
				gameState = GameState.PLAY_GAME;

			}
			else{
				playGame();
			}
		}
		board.displayFromState(gameState, player1, player2);
	}

	void setUp() {

		playerList = new ArrayList<>();
		player1.setId(1);
		player2.setId(2);
		playerList.add(player1);
		playerList.add(player2);

		for (Player player : playerList){
			// create a list of the players houses
			ArrayList<House> houses = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_HOUSES; i++){
			House house = new House();
			house.setUp(INITIAL_SEEDS);
			houses.add(house);
			}
			player.setHouses(houses);
			Store store = new Store();
			store.setUp(0);
			player.setStore(store);
		}
	}

	void sowSeeds(Player currentPlayer, int houseNum) {
		int houseSelected = houseNum - 1; // 0 indexed
		int houseSeeds;
		Player oppositePlayer = getOppositePlayer(currentPlayer);
		ArrayList<Pit> allPits = new ArrayList<>();

		// add everything
		houseSeeds = currentPlayer.getHouses().get(houseSelected).getSeeds();
		currentPlayer.getHouses().get(houseSelected).emptyHouse(); // pickup seeds part 2
			allPits.addAll(currentPlayer.getHouses());
			allPits.add(currentPlayer.getStore());
			allPits.addAll(oppositePlayer.getHouses());

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

	void playGame(){
		sowSeeds(currentPlayer, houseNum);
		ArrayList<House> currentPlayersHouses = currentPlayer.getHouses();
		Store currentPlayersStore = currentPlayer.getStore();
		Player oppositePlayer = getOppositePlayer(currentPlayer);
		ArrayList<House> oppositePlayersHouses = oppositePlayer.getHouses();

		if (!(lastPit instanceof Store)) {
			// lands in the opposite players house
			if (!currentPlayersHouses.contains(lastPit)){
				// set opposite player
				setNewPlayer(getOppositePlayer(currentPlayer));
			} else { //lands in own house

				if((lastPit.getSeeds() != 1)) { // house contains other seeds
					setNewPlayer(getOppositePlayer(currentPlayer));
				} else { // house was empty

					// check if opponents house has seeds
					int oppHouseIndex = ((NUMBER_OF_HOUSES - 1) - currentPlayersHouses.indexOf(lastPit));
					int oppositeHouseSeeds = oppositePlayersHouses.get(oppHouseIndex).getSeeds();
					if (oppositeHouseSeeds > 0){

						// add own seed to store
						currentPlayersStore.addSeeds(1);
						((House) lastPit).emptyHouse();

						currentPlayersStore.addSeeds(oppositeHouseSeeds);
						oppositePlayersHouses.get(oppHouseIndex).emptyHouse();
					}
					//swap player
					setNewPlayer(getOppositePlayer(currentPlayer));
				}
			}
		}
	}

	void setNewPlayer(Player player){
		currentPlayer = player;
	}

	Player getOppositePlayer(Player currentPlayer){
		if (currentPlayer == player1){
			return player2;
		} else {
			return player1;
		}
	}

	boolean isHouseEmpty(){
		if (currentPlayer.getHouses().get(houseNum-1).getSeeds() == 0){
			return true;
		}
		return false;
	}
}
