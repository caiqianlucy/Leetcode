/* author@ Qian Cai
 * Title@ Jump Game IV
 * Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.
 * 
 */
public class LeetCode1345 {
	 public int minJumps(int[] arr) {
	        int n = arr.length;
	        Map<Integer, List<Integer>> equals = new HashMap();
	        if (n == 1) return 0;
	        for (int i = 0; i < n; i++){
	            if (!equals.containsKey(arr[i])) equals.put(arr[i], new ArrayList<Integer>());
	            equals.get(arr[i]).add(i);
	        }
	   
	        boolean[] visited = new boolean[n];    
	        Queue<Integer> queue = new LinkedList();
	        queue.add(n-1);
	        visited[n-1] = true;
	        int level = 0;
	        while (!queue.isEmpty()){
	            int size = queue.size();
	            for (int i = 0; i < size; i++){
	                int cur = queue.poll();
	                if (cur == 0) return level;
	                if (cur > 0 && !visited[cur-1]) {
	                    visited[cur-1] = true;
	      
	                    queue.add(cur-1);
	                }
	                        
	                if (cur < n -1 && !visited[cur+1]){
	                    visited[cur+1] = true;
	                    queue.add(cur+1);
	                }
	                if (equals.containsKey(arr[cur])){
	                    for (int idx: equals.get(arr[cur])){
	                        if (idx != cur && !visited[idx]){
	                            visited[idx] = true;
	                            queue.add(idx);
	                        }
	                    }
	                    equals.remove(arr[cur]);
	                }
	            }
	            level++;
	        }
	        return 0;
	    }
	    
}
