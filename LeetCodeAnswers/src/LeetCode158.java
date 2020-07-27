/* author@ Qian Cai
 * Title@ Read N Characters
 * 
 */
public class LeetCode158 {
	public class Solution extends Reader4 {
	    /**
	     * @param buf Destination buffer
	     * @param n   Number of characters to read
	     * @return    The number of actual characters read
	     */
	    char[] buf4 = new char[4];
	    int left = 0; //actural character read
	    int right = 0; // number return from last read4 call
	    public int read(char[] buf, int n) {
	        int i = 0;
	        while (i < n){
	            if (left == right){
	                left = 0;
	                right = read4(buf4);
	                if (right == 0) return i; //no more char to read
	            }
	            while (i < n && left < right){
	                buf[i++] = buf4[left++];
	            }
	        }
	        return i;
	    }
	}
}
