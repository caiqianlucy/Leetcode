/* author@ Qian Cai
 * Title@ UTF-8 Validation
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
 */
public class LeetCode393 {
	public boolean validUtf8(int[] data) {
        int count = 0;
        for (int c : data){
            if (count == 0){
                if (c >> 3 == 0b11110) count = 3;  //3 byte character
                else if (c >> 4 == 0b1110) count = 2; 
                else if (c >> 5 == 0b110) count = 1;
                else if (c >> 7 != 0) return false;
            } else {
                if (c >> 6 != 0b10) return false;
                count--;
            }
        }
        return count == 0;
    }
}
