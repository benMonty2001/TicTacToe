package TicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class GameBoard {

	private int size;
	private boolean gameOver;
	private boolean cats;
	private ArrayList<Player> players = new ArrayList<>();
	private char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	protected ArrayList<Character> alphabet = new ArrayList<>();
	protected ArrayList<Integer> alphaHolders = new ArrayList<>();
	protected HashMap <Character, Integer> alphaNumericGrid = new HashMap<>();

	public GameBoard(int size) {				
		this.size = size;
		this.gameOver = false;
		this.cats = false;

		for(Character c : letters) {
			alphabet.add(c);
		}

		for(int i = 0; i < size; i++) {
			alphaHolders.add(i * size);

			//creates an alpha-numeric gride of size 26x26 for reference
			alphaNumericGrid.put(alphabet.get(i), alphaHolders.get(i));
		}
	}

	public int getSize() {
		return this.size;
	}

	public boolean gameOver() {
		return this.gameOver;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public Player nextPlayer(int turn) {
		if(turn % 2 == 1) {
			return players.get(0);
		} else {
			return players.get(1);
		}
	}

	public Player previousPlayer(int turn) {
		if(turn % 2 == 1) {
			return players.get(1);
		} else {
			return players.get(0);
		}
	}

	public void setGameOver(boolean bool) {
		this.gameOver = bool;
	}

	public boolean isCats() {
		if(players.get(0).getNumPositions() == ((size*size)/2) + 1 && !players.get(0).hasWon()) {
			return true;
		} else {
			return false;
		}
	}

	public void drawBoard() {
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				if(i < (this.size - 1)) {
					if(players.get(0).isFilled((i * size) + 1 + j)) {
						System.out.print("_" + players.get(0).getSymbol() + "_");
					} else if(players.get(1).isFilled((i * size) + 1 + j)) {
						System.out.print("_" + players.get(1).getSymbol() + "_");
					} else {
						
						System.out.print("___");
					}
				} else {
					if(players.get(0).isFilled((i * size) + 1 + j)) {
						System.out.print(" " + players.get(0).getSymbol() + " ");
					} else if(players.get(1).isFilled((i * size) + 1 + j)) {
						System.out.print(" " + players.get(1).getSymbol() + " ");
					} else {
						System.out.print("   ");
					}
				}

				if(j < (this.size - 1)) {
					System.out.print("|");
				}
			}
			System.out.print("\n");
		}
	}
}

/*



___|___|___
___|___|___
   |   |


*/