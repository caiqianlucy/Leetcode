import java.util.*;
/*author@ Qian Cai
 * Title@ BattleShip
 * Details:https://leetcode.com/discuss/interview-question/538068/
 * Time: O(n) n represents number of cells of ships
 * Space: O(m) m is number of hits
 * 
 */
public class BattleShip {
   public static String Solution(int n, String S, String T){
	    int sink = 0;
	    int hit = 0;
	    Set<String> hits = new HashSet<>(Arrays.asList(T.split(" ")));
	    String[] ships = S.split(",");
	    for (String ship : ships) {
	        Set<String> shipComponents = new HashSet<>();
	        char top = ship.charAt(0);
	        char left = ship.charAt(1);
	        char bottom = ship.charAt(3);
	        char right = ship.charAt(4);
	        for (char i = top; i <= bottom; i++) {
	            for (char j = left; j <= right; j++) {
	                shipComponents.add("" + i + j);
	            }
	        }
	        if (hits.containsAll(shipComponents)) {
	            sink++;
	        } else {
	            for (String com : shipComponents) {
	                if (hits.contains(com)) {
	                    hit++;
	                    break;
	                }
	            }
	        }

	    }

	    return "" + sink + "," + hit;

	}

	public static void main(String[] args) {
	    //  String ans = solution(4,"1B 2C,2D 4D","2B 2D 3D 4D 4A");
	    String ans = Solution(12, "1A 2A,12A 12A", "12A");
	    System.out.println(ans);
	}
}
