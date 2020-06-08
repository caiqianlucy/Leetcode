/* author@ Qian Cai
 * Title@ Confusing Number II
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.(Note that the rotated number can be greater than the original number.)

Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 * 
 */
import java.util.*;
public class LeetCode1088 {
	int ans = 0; 
    Map<Integer, Integer> map = new HashMap();
    public int confusingNumberII(int N) {       
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        for (int key: map.keySet()){
            if (key == 0) continue;
            backtrack((long)key, (long)map.get(key), N, 10);
        }
        return ans;
    }
    public void backtrack(long num, long rotated, int N, int digit){

        if (num != rotated) ans++;
        for (int key: map.keySet()){
          if ((long)(key+num*10) > (long)N) break;  
            backtrack(num*10+key, map.get(key)*digit+rotated, N, digit*10); //grow confusing number from one digit more(eg.1 digit to 2 digit)
        }
    }
}
