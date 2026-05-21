//charalampos kounnapis AM:5401
//Stelios Savva AM:5404
//Ioannis Rafail Tornaritis AM:5405

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;

public class AStar {
	private int[][] initialState;
	private int expand;
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
		return (a.getCost() + a.getHeuristic()) - (b.getCost() + b.getHeuristic());
		}
	};

	private PriorityQueue<GridNode> pq = new PriorityQueue<>(comparator);
	private HashSet<String> visited = new HashSet<>();

	public AStar(int[][] initialState) {
		this.initialState = initialState;
	}

	public void run() {
		GridNode initialNode = new GridNode(initialState, 0, heuristic(initialState), null);
		pq.add(initialNode);
		while (!pq.isEmpty()) {
			GridNode current = pq.poll();
			if (isGoalState(current.getState())) {
				printSolution(current);
				System.out.println("Number of moves: " + moves);
				System.out.println("Number of expands: " + expand);
				return;
		  }
		  visited.add(arrayToString(current.getState()));
		  expandNode(current, pq, visited);
		  incrementExpand();
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
		  int moveCost = calculateMoveCost(i);
		  if (isValid(newX, newY)) {
			int[][] newState = cloneState(currentState);
			int temp = newState[x][y];
			newState[x][y] = newState[newX][newY];
			newState[newX][newY] = temp;
			String newStateString = arrayToString(newState);
			if (!visited.contains(newStateString)) {
			  GridNode newNode = new GridNode(newState, cost + moveCost, heuristic(newState), node);
			  pq.add(newNode);
			  visited.add(newStateString);	
			}
		  }
		}
	}
	
	private int calculateMoveCost(int direction) {
    // Assign higher cost for diagonal moves (indices 4, 5, 6, 7)
		return (direction >= 4) ? 2 : 1; // Adjust the cost as needed
	}

	private boolean isValid(int x, int y) {
		return x >= 0 && x < 3 && y >= 0 && y < 3;
	}

	private boolean isGoalState(int[][] state) {
		return Arrays.deepEquals(state, goalState);
	}

	private int heuristic(int[][] state) {
		int manhattanDistance = 0;
		int misplacedTiles = 0;

		// Loop through each tile in the state
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int value = state[i][j];
				if (value != 0) { // Skip the blank tile
					// Calculate the target position of the current tile
					int targetX = (value - 1) / 3;
					int targetY = (value - 1) % 3;

					// Calculate Manhattan distance
					manhattanDistance += Math.abs(i - targetX) + Math.abs(j - targetY);

					// Check if the tile is misplaced
					if (value != goalState[i][j]) {
						misplacedTiles++;
					}
				}
			}
		}

		// Heuristic penalty for tiles that are in their correct row or column but not in the correct order
		int correctOrderPenalty = countCorrectOrderPenalty(state);

		// Combine the Manhattan distance with the misplaced tiles count and correct order penalty
		return manhattanDistance + misplacedTiles + correctOrderPenalty;
	}

	// Helper function to count the penalty for tiles that are in their correct row or column but not in the correct order
	private int countCorrectOrderPenalty(int[][] state) {
		int penalty = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int value = state[i][j];
				if (value != 0 && value != goalState[i][j]) {
					int targetX = (value - 1) / 3;
					int targetY = (value - 1) % 3;
					if (i == targetX || j == targetY) {
						penalty++;
					}
				}
			}
		}

		return penalty;
	}




	private void printSolution(GridNode node) {
		if (node == null) return;
		printSolution(node.getParent());
		printState(node.getState());
		System.out.println("Move: " + moves);
		System.out.println("---------------------");
	}

	private void printState(int[][] state) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(state[i][j] + " ");
			}
			System.out.println();
		}
		moves++;
	}

	private int[][] cloneState(int[][] state) {
		int[][] newState = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newState[i][j] = state[i][j];
			}
		}
		return newState;
	}

	private String arrayToString(int[][] state) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(state[i][j]);
			}
		}
		return sb.toString();
	}

	private void incrementExpand() {
		expand++;
	}
}
