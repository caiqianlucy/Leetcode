/* author@ Qian Cai
 * Title@ Sequence Reconstruction
 * Check whether the original sequence org can be uniquely reconstructed from 
 * the sequences in seqs. The org sequence is a permutation of the integers 
 * from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest 
 * common supersequence of the sequences in seqs (i.e., a shortest sequence so
 *  that all sequences in seqs are subsequences of it). Determine whether there
 *   is only one sequence that can be reconstructed from seqs and it is the org 
 *   sequence.
 *  ===================
 *  Topological sort
 *  Time, Space O(V+E）
 *  ===================
 */
import java.util.*;
public class LeetCode444 {
	 public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
	        int n = org.length;
	        Map<Integer, Integer> indegree = new HashMap();
	        Map<Integer, List<Integer>> graph = new HashMap();
	        for (List<Integer> seq: seqs){
	            for (int i = 0; i < seq.size(); i++){
	                if (!indegree.containsKey(seq.get(i))) indegree.put(seq.get(i), 0);
	                if (i < seq.size()-1){
	                    int from = seq.get(i), to = seq.get(i+1);
	                    indegree.put(to, indegree.getOrDefault(to, 0)+1);
	                    if (!graph.containsKey(from)) graph.put(from, new ArrayList());
	                    graph.get(from).add(to);
	                }
	                
	                
	            }
	        }
	        if (indegree.size() != n) return false; //check for the case org: [1] seqs: [[1],[2,3],[3,2]]
	        int idx = 0;
	        Queue<Integer> queue = new LinkedList();
	        for (int key: indegree.keySet()){
	            if (indegree.get(key) == 0) queue.add(key);
	        }
	        while (!queue.isEmpty()){
	            if (queue.size() != 1) return false;
	            int cur = queue.poll();
	            
	            if (idx >= n || cur != org[idx++]) return false;
	            if (graph.containsKey(cur)){
	                for (int nei: graph.get(cur)){
	                indegree.put(nei, indegree.get(nei)-1);
	                if (indegree.get(nei) == 0) queue.add(nei);
	                }
	            }
	            
	            
	        }
	        return idx == n;
	    }
}
