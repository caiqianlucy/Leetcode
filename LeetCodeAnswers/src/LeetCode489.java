import java.util.*;

public class LeetCode489 {
	interface Robot {
		    // Returns true if the cell in front is open and robot moves into the cell.
		   // Returns false if the cell in front is blocked and robot stays in the current cell.
		     public boolean move();
		 
		      // Robot will stay in the same cell after calling turnLeft/turnRight.
		      // Each turn will be 90 degrees.
		      public void turnLeft();
		     public void turnRight();
		 
		      // Clean the current cell.
		     public void clean();
		 }
	int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //right turn after each direction
    Set<Pair> visited = new HashSet();
    Robot robot;
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);  //initial x, y, direction
    }
    public void backtrack(int row, int col, int d){
        visited.add(new Pair(row, col));
        robot.clean();
        for (int i = 0; i < 4; i++){
            int newD = (d+i) % 4;
            int x = row + dirs[newD][0];
            int y = col + dirs[newD][1];
            if (!visited.contains(new Pair(x, y)) && robot.move()){
                backtrack(x, y, newD);
                goBack();
            }
            robot.turnRight();          
        }
    }
    public void goBack(){
        robot.turnRight();
        robot.turnRight();
        robot.move(); //to last position
        robot.turnRight();
        robot.turnRight(); //reset to original directions
    }
}
