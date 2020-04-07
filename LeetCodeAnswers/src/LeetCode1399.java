/*author@ Qian Cai
 * Title@ Count Largest Group
 * Given an integer n. Each number from 1 to n is grouped according to the sum of its digits. 

Return how many groups have the largest size.
 * 1. Time, Space: O(n)
 * 2. Time, Space: o(logn)
 */
import java.util.*;
public class LeetCode1399 {
	public static int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap();
        int mCnt = 0, res = 0;
        for (int i = 1; i <= n; i++){
            int sum = helper(i);
            map.put(sum, map.getOrDefault(sum, 0)+1);
            mCnt = Math.max(mCnt, map.get(sum));
        }
        for (int key: map.keySet()){
            if (map.get(key) == mCnt){
                res++;
            }
        }
        return res;
    }
    public static int helper(int n){
        int res = 0;
        while (n != 0){
            res += n%10;
            n/=10;
        }
        return res;
    }
    //count binary representation digits sum
    public static int countLargestGroup2(int n) {
    	String s = binary(n);
    	System.out.println(s);
    	int m = s.length();
    	int[] count = new int[m+1];
    	int prev = 0;
    	for (int i = 0; i < m; i++) {
    		if (s.charAt(i) == '1') {
    			update(count, m-i-1, prev++);//counts from 1 to 10000
    		}
    		
    	}
    	int max = 0;
    	for (int c: count) {
    		if (c > max) max = c;
    	}
        System.out.println("max is " + max);
    	int res = 0;
    	for (int c: count) {
    		if (c == max) res++;
    	}
    	return res;
    }
    public static void update(int[] count, int n, int prev) {
    	for (int i = 1; i <= n; i++) {
    		count[prev+i] += combination(n, i);	
    		System.out.println(prev+i);
    		System.out.println(count[prev+i]);
    	}
    	count[1]++;
    }
    public static int combination(int n, int i) {
    	int one = 1, two = 1;
    	while (i > 0) {
    		one *= n--;
    		two *= i--;
    	}
    	return one/two;
    }
    
    public static String binary(int n){
        StringBuilder sb = new StringBuilder();
        while (n != 0){
            sb.insert(0, n%2);
            n/=2;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
    	System.out.println(countLargestGroup2(13));
    	
    }
}
