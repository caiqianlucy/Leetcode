/* author@ Qian Cai
 * Title@ Longest Substring with At Most Two Distince Characters
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 * 
 */
import java.util.*;
public class LeetCode159 {
	//Two Pointer , time, space: O(n)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 3) return s.length();
        int l = 0, r = 0;
        int res = 2;
        Map<Character, Integer> count = new HashMap();
        while (r < s.length()){
            if (count.size() <= 2){
                count.put(s.charAt(r), count.getOrDefault(s.charAt(r++), 0) + 1);
                
            }
            while (count.size() == 3){
                char c = s.charAt(l++);
                count.put(c, count.get(c)-1);
                if (count.get(c) == 0) count.remove(c);
            }
            res = Math.max(res, r - l);
            
        }
        return res;
        
    }
    //directly move left pointer by using collection default method
    class Solution {
        //Two Pointer , time, space: O(n)
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s.length() < 3) return s.length();
            int l = 0, r = 0;
            int res = 2;
            Map<Character, Integer> count = new HashMap();
            while (r < s.length()){
                if (count.size() <= 2){
                    count.put(s.charAt(r), r++);
                    
                }
                if (count.size() == 3){
                    int del = Collections.min(count.values());
                    count.remove(s.charAt(del));
                    l =  del + 1;
                }
                res = Math.max(res, r - l);
                
            }
            return res;
            
        }
    }
}
