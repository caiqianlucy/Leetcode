/* author@ Qian Cai
 * Title@ Encode and Decode Strings
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent
 *  over the network and is decoded back to the original list of strings.
 * 
 */
import java.util.*;
public class LeetCode271 {
	public class Codec {

	    // Encodes a list of strings to a single string.
	    public String encode(List<String> strs) {
	        StringBuilder sb = new StringBuilder();
	        for (String s: strs){
	            sb.append(intToString(s.length()));
	            sb.append(s);
	        }
	        return sb.toString();
	    }
	    public String intToString(int x){
	        char[] bytes = new char[4];
	        for (int i = 3; i > -1; i--){
	            bytes[3-i] = (char) (x >> (i*8) & 0xff);
	        }
	        //System.out.println(new String(bytes));
	        return new String(bytes);
	    }
	    public int strToInt(String bytes){
	        int res = 0;
	        for (char b: bytes.toCharArray()){
	            res = (res << 8) + (int)b;
	        }
	        return res;
	    }

	    // Decodes a single string to a list of strings.
	    public List<String> decode(String s) {
	        int i = 0; 
	        int n = s.length();
	        List<String> output = new ArrayList();
	        while (i < n){
	            int len = strToInt(s.substring(i, i+4));
	            i += 4;
	            output.add(s.substring(i, i+len));
	            i += len;
	        }
	        return output;
	    }
	}
}
