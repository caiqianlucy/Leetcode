/* author@ Qian Cai
 * Title@ X of a kind in a deck of cards
 * In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.
 * 
 */
import java.util.*;
public class LeetCode914 {
	public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap();
        for (int d: deck){
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        int gcd = -1;
        for (int key: map.keySet()){
            if (gcd == -1) gcd = map.get(key);
            else gcd = helper(gcd, map.get(key));
        }
        return gcd >= 2;
    }
    //helper for look for greatest common divisor
    public int helper(int a, int b){
        if (b == 0) return a;
        return helper(b, a%b);
    }
}
