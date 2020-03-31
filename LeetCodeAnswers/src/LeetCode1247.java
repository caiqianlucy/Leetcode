/*author@ Qian Cai
 * Title@ Minimum Swaps to Make Strings Equal
 * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].

Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
 * 
 * Solution: Time: O(n) Space: O(1)
 * 1. for equal position skip, 
 * 2. for odd number of xy and yx cases, (eg, s1, xy, s2, yx) need to 2 swaps to make equal
 * 3. for even number of xy and yx case, need 1 swap to make equal
 * 4. if xy+yx is not even, cannot match(eg, s1, xxx, s2, yyy)
 */
public class LeetCode1247 {
	public int minimumSwap(String s1, String s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) == s2.charAt(i)) continue;
            if (s1.charAt(i) == 'x') xy++;
            else yx++;
        }
        return ((xy + yx) % 2 == 0 ) ?(xy/2+yx/2+(xy%2)*2) : -1;
    }
}
