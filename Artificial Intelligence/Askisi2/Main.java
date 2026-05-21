//Charalampos Kounnapis AM:5401 
//Stelios Savva AM:5404 
//Ioannis Rafail Tornaritis AM:5405

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Print current board state
            game.printBoard();

            // Player's turn
            System.out.println("It's your turn. Enter row, column, and mark (C, S, or E) separated by space (e.g., 1 2 C):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char mark = scanner.next().charAt(0);
			if (mark != 'C' && mark != 'S' && mark != 'E') {
				System.out.println("Invalid mark. Please enter only 'C', 'S', or 'E'.");
				continue;
            }
            game.makeMove(row, col, mark);
            System.out.printf("Player's move: Row=%d, Column=%d, Mark=%c%n", row, col, mark);
			game.printBoard();
            // Check if player wins or if it's a draw
            if (game.checkWin()) {
                System.out.println("Game over!");
                game.printBoard();
                System.out.println("You win!");
                break;
            } else if (game.checkDraw()) {
                System.out.println("It's a draw!");
                game.printBoard();
                break;
            }

            // Computer's turn
            Minimax.findBestMove(game);

            // Check if computer wins or if it's a draw
            if (game.checkWin()) {
                System.out.println("Game over!");
                game.printBoard();
                System.out.println("Computer wins!");
                break;
            } else if (game.checkDraw()) {
                System.out.println("It's a draw!");
                game.printBoard();
                break;
            }
        }

        scanner.close();
    }
}
