/* author@ Qian Cai
 * Title@ Self Crossing
 * You are given an array x of n positive numbers. You start at point (0,0) 
 * and moves x[0] metres to the north, then x[1] metres to the west, 
 * x[2] metres to the south, x[3] metres to the east and so on. In other words, 
 * after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path 
crosses itself, or not.
 */
public class LeetCode335 {
	 /*
    3 senario:
    4th line cross 1st line
    5th line cross 1st line
    6th line cross 1st line
    7th line cross 1st line is the same as 7th cross 2nd (covered in 6 to 1 case)
    time: O(n) space: O(1)
    */
    public boolean isSelfCrossing(int[] x) {
        if (x.length <= 3) return false;
        for (int i = 3; i < x.length; i++){
            if (i >= 3 && x[i] >= x[i-2] && x[i-1] <= x[i-3]) return true;
            if (i >= 4 && x[i] + x[i-4]>= x[i-2] && x[i-1] == x[i-3]) return true;
            if (i >= 5 && x[i]+x[i-4] >= x[i-2]  && x[i-2] >= x[i-4] && x[i-1] + x[i-5] >= x[i-3] && x[i-3] >= x[i-5] && x[i-3] >= x[i-1]) return true;

        }
        return false;
    }
}
