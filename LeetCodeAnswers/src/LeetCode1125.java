/* author@ Qian Cai
 * Title@ Smallest Sufficient Team
 * In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].

Return any sufficient team of the smallest possible size, represented by the index of each person.

You may return the answer in any order.  It is guaranteed an answer exists.
 * 
 */
import java.util.*;
public class LeetCode1125 {
	public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> map = new HashMap();	
		int n = req_skills.length;
		for (int i = 0; i < n; i++) map.put(req_skills[i], i);
		int[] peMask = new int[people.size()];
		for (int i = 0; i < people.size(); i++){
			int mask = 0;
			for (String skill: people.get(i)){
				if (map.containsKey(skill)){
					mask |= (1<<map.get(skill));
				}
			}
				peMask[i] = mask;
		}
		Map<Integer, List<Integer>>  memo = new HashMap();
		List<Integer> res = dp((1<<n)-1, peMask, memo);
		int[] ans = new int[res.size()];
for (int i = 0; i < ans.length; i++) ans[i] = res.get(i);
 		return ans;
	}
	public List<Integer> dp(int mask, int[] peMask, Map<Integer, List<Integer>> memo){
		if (memo.containsKey(mask)) return memo.get(mask);
        
        List<Integer> list = new ArrayList();
        int minLen = Integer.MAX_VALUE;	
		for (int i = 0; i < peMask.length; i++){
			int skills = peMask[i]&mask;
			
			if (skills > 0){
				int left = mask-skills;
				if (left == 0){
					list = new ArrayList();
					list.add(i);
				}  else {
					List<Integer> rest = dp(left, peMask, memo);
					if (rest.size()+1 < minLen){
						list = new ArrayList(rest);
						list.add(i);
						minLen = rest.size()+1;
					}//end of if
				}// end of else
			} //end of if skills>0
			
		}
        memo.put(mask, list);
		return list;	
    }
}
