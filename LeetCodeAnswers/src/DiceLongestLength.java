/* author@ Qian Cai
 * Title@ DeceLongestLength
 * 很多色子，每个色子有两面，如果两个色子的左面或者右面点数一样，就可以串起来比如1-4 1-6可以串成
 * 4-1-1-6可以掉头。 求最长能串起来的长度。
 * time: O(EV) O(E+V)
 */
import java.util.*;
public class DiceLongestLength {	
	public static int DiceLongestLength(int[][] dices) {
		Map<Integer, List<Integer>>graph = new HashMap(); //key: dice number, value: dice idx which has the key number
		int n = dices.length;		
		for (int i = 0; i < n; i++) {
			int num1 = dices[i][0], num2 = dices[i][1];
			if (!graph.containsKey(num1)) graph.put(num1, new ArrayList());
			if (!graph.containsKey(num2)) graph.put(num2, new ArrayList());
			graph.get(num1).add(i);
			graph.get(num2).add(i);
		}
		int res = 0;
		//try to find the longest dfs path from each node and edge
		for (int i = 0; i < n; i++) {
			res = Math.max(res,  dfs(i, dices[i][0], new HashSet(), dices, graph));
			res = Math.max(res, dfs(i, dices[i][1], new HashSet(), dices, graph));
		}
		
		return res;
	}
	public static int dfs(int idx, int to, Set<Integer> visited, int[][] dices, Map<Integer, List<Integer>> graph) {
		visited.add(idx);
		int res = 2;
		for (int nextIdx: graph.get(to)) {
			if (!visited.contains(nextIdx)) {
				int newNode = to;
				for (int i: dices[nextIdx]) {
					if (i != to) newNode = i;
				}
				res = Math.max(res, 2 + dfs(nextIdx, newNode, visited, dices, graph));
			}
		}
		//System.out.println("idx: " + idx + " res: " + res);
		visited.remove(idx);
		return res;
	}
	public static void main(String[] args) {
		int[][] dices_one = new int[][] {{1, 3}, {1, 3}, {1, 3}, {1, 3}};
		int[][] dices_two = new int[][] {{5, 6}, {1, 3}, {2, 3}, {1, 5}, {1, 8}, {8, 2}, {11, 12}};
		int[][] dices_three = new int[][] {{1, 2}, {2, 4}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {4, 6}, {6, 7}, {4, 7}};
		int res_one = DiceLongestLength(dices_one);
		int res_two = DiceLongestLength(dices_two);
		int res_three = DiceLongestLength(dices_three);
		System.out.println(res_one);
		System.out.println(res_two);
		System.out.println(res_three);
		if (res_one == 8 && res_two == 12) {
			System.out.println("You pass the test cases!");
		} else {
			System.out.println("Try again!");
		}
		
	}
}
