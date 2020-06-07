/* author@ Qian Cai
 * Title@ Optimal Account Balancing
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 *  Time: O(n!)
 */
import java.util.*;
public class LeetCode465 {
	 public int minTransfers(int[][] transactions) {
	        List<Integer> accounts = buildAccounts(transactions);
	        int ans = 0;
	        Collections.sort(accounts);
	        ans += removingPaired(accounts);
	        return ans + dfs(0, accounts);
	        
	    }
	    //dfs to find the minimum transactions
	    public int dfs(int cur, List<Integer> accounts){
	        while (cur < accounts.size() && accounts.get(cur) == 0) cur++;
	        if (cur == accounts.size()) return 0;
	        int minTrans = Integer.MAX_VALUE;
	        for (int i = cur+1; i < accounts.size(); i++){
	            if (accounts.get(cur)*accounts.get(i) < 0){
	                accounts.set(i, accounts.get(i) + accounts.get(cur));
	                minTrans = Math.min(minTrans, 1+dfs(cur+1, accounts));
	                accounts.set(i, accounts.get(i)-accounts.get(cur));
	            }
	        }
	        return minTrans;
	    }
	    //build the list of each person's final balance
	    public List<Integer> buildAccounts(int[][] transactions){
	        Map<Integer, Integer> balances = new HashMap();
	        for (int[] transaction: transactions){
	            int from = transaction[0], to = transaction[1], val = transaction[2];
	            balances.put(from, balances.getOrDefault(from, 0) + val);
	            balances.put(to, balances.getOrDefault(to, 0) - val);
	        }
	        List<Integer> accounts = new ArrayList();
	        for (int val: balances.values()){
	            if (val != 0){
	                accounts.add(val);
	            }
	        }
	        return accounts;
	    }
	    //removing person A + person B has paired balances, like 3, -3, return the number of pair, each pair need one transaction
	    public int removingPaired(List<Integer> accounts){
	        int i = 0, j = accounts.size()-1;
	        int paired = 0;
	        while (i < j){
	            int sum = accounts.get(i) + accounts.get(j);
	            if (sum == 0){
	                paired++;
	                accounts.remove(j);
	                accounts.remove(i);
	                j -= 2;
	            } else if (sum > 0){
	                j--;
	            } else i++;
	        }
	        return paired;
	    }
}
