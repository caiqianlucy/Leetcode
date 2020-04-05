/*author@ Qian Cai
 * Title@ Rearrange String k Distance Apart
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * Time: O(n) Space: O(1)
 */
import java.util.*;
public class LeetCode358 {
	public String rearrangeString(String s, int k) {
        if (k == 0) return s;
        int[] arr = new int[26];
        for (char c: s.toCharArray()){
            arr[c-'a']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b)->(arr[b]==arr[a]?(b-a):(arr[b]-arr[a])));
        for (int i = 0; i < 26; i++){
            if (arr[i] > 0) pq.add(i);
        }
        Queue<Integer> wait = new LinkedList();
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()){
            int c = pq.poll();
            sb.append((char)(c+'a'));
            arr[c]--;
            wait.add(c);
            if (wait.size() < k) continue;
            
            int f = wait.poll();
            if (arr[f] > 0) pq.add(f);
            
        }
        return sb.length() == s.length() ? sb.toString(): "";
    }
}
