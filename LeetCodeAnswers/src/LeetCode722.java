/* author@ Qian Cai
 * Title@ Remove comments
 * time: O(n)
 * space: O(n)
 * 
 */
import java.util.*;
public class LeetCode722 {
	public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        List<String> ans = new ArrayList();
        for (String line: source){
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) newline = new StringBuilder();
            while (i < line.length()){
                //newBlock
                if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '*'){
                    inBlock = true;
                    i++;
                } 
                //end of block
                else if (inBlock && i+1 < line.length() && chars[i] == '*' && chars[i+1] == '/'){
                    inBlock = false;
                    i++;
                }
                //line comment
                else if (!inBlock && i+1 < line.length() && chars[i] == '/' && chars[i+1] == '/'){
                    break;
                } 
                //append regular letter
                else if (!inBlock){
                    newline.append(chars[i]);
                }
                i++;            
           }
            if (!inBlock && newline.length() > 0) ans.add(newline.toString());
        }
        return ans;
        
    }
}
