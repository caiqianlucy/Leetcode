/* author@ Qian Cai
 * Title@ Minimum Possible Integer After at Most K Adjacent Swaps On Digits
 * Given a string num representing the digits of a very large integer and an integer k.

You are allowed to swap any two adjacent digits of the integer at most k times.

Return the minimum integer you can obtain also as a string.

 

Example 1:


Input: num = "4321", k = 4
Output: "1342"
Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
Example 2:

Input: num = "100", k = 1
Output: "010"
Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
Example 3:

Input: num = "36789", k = 1000
Output: "36789"
Explanation: We can keep the number without any swaps.
Example 4:

Input: num = "22", k = 22
Output: "22"
Example 5:

Input: num = "9438957234785635408", k = 23
Output: "0345989723478563548"
 

Constraints:

1 <= num.length <= 30000
num contains digits only and doesn't have leading zeros.
1 <= k <= 10^9
 */
import java.util.*;
public class LeetCode1505 {
	public String minInteger(String num, int k) {
        Map<Integer, Queue<Integer>> map = new HashMap(); //store the index of 0-9
        int n = num.length();
        StringBuilder sb = new StringBuilder();
        Set<Integer> visited = new HashSet(); //store moved index
        BIT bit = new BIT(n); //counts of moved index before i.
        for (int i = 0; i < n; i++){
            int cur = num.charAt(i)-'0';
            if (!map.containsKey(cur)) map.put(cur, new LinkedList());
            map.get(cur).add(i);
        }
        if (k >= n*(n-1)/2){
            return sorted(map);
        }
        //swap
        while (k > 0){
            boolean found = false;
            for (int i = 0; i < 10; i++){
                if (!map.containsKey(i)) continue;
                int idx = map.get(i).peek();
                int swap = idx-bit.query(idx);
                if (swap<=k){
                    k-=swap;
                    map.get(i).poll();
                    sb.append((char)(i+'0'));
                    if (map.get(i).size() == 0) map.remove(i);
                    visited.add(idx);
                    bit.update(idx);
                    found = true;
                    break;
                }
            }
            if (!found) break; 
        }
        //append rest nums
        for (int i = 0; i < n; i++){
            if (!visited.contains(i)) sb.append(num.charAt(i));
        }
        return sb.toString();
    }
    public String sorted(Map<Integer, Queue<Integer>> map){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++){
            if (map.containsKey(i)){
                for (int c = 0; c < map.get(i).size(); c++){
                    sb.append((char)(i+'0'));
                }
            }
        }
        return sb.toString();
    }
    class BIT{
        int[] arr;
        public BIT(int n){
            arr = new int[n+1];
        }
        public int query(int i){
            i++;
            int res = 0;
            while (i > 0){
                //System.out.println(i);
                res += arr[i];
                i -= (i&-i);
            }
            return res;
        }
        public void update(int i){
            i++;
            while (i < arr.length){
                
                arr[i]++;
                i += (i&-i);
            }
        }
    }
}
