//charalampos kounnapis AM:5401
//Stelios Savva AM:5404
//Ioannis Rafail Tornaritis AM:5405

public class GridNode {
    private int[][] state;
    private int cost;
    private int heuristic;
    private GridNode parent;

    public GridNode(int[][] state, int cost, int heuristic, GridNode parent) {
        this.state = state;
        this.cost = cost;
        this.heuristic = heuristic;
        this.parent = parent;
    }

    public GridNode(int[][] state, int cost, GridNode parent) {
        this(state, cost, 0, parent);
    }

    public int[][] getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public GridNode getParent() {
        return parent;
    }
}
