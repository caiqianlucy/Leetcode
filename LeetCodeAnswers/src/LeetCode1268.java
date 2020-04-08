/*author@ Qian Cai
 * Title@ Search Suggestion System
 * Given an array of strings products and a string searchWord. We want to design a
 *  system that suggests at most three product names from products after each 
 *  character of searchWord is typed. Suggested products should have common prefix 
 *  with the searchWord. If there are more than three products with a common prefix 
 *  return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord 
is typed. 
 * * m = length of products, n = products.length, L = searchWord.length()
*Time complexity: sorting cost O(m*nlogn), building Trie O(m*n), search L, total will be (m*nlogn+L)
* Space complexity: Math.max(L, n)*m
 */
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
public class LeetCode1268 {
	public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        Trie root = new Trie();
        for (String p: products){
            Trie cur = root;
            for (char c: p.toCharArray()){
                if (cur.children[c-'a'] == null) cur.children[c-'a'] = new Trie();
                cur = cur.children[c-'a'];
                if (cur.list.size() < 3) cur.list.add(p);
            }
        }
        List<List<String>> res = new ArrayList();
        for (char c: searchWord.toCharArray()){
            if (root != null){
                root = root.children[c-'a'];
            }
            res.add(root == null ? new ArrayList(): root.list);
        }
        return res;
    }
   public class Trie{
         Trie[] children;
         List<String> list;
         public Trie(){
             children = new Trie[26];
             list = new LinkedList();
         }
    }

}
