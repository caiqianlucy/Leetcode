/* author@ QianCai
 * Title@ Brace Expansion
 * A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.
 * 
 * Solution
 * backtrack
 */
import java.util.*;
public class LeetCode1087 {
	public String[] expand(String S) {
        int i = S.indexOf('{');
        if (i == -1) return new String[]{S};
        int j = S.indexOf('}');
        String front = S.substring(0, i);
        String[] middle = S.substring(i+1, j).split(",");
        Arrays.sort(middle);
        if (j == S.length()){
            return join(front, middle, new String[]{""});
        }
        else return join(front, middle, expand(S.substring(j+1)));
        
    }
    public String[] join(String front, String[] middle, String[] end){
        List<String> res = new ArrayList();
        for (int i = 0; i < middle.length; i++){
            for (int j = 0; j < end.length; j++){
                String s= front + middle[i] + end[j];
                res.add(s);
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = res.get(i);
        return ans;
    }
}
