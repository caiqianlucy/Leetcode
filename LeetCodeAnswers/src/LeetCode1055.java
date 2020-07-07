/* author@ Qian Cai
 * Title@ Shortest Way to Form String
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

 

Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
Example 2:

Input: source = "abc", target = "acdbc"
Output: -1
Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
Example 3:

Input: source = "xyz", target = "xzyxz"
Output: 3
Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 

Constraints:

Both the source and target strings consist of only lowercase English letters from "a"-"z".
The lengths of source and target string are between 1 and 1000.
===================================================================
Solution1: time: O(MN)      space: O(1)
Solution2: time: O(NlogM)   space: O(m)
Solution3: time: O(N)       space: O(m)


 * 
 */
import java.util.*;
public class LeetCode1055 {
	public int shortestWay1(String source, String target) {
        int m = source.length(), n = target.length();
        int j = 0;
        int res = 0;
        while (j < n){
            boolean match = false;
            res++;
            for (int i = 0; i < m; i++){
                if (j == n) return res;
                if (source.charAt(i) == target.charAt(j)){
                    j++;
                    match = true;
                }
            }
            if (!match) return -1;
        }
        return res;
    }
	public int shortestWay2(String source, String target) {
        Map<Character, List<Integer>> pos = new HashMap(); //pos (key: char, list of index for this char)
        for (int i = 0; i < source.length(); i++){
            char c = source.charAt(i);
            if (!pos.containsKey(c)) pos.put(c, new ArrayList());
            pos.get(c).add(i);
        }
        int cur = -1;
        int res = 1;
        for (int j = 0; j < target.length(); j++){
            char c = target.charAt(j);
            if (!pos.containsKey(c)) return -1;
            cur = binarySearch(pos.get(c), cur);
            if (cur == -1){
                res++;
                cur = pos.get(c).get(0);
            }
        }
        return res;
    }
    public int binarySearch(List<Integer> list, int cur){
        int lo = 0, hi = list.size()-1;
        while (lo <= hi){
            int mi = (lo+hi)/2;
            if (list.get(mi) <= cur) lo = mi+1;
            else hi = mi-1;
        }
        return lo == list.size() ? -1: list.get(lo);
    }
    public int shortestWay3(String source, String target) {
        int m = source.length();
        int[][] pos = new int[26][m]; //pos[i][j] is i stands for char c (c-'a') j is the next char c >= j position
        for (int [] p: pos) Arrays.fill(p, -1);
        for (int i = 0; i < source.length(); i++){
            char c = source.charAt(i);
            pos[c-'a'][i] = i;
        }
        for (int i = 0; i < 26; i++){
            int prev = -1;
            for (int j = m-1; j >= 0; j--){
                if (pos[i][j] == -1 && prev != -1) pos[i][j] = prev;
                else if (pos[i][j] != -1) prev = pos[i][j];
            }
            
        }
        int cur = 0;
        int res = 1;
        for (int j = 0; j < target.length(); j++){
            char c = target.charAt(j);
            if (cur == m || pos[c-'a'][cur] == -1){
                res++;
                if (pos[c-'a'][0] == -1) return -1;
                cur = pos[c-'a'][0]+1;
            } else {
               cur = pos[c-'a'][cur] + 1;
            }        
        }
        return res;
    }
}
