import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode819 {
	public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        Map<String, Integer> map = new HashMap();
        Set<String> set = new HashSet();
        for (String b: banned) set.add(b);
        StringBuilder word = new StringBuilder();
        for (char c: paragraph.toCharArray()){
            if (Character.isLetter(c)) word.append(c);
            else{
                add(word, map, set);
                word = new StringBuilder();
            }
        }
        add(word, map, set);
        int cnt = 0;
        String res = "";
        for (String key: map.keySet()){
            if (map.get(key) > cnt){
                cnt = map.get(key);
                res = key;
            }
        }
        return res;
    }
    private void add(StringBuilder word, Map<String, Integer> map, Set<String> set){
             if (word.length() > 0){
                 String w = word.toString();
                 if (!set.contains(w)){
                 map.put(w, map.getOrDefault(w, 0)+1);
                 }
             } 
    }
    /*private String clean(String s){
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()){
            if (Character.isLetter(c)){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    */
}
