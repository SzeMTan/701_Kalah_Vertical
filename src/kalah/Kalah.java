package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	int numOfHouses = 5;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		displayBoard(io);

		int houseNum = io.readInteger("Player P1's turn - Specify house number or 'q' to quit: ", 0, numOfHouses,-1, "q" );

		if (houseNum == -1){
			io.println("Game over");
		}
		displayBoard(io);


	}
	void displayBoard(IO io){
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	//	io.println("Player P1's turn - Specify house number or 'q' to quit: ");
	}
}
