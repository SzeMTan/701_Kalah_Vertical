package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

import java.util.ArrayList;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */

enum GameState{
	QUIT,
	GAME_OVER,
	PLAY_GAME
}
public class Kalah {

	private int NUMBER_OF_HOUSES = 6;
	private int INITIAL_SEEDS = 4;
	private int houseNum = 0;
	private Pit lastPit;
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player currentPlayer = player1;
	private Player otherPlayer = player2;
	private GameState gameState = GameState.PLAY_GAME;
	private ArrayList<Player> playerList;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		Board board = new Board();
		board.setIO(io);
		setUp();
		board.setPlayersList(playerList);

		while (true) {
			board.displayBoard(player1.getHouses(),player2.getHouses(), player1.getStore(),player2.getStore());

			// check if houses of the current player is empty
			if (currentPlayer.checkIfHousesAreEmpty()){
				gameState = GameState.GAME_OVER;
				break;
			}
			houseNum = board.askPlayerInput(currentPlayer, NUMBER_OF_HOUSES);

			// Check for quit
			if (houseNum == -1){
				gameState = GameState.QUIT;
				break;
			}

			// Check for invalid selection (empty house)
			if (isHouseEmpty()){
				board.houseEmptyMessage();
			} else{
				playGame();
			}
		}
		board.handleGameOver(gameState, player1, player2);
	}

	private void setUp() {

		playerList = new ArrayList<>();
		player1.setId(1);
		player2.setId(2);
		playerList.add(player1);
		playerList.add(player2);

		for (Player player : playerList){
			// create a list of the players houses
			ArrayList<House> houses = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
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

	private ArrayList<Pit> getAllPits(Player currentPlayer, int houseSelected) {
		Player oppositePlayer = getOppositePlayer(currentPlayer);
		ArrayList<Pit> allPits = new ArrayList<>();

		// Add all the pits the same ArrayList
		currentPlayer.getHouses().get(houseSelected).emptyHouse(); // pickup seeds part 2
		allPits.addAll(currentPlayer.getHouses());
		allPits.add(currentPlayer.getStore());
		allPits.addAll(oppositePlayer.getHouses());
		return allPits;
	}

	private void sowSeeds(Player currentPlayer, int houseNum) {
		int houseSelected = houseNum - 1; // 0 indexed
		int houseSeeds = currentPlayer.getHouses().get(houseSelected).getSeeds();

		ArrayList<Pit> allPits = getAllPits(currentPlayer, houseSelected);

		// keep going until no more seeds
		while (houseSeeds > 0) {
			if (houseNum >= allPits.size()) {
				houseNum = 0;
			}

			allPits.get(houseNum).addSeeds(1);
			houseNum++;
			houseSeeds--;
		}
		lastPit = allPits.get(houseNum - 1);
	}

	private void playGame() {
		sowSeeds(currentPlayer, houseNum);
		ArrayList<House> currentPlayersHouses = currentPlayer.getHouses();
		Store currentPlayersStore = currentPlayer.getStore();
		Player oppositePlayer = getOppositePlayer(currentPlayer);
		ArrayList<House> oppositePlayersHouses = oppositePlayer.getHouses();

		if (lastPit instanceof Store) {
			return;
		}

		if (currentPlayersHouses.contains(lastPit) && lastPit.getSeeds() == 1) {
			capture(currentPlayersHouses, oppositePlayersHouses, currentPlayersStore);
		}

		swapPlayer();
	}

	private void capture(ArrayList<House> currentPlayersHouses, ArrayList<House> oppositePlayersHouses,
						 Store currentPlayersStore) {
		// check if opponents house has seeds
		int oppHouseIndex = ((NUMBER_OF_HOUSES - 1) - currentPlayersHouses.indexOf(lastPit));
		int oppositeHouseSeeds = oppositePlayersHouses.get(oppHouseIndex).getSeeds();
		if (oppositeHouseSeeds > 0) {

			// add own seed to store
			currentPlayersStore.addSeeds(1);
			((House) lastPit).emptyHouse();

			currentPlayersStore.addSeeds(oppositeHouseSeeds);
			oppositePlayersHouses.get(oppHouseIndex).emptyHouse();
		}
	}

	private void swapPlayer() {
		currentPlayer = getOppositePlayer(currentPlayer);
	}

	private Player getOppositePlayer(Player currentPlayer){
		if (currentPlayer == player1){
			return player2;
		} else {
			return player1;
		}
	}

	private boolean isHouseEmpty(){
		if (currentPlayer.getHouses().get(houseNum-1).getSeeds() == 0){
			return true;
		}
		return false;
	}
}
