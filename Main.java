import TicTacToe.GameBoard;
import TicTacToe.Player;

import java.util.Scanner;

public class Main {

	public static char[] setPosition(Scanner in, int turn, GameBoard game1) {
		boolean positionAquired = false;
		char[] position = new char[3];
		
		while(!positionAquired) {

			position = in.nextLine().toCharArray();

			//check if position is in correct format

			boolean safeInput = true;

			for(int i = 0; i < position.length; i++) {
				if((!Character.isLetter(position[i]) || !Character.isLowerCase(position[i])) && i == 0) {
					safeInput = false;
				}
				if(!Character.isDigit(position[i]) && i != 0) {
					safeInput = false;
				}
			}

			if(!safeInput) {
				System.out.println("INVALID INPUT: [letter (lower case), number]");
				continue;
			}

			//check if position is already filled --> else, fill it
			if(game1.previousPlayer(turn).isFilled(position) || !(game1.nextPlayer(turn).setPosition(position))) {
				System.out.println("INVALID INPUT: index unavailable");
				continue;
			}

			positionAquired = true;
		}

		return position;
	}

	public static void main(String[] args) {
		System.out.println("TIC-TAC-TOE");

		System.out.print("Enter gameBoard size: ");
		Scanner in = new Scanner(System.in);

		int size = 0;
		String newLine = "";

		while(true) {
			size = in.nextInt();
			newLine = in.nextLine();

			if(size < 27 && size > 1) {
				break;
			} else {
				System.out.println("INVALID INPUT: max size = 26");
			}
		}

		System.out.println("Ready...Set...GOOOO!");

		GameBoard game1 = new GameBoard(size);

		Player player1 = new Player('X', game1);
		Player player2 = new Player('O', game1);

		game1.addPlayer(player1);
		game1.addPlayer(player2);

		int turn = 0;

		game1.drawBoard();
		while(!game1.gameOver()) {
			//next turn
			turn++;

			//get next position
			System.out.println("\nPlayer " + game1.nextPlayer(turn).getSymbol() + " Turn!");
			setPosition(in, turn, game1);

			//check win condition
			if(game1.nextPlayer(turn).hasWon() || game1.isCats()) {
				game1.setGameOver(true);
			}

			game1.drawBoard();
		}

		if(!game1.isCats()) {
			System.out.println("\nPlayer " + game1.nextPlayer(turn).getSymbol() + " Wins!!!");
		} else {
			System.out.println("The game is CATS!");
		}

	}	
}