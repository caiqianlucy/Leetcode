
import java.util.ArrayList;
import java.util.List;



//Time, space: O(4^n/(n^0.5))
public class LeetCode22 {
	public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }
    public void backtrack(List<String> ans, String cur, int left, int right, int n){
        if (cur.length() == n*2){
            ans.add(cur);
            return;
        }
        if (left < n){
            backtrack(ans, cur + '(', left+1, right, n);
        } if (right < left){
            backtrack(ans, cur + ')', left, right+1, n);
        }
        
    }
}
