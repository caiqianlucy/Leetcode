/* author@ Qian Cai
 * Title@ Analyze User Website Visit Pattern
 * We are given some website visits: the user with name username[i] 
 * visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order
 by the time of their visits.  (The websites in a 3-sequence are not 
 necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there 
is more than one solution, return the lexicographically smallest such
 3-sequence.
 * Time: O(n^4) Space(n^2)
 */
import java.util.*;
public class LeetCode1152 {
	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap();
        int n = username.length;
        for (int i = 0; i < n; i++){
            map.putIfAbsent(username[i], new ArrayList());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }
        Map<String, Integer> count = new HashMap(); //count of each three sequence
        String res = "";
        for (String user: map.keySet()){
            Set<String> set = new HashSet();
            List<Pair> list = map.get(user);
            Collections.sort(list, (a, b)->(a.time-b.time));//sort by time
            for (int i = 0; i < list.size()-2; i++){
                for (int j = i+1; j < list.size()-1; j++){
                    for (int k = j+1; k < list.size(); k++){
                        String key = list.get(i).web + "," + list.get(j).web + "," + list.get(k).web;
                        if (!set.contains(key)){
                            count.put(key, count.getOrDefault(key, 0)+1);
                            set.add(key);
                            
                        }
                        if (res == "" || count.get(res) < count.get(key) ||(count.get(res) == count.get(key) && res.compareTo(key) > 0)){
                                res = key;
                        }
                    }
                }
             }
            
        }
        String[] resArray = res.split(",");
       //System.out.println(res);
        return Arrays.asList(resArray);
    }
    private class Pair{
        int time;
        String web;
        public Pair(int t, String w){
            time = t;
            web = w;
        }
    }
}
