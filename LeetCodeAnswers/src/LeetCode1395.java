/*author@Qian Cai
 * Title@ Count Number of Teams
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if:  (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 * Two Solution:
 * 1. Time: O(n^3), Space: O(1)
 * 2. Time: O(n^2), Space: O(1)
 * 3. Time: O(nlogm), space: O(m), BIT method， m is the size of rating range
 */
public class LeetCode1395 {
	public int numTeams(int[] rating) {
        int res = 0;
        int n = rating.length;
        if (n <= 2) return res;
        for (int i = 0; i < n-2; i++){
            for (int j = i+1; j < n-1; j++){
                for (int k = j+1; k < n; k++){
                    if (rating[i] < rating[j]){
                        if (rating[k] > rating[j]) res++;
                    } else if (rating[i] > rating[j]){
                        if (rating[k] < rating[j]) res++;
                    }
                }
            }
        }
        return res;
    }
	public int numTeams2(int[] rating) {
        int res = 0;
        int n = rating.length;
        if (n <= 2) return res;
        for (int i = 1; i < n-1; i++){
            //left[0], right[0] smaller than rating[i] on the left and right side
            //right[1], left[1] greater than rating[i] on the left and right side
            int[] left = new int[2], right = new int[2];
            for (int j = 0; j < n; j++){
                if (j < i) {
                    ++left[(rating[j] < rating[i])? 0:1];
                } else if (j > i){
                    ++right[(rating[j] < rating[i])?0:1];
                }
                
            }
            res += left[0]*right[1] + left[1]*right[0];
        }
        return res;
    }
	public int numTeams3(int[] rating) {
        int res = 0;
        int n = rating.length;
        if (n <= 2) return res;
        int[] left = new int[100001];
        int[] right = new int[100001];
        for (int r: rating) update(right, r, 1);
        for (int i = 0; i < n; i++){
            int cur = rating[i];
            update(right, cur, -1);
            res += (getLeft(left, cur-1))*(getRight(right, cur+1));
            res += (getRight(left, cur+1))*(getLeft(right, cur-1));
            update(left, cur, 1);
        }
        return res;
    }
    private void update(int[] bit, int index, int val){
        while (index < bit.length){
            bit[index] += val;
            index += index & (-index);
        }
    }
    private int getLeft(int[] bit, int index){
        int sum = 0;
        while (index > 0){
            sum += bit[index];
            index -= index&(-index);
        }
        return sum;
    }
    private int getRight(int[] bit, int index){
        return getLeft(bit, 100000)-getLeft(bit, index-1);
    }
}
