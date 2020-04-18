/* author@ Qian Cai
 * Title@ Find fibonacci number
 * Given the number k, return the minimum number of Fibonacci 
 * numbers whose sum is equal to k, whether a Fibonacci number 
 * could be used multiple times.

The Fibonacci numbers are defined as:

F1 = 1
F2 = 1
Fn = Fn-1 + Fn-2 , for n > 2.
It is guaranteed that for the given constraints we can always
 find such fibonacci numbers that sum k.
 * 
 */
import java.util.*;
public class LeetCode1414 {
	public int findMinFibonacciNumbers(int k) {
        List<Integer> fibList = new ArrayList();
        fibList.add(1);
        int first = 1, sec = 1;
        while (first + sec <= k){   
            int third = first +sec;
            fibList.add(third);
            first = sec;
            sec = third;
        }
        int n = fibList.size();
        if (fibList.get(n-1) == k) return 1;
        int step = 0, i = n-1;
        while (k > 0){
            int count = k/fibList.get(i);
            step  += count;
            k -=  count*fibList.get(i--);
        }
        return step;
    }
}
