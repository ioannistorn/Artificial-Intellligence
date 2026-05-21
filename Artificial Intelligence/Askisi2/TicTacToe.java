//Charalampos Kounnapis AM:5401 
//Stelios Savva AM:5404 
//Ioannis Rafail Tornaritis AM:5405

import java.util.Arrays;
import java.util.Random;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'S';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
		
		// Start with 'S' in the middle row either in the left or right position
		Random random = new Random();
		int side = random.nextInt(2); // 0 or 1
		board[1][side * 2] = 'S'; // Place 'S' either in column 0 or column 2
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print("[");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    public boolean checkWin() {
        // Check if the word "CSE" or "ESC" is formed anywhere on the board
        String target1 = "CSE";
        String target2 = "ESC";

        String[] rows = new String[3];
        String[] columns = new String[3];
        String diagonal1 = "";
        String diagonal2 = "";

        for (int i = 0; i < 3; i++) {
            rows[i] = "";
            columns[i] = "";
            for (int j = 0; j < 3; j++) {
                rows[i] += board[i][j];
                columns[i] += board[j][i];
                if (i == j) {
                    diagonal1 += board[i][j];
                }
                if (i + j == 2) {
                    diagonal2 += board[i][j];
                }
            }
        }

        // Check rows, columns, and diagonals for the target words
        if (Arrays.asList(rows).contains(target1) || Arrays.asList(rows).contains(target2) ||
            Arrays.asList(columns).contains(target1) || Arrays.asList(columns).contains(target2) ||
            diagonal1.equals(target1) || diagonal1.equals(target2) ||
            diagonal2.equals(target1) || diagonal2.equals(target2)) {
            return true;
        }

        return false;
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeMove(int row, int col, char player) {
        board[row][col] = player;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'S') ? 'C' : 'S';
    }

    public char[][] getBoard() {
        return board;
    }
}
