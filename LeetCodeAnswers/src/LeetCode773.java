/* author@ Qian Cai
 * Title@ Sliding Puzzle
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 * 
 */
import java.util.*;
public class LeetCode773 {
	/*
    [[1,2,3],
     [4,0,5]]
    */

    public int slidingPuzzle(int[][] board) {
        Set<String> seen = new HashSet();
        String initial = getCode(board);
        int step = 0;
        seen.add(initial); 
        Queue<String> queue = new LinkedList();
        queue.add(initial);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                 String cur = queue.poll();
                 if (cur.equals("123450")) return step;
                 List<String> nextList = getNext(cur);
                for (String next: nextList){
                    if (!seen.contains(next)){
                        queue.add(next);
                        seen.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    public String getCode(int[][] board){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++){
            sb.append(board[i/3][i%3]);  
        }
        return sb.toString();
    }
    public List<String> getNext(String cur){
        List<String> res = new ArrayList();
       
        int i = 0; 
        for (int j = 0; j < 6; j++){
            if (cur.charAt(j) == '0'){
                i = j;
                break;
            }
        }
        int x = i/3, y = i%3;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int d = 0; d < 4; d++){
           int r = dirs[d][0] + x, c = dirs[d][1] + y;
           if (r >= 0 && r < 2 && c >= 0 && c < 3){
                              
                   res.add(swap(cur, i, r*3+c));
           }            
        }
        return res;
    }
    public String swap(String cur, int i, int j){
        char[] charArray = cur.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return new String(charArray);
    }
}
