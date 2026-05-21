//charalampos kounnapis AM:5401
//Stelios Savva AM:5404
//Ioannis Rafail Tornaritis AM:5405

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;

public class UCS {
    private int[][] initialState;
	private int expands;
	private static int moves = -1;

    static final int[][] goalState = {
        {6, 5, 4},
        {7, 0, 3},
        {8, 1, 2}
    };

    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1}; // Include diagonal moves
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1}; // Include diagonal moves

    static Comparator<GridNode> comparator = new Comparator<GridNode>() {
        public int compare(GridNode a, GridNode b) {
            return a.getCost() - b.getCost();
        }
    };

    

    public UCS(int[][] initialState) {
        this.initialState = initialState;
    }

    public void run() {
		PriorityQueue<GridNode> pq = new PriorityQueue<>(comparator);
		HashSet<String> visited = new HashSet<>();
		
		GridNode initialNode = new GridNode(initialState, 0, null);
		pq.add(initialNode);
		while (!pq.isEmpty()) {
			GridNode current = pq.poll();
			if (isGoalState(current.getState())) {
				printSolution(current);
				System.out.println("Number of moves: " + moves);
				System.out.println("Number of expands: " + expands);
				return;
			}
			visited.add(arrayToString(current.getState()));
			expandNode(current, pq, visited); // Pass pq and visited to expandNode
			incrementExpants();
		}
		System.out.println("No solution found");
	}

	

    private void expandNode(GridNode node, PriorityQueue<GridNode> pq, HashSet<String> visited) {
		int[][] currentState = node.getState();
		int cost = node.getCost();
		int x = -1, y = -1;
		// Find the position of the empty tile
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (currentState[i][j] == 0) {
					x = i;
					y = j;
					break;
				}
			}
		}
		// Generate child states by moving the empty tile
		for (int i = 0; i < 8; i++) { // Loop through all directions
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY)) {
				int[][] newState = cloneState(currentState);
				int temp = newState[x][y];
				newState[x][y] = newState[newX][newY];
				newState[newX][newY] = temp;
				String newStateString = arrayToString(newState);
				if (!visited.contains(newStateString)) {
					GridNode newNode = new GridNode(newState, cost + 1, node);
					pq.add(newNode);
					visited.add(newStateString); // Add the new state to visited
				}
			}
		}
	}


    static boolean isValid(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    static boolean isGoalState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != goalState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static void printSolution(GridNode node) {
        if (node == null) return;
        printSolution(node.getParent());
        printState(node.getState());
        System.out.println();
    }

    static void printState(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
		CountMoves();
		System.out.println("Move: "+ moves);
		System.out.println("---------------------");
		
    }

    static int[][] cloneState(int[][] state) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newState[i][j] = state[i][j];
            }
        }
        return newState;
    }

    static String arrayToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(state[i][j]);
            }
        }
        return sb.toString();
    }
	
	private void incrementExpants() {
        expands++;
    }
	public static void CountMoves(){
		moves++;
	}
	public int getMoves() { 
		return moves;	
	}
}