//Charalampos Kounnapis AM:5401 
//Stelios Savva AM:5404 
//Ioannis Rafail Tornaritis AM:5405

public class Minimax {
    public static void findBestMove(TicTacToe game) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];  // row, col
        char bestMark = ' '; // mark

        // Try every possible move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getBoard()[i][j] == ' ') {
                    for (char mark : new char[]{'C', 'S', 'E'}) {
                        game.makeMove(i, j, mark);
                        int score = minimax(game, 0, false);
                        game.makeMove(i, j, ' '); // Undo move
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                            bestMark = mark;
                        }
                    }
                }
            }
        }

        game.makeMove(bestMove[0], bestMove[1], bestMark);
        System.out.printf("Computer's move: Row=%d, Column=%d, Mark=%c%n", bestMove[0], bestMove[1], bestMark);
    }

    private static int minimax(TicTacToe game, int depth, boolean isMaximizing) {
        if (game.checkWin()) {
            return isMaximizing ? -10 : 10;
        } else if (game.checkDraw()) {
            return 0;
        }

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getBoard()[i][j] == ' ') {
                    for (char mark : new char[]{'C', 'S', 'E'}) {
                        game.makeMove(i, j, mark);
                        int score = minimax(game, depth + 1, !isMaximizing);
                        game.makeMove(i, j, ' '); // Undo move

                        if (isMaximizing) {
                            bestScore = Math.max(score, bestScore);
                        } else {
                            bestScore = Math.min(score, bestScore);
                        }
                    }
                }
            }
        }

        return bestScore;
    }
}
