//charalampos kounnapis AM:5401
//Stelios Savva AM:5404
//Ioannis Rafail Tornaritis AM:5405

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] initialState = new int[3][3];

        System.out.println("Enter the initial state (use numbers 0-8 for tiles, separated by spaces):");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                initialState[i][j] = scanner.nextInt();
            }
        }
		System.out.println();
		
		
		System.out.println("UCS_Algorithm");

        UCS ucs = new UCS(initialState);
        ucs.run();
		
		System.out.println("-------------------------------------------");
		System.out.println("AStar_Algorithm");
		AStar astar = new AStar(initialState);
        astar.run();
	}
	
}