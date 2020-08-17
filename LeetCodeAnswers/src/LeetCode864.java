/* author@ Qian Cai
 * Title@ Shortest Path to Get All Keys
 * We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
 *  
 */
import java.util.*;
import java.awt.Point;
public class LeetCode864 {
	
	class Solution {
	    int m, n;
	    String[] grid;
	    Map<Character, Point> locs;
	    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	    public int shortestPathAllKeys(String[] grid) {
	        this.grid = grid;
	        m = grid.length;
	        n = grid[0].length();
	        locs = new HashMap();
	        for (int i = 0; i < m; i++){
	            for (int j = 0; j < n; j++){
	                char c = grid[i].charAt(j);
	                if (c != '.' && c != '#'){
	                    locs.put(c, new Point(i, j));
	                }
	            }
	        }
	        int targetState = (1 << (locs.size()/2)) - 1;
	        Map<Character, Map<Character, Integer>> dists = new HashMap();
	        //bfs until collect distance of one point of interest to all other point of interest pos
	        for (char c: locs.keySet()){
	            dists.put(c, bfs(c));
	        }
	        //Dijkastra
	        PriorityQueue<ANode> pq = new PriorityQueue<ANode>((a, b)->Integer.compare(a.dist, b.dist));
	        pq.offer(new ANode(new Node('@', 0), 0));
	        Map<Node, Integer> finalDist = new HashMap();
	        finalDist.put(new Node('@', 0), 0);
	        while (!pq.isEmpty()){
	            ANode anode = pq.poll();
	            Node node = anode.node;
	            int d = anode.dist;
	            if (finalDist.getOrDefault(node, Integer.MAX_VALUE) < d) continue;
	            if (node.state == targetState) return d;
	            for (char destination: dists.get(node.place).keySet()){
	                int d2 = dists.get(node.place).get(destination);
	                int state2 = node.state;
	                if (Character.isLowerCase(destination)){
	                    state2 |= (1 <<(destination-'a'));
	                }
	                if (Character.isUpperCase(destination)){
	                    if ((node.state & (1 << (destination -'A'))) == 0) continue;
	                }
	                if (d + d2 < finalDist.getOrDefault(new Node(destination, state2), Integer.MAX_VALUE)){
	                    finalDist.put(new Node(destination, state2), d+d2);
	                    pq.offer(new ANode((new Node(destination, state2)), d+d2));   
	                }
	            }
	        }
	        return -1;
	    }
	    public Map<Character, Integer> bfs(char c){
	        int cr = locs.get(c).x, cc = locs.get(c).y;
	        boolean[][] seen = new boolean[m][n];
	        seen[cr][cc] = true;
	        int level = 0;
	        Queue<Point> queue = new LinkedList();
	        queue.add(new Point(cr, cc));
	        Map<Character, Integer> dist = new HashMap();
	        while (!queue.isEmpty()){
	            int size = queue.size();
	            for (int i = 0; i < size; i++){
	                Point cur = queue.poll();
	                if (grid[cur.x].charAt(cur.y) != c && grid[cur.x].charAt(cur.y) != '.'){
	                    dist.put(grid[cur.x].charAt(cur.y), level);
	                    continue;
	                }
	                for (int d = 0; d < 4; d++){
	                    int x = cur.x + dirs[d][0], y = cur.y + dirs[d][1];
	                    if (x >= 0 && x < m && y >= 0 && y < n && !seen[x][y]){  
	                        //System.out.println(grid[x].charAt(y));
	                        if (grid[x].charAt(y) != '#'){
	                            queue.add(new Point(x, y));
	                            seen[x][y] = true;
	                        }
	                    }
	                }
	            }
	            level++;
	        }
	        return dist;
	    }
	    //annotated Node
	    class ANode{
	        Node node;
	        int dist;
	        ANode(Node n, int d){
	            node = n;
	            dist = d;
	        }
	    }
	    class Node{
	        char place;
	        int state;
	        Node(char p, int s){
	            place = p;
	            state = s;
	        }
	        //override
	        public boolean equals(Object o){
	            if (this == o) return true;
	            if (!(o instanceof Node)) return false;
	            Node other = (Node) o;
	            return (place == other.place && state == other.state);
	        }
	        public int hashCode(){
	            return 256*state + place;
	        }
	    }
	  }
	    
}
