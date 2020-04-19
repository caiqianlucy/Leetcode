/*author@ Qian Cai
 * Title@ Display Table of Food Orders in a Restaurant
 * Given the array orders, which represents the orders that customers have done
 *  in a restaurant. More specifically orders[i]=[customerNamei,tableNumberi,
 *  foodItemi] where customerNamei is the name of the customer, tableNumberi 
 *  is the table customer sit at, and foodItemi is the item customer orders.
 *  

Return the restaurant's “display table”. The “display table” is a table whose 
row entries denote how many of each food item each table ordered. The first 
column is the table number and the remaining columns correspond to each food 
item in alphabetical order. The first row should be a header whose first 
column is “Table”, followed by the names of the food items. Note that the 
customer names are not part of the table. Additionally, the rows should be 
sorted in numerically increasing order.
 * 
 */
import java.util.*;
public class LeetCode1418 {
	 public List<List<String>> displayTable(List<List<String>> orders) {
	        List<Integer> tableList = new ArrayList();
	        Set<String> dishSet = new HashSet();
	        HashMap<Integer, HashMap<String, Integer>> table = new HashMap();
	        for (List<String> order: orders){
	            int tableId = Integer.parseInt(order.get(1));
	            String dish = order.get(2);
	            dishSet.add(dish);
	            if (!table.containsKey(tableId)){
	                table.put(tableId, new HashMap());
	                tableList.add(tableId);
	            } 
	            HashMap<String, Integer> orderMap = table.get(tableId);
	            orderMap.put(dish, orderMap.getOrDefault(dish, 0)+1);
	        }
	        List<String> dishList = new ArrayList<String>(dishSet);
	        Collections.sort(tableList);
	        Collections.sort(dishList);
	        dishList.add(0, "Table");
	        List<List<String>> res = new ArrayList();
	        res.add(dishList);
	        for (int i = 0; i < tableList.size(); i++){
	            List<String> row = new ArrayList();
	            int tableId = tableList.get(i);
	            HashMap<String, Integer> orderMap = table.get(tableId);
	            row.add(""+tableId);
	            for (int j = 1; j < dishList.size(); j++){
	                String dish = dishList.get(j);
	                if (orderMap.containsKey(dish)){
	                    row.add(orderMap.get(dish).toString());
	                } else {
	                    row.add("0");
	                }
	            }
	            res.add(row);
	        }
	        return res;
	    }
}
