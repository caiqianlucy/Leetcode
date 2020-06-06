/*author@Qian Cai
 * Title@Expressive Words
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy. 
 * 
 * 
 * Solution
Find the key and count for each word, compare to see whether the target and key of S is the same, then compare the count to see whether the word is stretchy

*/
import java.util.*;
public class LeetCode809 {
	public int expressiveWords(String S, String[] words) {
        RLE R = new RLE(S);
        int ans = 0;
        search: for (String word: words){
            RLE cur = new RLE(word);
            if (!R.key.equals(cur.key)) continue search;
            for (int i = 0; i < R.counts.size(); i++){
                int c1 = R.counts.get(i);
                int c2 = cur.counts.get(i);
                if ((c1 < 3 && c1 != c2) || (c1 < c2)) continue search;         
            }
            ans++;  
        }
        return ans;
    }
    class RLE {
        String key;
        List<Integer> counts;
        public RLE(String S){
            StringBuilder sb = new StringBuilder();
            counts = new ArrayList();
            char[] ca = S.toCharArray();
            int n = ca.length;
            int prev = -1;
            for (int i = 0; i < n; i++){
                if (i == n-1 || ca[i] != ca[i+1]){
                    sb.append(ca[i]);
                    counts.add(i-prev);
                    prev = i;
                }
            }
            key = sb.toString();
        }
    }
}
