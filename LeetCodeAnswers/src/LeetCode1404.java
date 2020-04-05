/*author@ Qian Cai
 * Title@ Number of Steps to Reduce a Number in Binary Representation to One
 * Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:

If the current number is even, you have to divide it by 2.

If the current number is odd, you have to add 1 to it.

It's guaranteed that you can always reach to one for all testcases.
*Time:O(n) Space: O(n)
 */
public class LeetCode1404 {
	public int numSteps(String s) {
        int step = 0;
        while (!s.equals("1")){
            s = next(s);
            step++;
        }
        return step;
    }
    public String next(String s){
        int n = s.length();
        int i = n-1;
        if (s.charAt(i) == '0'){
            return s.substring(0, i);
        } else{
            while (i-1 >= 0 && s.charAt(i-1) != '0') i--;
            if (i == 0){
                StringBuilder res = new StringBuilder();
                res.append("1");
                for (int k = 0; k < n; k++) res.append("0");
                return res.toString();
            }
            else return s.substring(0, i-1)+ "1" + s.substring(i);
        }
    }
}
