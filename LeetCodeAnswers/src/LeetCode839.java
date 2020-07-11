/* author@ Qian Cai
 * Title@ Similar String Groups
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?
 * 
 */
import java.util.*;
public class LeetCode839 {
	public int numSimilarGroups(String[] A) {
        int n = A.length;
	    DSU dsu = new DSU(n);
	
	for (int i = 0; i < n-1; i++){
		for (int j = i+1; j < n; j++){
            if (helper(A[i], A[j])) dsu.union(i, j);
        }
	}
	
	return dsu.count;

}
    public Boolean helper(String s, String t){
        int i = 0, j = s.length()-1;
        
        while (s.charAt(i) == t.charAt(i) && i < j) i++;
        while (s.charAt(j) == t.charAt(j) && i < j) j--;
        
        if (i == j || i == j-1) return true;
        return s.substring(i+1, j).equals(t.substring(i+1, j));
    }
class DSU{
	int[] parents;
	int[] size;
 	int count;  //count of groups
	public DSU(int n){
		parents = new int[n];
		for (int i = 0; i < n; i++) parents[i] = i;
		size = new int[n];
		Arrays.fill(size, 1);
		count = n;
	}
	public int find(int x){
		while (parents[x] != x){
			parents[x] = parents[parents[x]]; //path compression
			x = parents[x];
		}
		return x;
	}
	public void union(int x, int y){
		int xp = find(x), yp = find(y);
		if (xp != yp){
			count--;
			if (size[xp] > size[yp]){
				parents[yp] = xp;
				size[xp] += size[yp];
			} else{
				parents[xp] = yp;
				size[yp] += size[xp];
			}
		}
	}
}
}
