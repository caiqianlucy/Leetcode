/* author@ Qian Cai
 * Title@ Split Array into Consecutive Subsequences
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more subsequences such that each subsequence consists of consecutive integers and has length at least 3.

Keep track of the counts of incomplete list with one, two and >= 3 elements
Time: O(n)
Space: O(1)
 * 
 */
public class LeetCode659 {
	public boolean isPossible(int[] nums) {
        if (nums.length < 3) return false;
        int one = 0, two = 0, three = 0;
        int prev = 0;
        int i = 0;
        while (i < nums.length){
            if (nums[i] != prev+1 && (one != 0 || two != 0)) return false; //incomplete list less than three element 
            int nextIdx = findNext(nums, i); // find next element that is not equal to nums[i]
            int count = nextIdx-i;
            if (count < one+two) return false; //the number of current element cannot append to previous incomplete list;
            /* if new element counts greater than all previous lists
            append new element to previous list with >= 3 element first,
            then 2 element and 1 element
            */
            if (three < count-one-two){
                int three_n = two + three;
                int two_n = one;
                int one_n = count-one-two-three;
                one = one_n;
                two = two_n;
                three = three_n;
            }  else {
               /*if count <= one+two+three
               append to one element list first,then append to two element, then append to three element
               */
                three = count-one;
                two = one;
                one = 0;
            }
            prev = nums[i];
            i = nextIdx;
        }
        if (one != 0 || two != 0) return false;
        return true;
    }
    public int findNext(int[] nums, int i){
        for (int j = i+1; j < nums.length; j++){
            if (nums[j] != nums[i]) return j;
        }
        return nums.length;
    }
}
