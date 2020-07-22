/* author@ Qian Cai
 * Title@ Accounts Merge
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * only need to draw edges between one email between rest between accounts
    time complexity: O(sum(a(i)loga(i)) a(i) is the length of accounts[i]
   space: O(sum(a(i))

 */
import java.util.*;
public class LeetCode721 {
	 public List<List<String>> accountsMerge(List<List<String>> accounts) {
	        Map<String, List<String>> map = new HashMap();
			Map<String, String> email_to_name = new HashMap();
			Set<String> visited = new HashSet();
			List<List<String>> res = new ArrayList();
			for (List<String> account: accounts){
				String name = account.get(0); 
				String email = account.get(1);
				email_to_name.put(email, name);
				if (!map.containsKey(email)) map.put(email, new ArrayList());
				for (int i = 2; i < account.size(); i++){
					if (!map.containsKey(account.get(i))){                                     map.put(account.get(i), new ArrayList());
	                                                     }
					map.get(email).add(account.get(i));
					map.get(account.get(i)).add(email);		
	                email_to_name.put(account.get(i), name);		
				}
			}
	      
			for (String key: map.keySet()){
				if (!visited.contains(key)){
					//bfs for all connected componenet
					Queue<String> queue = new LinkedList();
					List<String> emails = new ArrayList();
					queue.add(key);
					visited.add(key);
					emails.add(key);
					while (!queue.isEmpty()){
						int size = queue.size();
						for (int i = 0; i < size; i++){
							String cur = queue.poll();
							for (String next: map.get(cur)){
								if (!visited.contains(next)){	
	                                  queue.add(next);
	                                  emails.add(next);
	                                  visited.add(next);		
								}
							}
						}
					}
					Collections.sort(emails);
					emails.add(0, email_to_name.get(key));
					res.add(emails);
				}
			}
	           return res;

	    }
}
