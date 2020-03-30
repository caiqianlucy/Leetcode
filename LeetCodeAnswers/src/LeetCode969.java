import java.util.*;

/*author@Qian Cai
 * Title@Pancake Sorting
 * Time: O(n^2) Space: O(n) Flip from largest number to smallest number
 * Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.

Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 * 
 */
public class LeetCode969 {
	public List<Integer> pancakeSort(int[] A) {
        int n = A.length;
        List<Integer> res = new ArrayList();
        Integer[] B = new Integer[n];
        for (int i = 0; i < n; i++) B[i] = i+1;
        Arrays.sort(B, (a, b)->A[b-1]-A[a-1]);
        for (int i: B){
            for (int j: res){
                if (i <= j){
                    i =  j+1-i;
                }
            }
            res.add(i);
            res.add(n--);
        }
        return res;
    }
}
