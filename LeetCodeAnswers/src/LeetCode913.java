/* author@ Qian Cai
 * Title@ Cat and Mouse
 * A game on an undirected graph is played by two players, Mouse and Cat, 
 * who alternate turns.
 * The graph is given as follows: graph[a] is a list of all nodes b such that
 *  ab is an edge of the graph.
 *  Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second,
 *  and there is a Hole at node 0.
 *  During each player's turn, they must travel along one edge of the graph 
 *  that meets where they are.  For example, if the Mouse is at node 1, 
 *  it must travel to any node in graph[1]. Additionally, it is not allowed 
 *  for the Cat to travel to the Hole (node 0.)
Then, the game can end in 3 ways:

If ever the Cat occupies the same node as the Mouse, the Cat wins.
If ever the Mouse reaches the Hole, the Mouse wins.
If ever a position is repeated (ie. the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
Given a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 2 if the game is won by Cat, and 0 if the game is a draw.
 * ==========================================================================
 * Time Complexity: O(n^4)
 * Space Complexity: O(n^3)
 * ==========================================================================
 */
import java.util.Arrays;
public class LeetCode913 {
	public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] dp = new int[n][n][2*n]; //i is mouse position, j is cat position, k == 0 is mouse turn, k == 1 is cat turn
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return helper(graph, 1, 2, 0, dp);
    }
    public int helper(int[][] graph, int mousePos, int catPos, int turn, int[][][] dp){
        if (turn == graph.length*2){
            return  0; //if both mouse and cat goes all the vertex, is a draw
        } 
        if (mousePos == catPos){
            dp[mousePos][catPos][turn] = 2;
            return 2;
        } 
        if (mousePos == 0) {
            dp[mousePos][catPos][turn] = 1;
            return 1;
        }
        if (dp[mousePos][catPos][turn] != -1) return dp[mousePos][catPos][turn];
        if (turn % 2 == 0){
            boolean catWin = true;
            for (int nei: graph[mousePos]){
                int next = helper(graph, nei, catPos, 1+turn, dp);
                if (next == 1){
                    dp[mousePos][catPos][turn] = 1;
                    return 1;
                } else if (next != 2){
                    catWin = false;
                }
            }   
            if (catWin){
                dp[mousePos][catPos][turn] = 2;
                return 2;
            }
            return dp[mousePos][catPos][turn] = 0;
        } else {
            boolean mouseWin = true;
            for (int nei: graph[catPos]){
                if (nei == 0) continue;
                int next = helper(graph, mousePos, nei, 1+turn, dp);
                if (next == 2) return dp[mousePos][catPos][turn] = 2;
                else if (next != 1) mouseWin = false;
            }
            if (mouseWin) return dp[mousePos][catPos][turn] = 1;
            return dp[mousePos][catPos][turn] = 0;
            
        }
       
    }
}
