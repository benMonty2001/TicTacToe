package TicTacToe;

import java.lang.NullPointerException;

public class Player {
	private boolean victor;
	private char symbol;
	private int[] positions;
	private int numPositions;
	private GameBoard gameBoard;

	public Player(char symbol, GameBoard gameBoard) {
		this.victor = false;
		this.symbol = symbol;
		this.gameBoard = gameBoard;
		this.positions = new int[gameBoard.getSize()*gameBoard.getSize()];
	}

	public boolean hasWon() {
		return this.victor;
	}

	public void setVictor(boolean bool) {
		this.victor = bool;
	}

	public char getSymbol() {
		return this.symbol;
	}

	public int getNumPositions() {
		return this.numPositions;
	}

	public int getNumericPosition(char[] position) {
		
		char[] digits = new char[position.length - 1];

		for(int i = 0; i < digits.length; i++) {
			digits[i] = position[i + 1];
		}

		return Integer.parseInt(new String(digits));
	}

	public boolean setPosition(char[] position) {
		//size map is 3 and input is d1
		if(gameBoard.alphabet.indexOf(position[0]) + 1 > gameBoard.getSize()) {
			System.out.println(getNumericPosition(position));
			return false;
		}

		//check to avoid this cenario --> map.size = 3 and index provided is 'a', '4'
		if(getNumericPosition(position) > gameBoard.getSize()) {
			System.out.println(getNumericPosition(position));
			return false;
		}

		//convert position to an integer value
		int positionAsInt = gameBoard.alphaNumericGrid.get(position[0]) + getNumericPosition(position);

		if(isFilled(position)) {
			System.out.println(getNumericPosition(position));
			return false;
		}

		positions[positionAsInt - 1] = 1;
		numPositions++;

		//check win condition

		if(hasPlayerWon()) {
			setVictor(true);
		}

		//the position was successfully implemented

		return true;
	}

	//returns true if position on board is already occupied
	public boolean isFilled(char[] position) {
		//size map is 3 and input is d1
		if(gameBoard.alphabet.indexOf(position[0]) + 1 > gameBoard.getSize()) {
			return false;
		}

		//check to avoid this cenario --> map.size = 3 and index provided is 'a', '4'
		if(getNumericPosition(position) > gameBoard.getSize()) {
			return false;
		}

		//convert position into integer value
		int positionAsInt = gameBoard.alphaNumericGrid.get(position[0]) + getNumericPosition(position);

		if(positions[positionAsInt - 1] == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFilled(int position) {
		if(positions[position - 1] == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasPlayerWon() {
		
		int[] row_counter = new int[gameBoard.getSize()];
		int[] column_counter = new int[gameBoard.getSize()];

		//insert positions into counters
		for(int i = 0; i < gameBoard.getSize(); i++) {
			for(int j = 0; j < gameBoard.getSize(); j++) {
				if(positions[(i * gameBoard.getSize()) + j] == 1) {
					row_counter[i] += 1;
					column_counter[j] += 1;	
				}
			}
		}

		//check for a win patterns
		boolean crissCrossPatternDetected = true;

		for(int i = 0; i < gameBoard.getSize(); i++) {
			if(row_counter[i] != 1 || column_counter[i] != 1) {
				crissCrossPatternDetected = false;
			}
			if(row_counter[i] == gameBoard.getSize() || column_counter[i] == gameBoard.getSize()) {
				return true;
			}
		}

		if(crissCrossPatternDetected) {
			return true;
		} else {
			return false;
		}
	}

}