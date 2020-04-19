/* author@ Qian Cai
 * Title@ Reformat the String
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of
 *  lowercase English letters and digits).

You have to find a permutation of the string where no letter is followed by 
another letter and no digit is followed by another digit. That is, no two
 adjacent characters have the same type.

Return the reformatted string or return an empty string if it is impossible
 to reformat the string.
 * 
 */
public class LeetCode1417 {
	public String reformat(String s) {
        StringBuilder dig = new StringBuilder();
        StringBuilder let = new StringBuilder();
        int i = 0;
        while (i < s.length()){
            if (Character.isDigit(s.charAt(i))){
                dig.append(s.charAt(i));

            } else{
                let.append(s.charAt(i));
            }
            i++;
        }
        int len1 = dig.length(), len2 = let.length();
        if (Math.abs(len1-len2) > 1) return "";
        
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < Math.min(len1, len2); j++){
            res.append(dig.charAt(j));
            res.append(let.charAt(j));
        }
        if (len1 == len2) return res.toString();
        if (len1 > len2){
            res.append(dig.charAt(len1-1));
            return res.toString();
        }
        res.insert(0, let.charAt(len2-1));
        return res.toString();
        
    }
}
