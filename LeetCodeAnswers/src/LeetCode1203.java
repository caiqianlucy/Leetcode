/* author@ Qian Cai
 * Title@ Sort Items by Groups Respecting Dependencies
 * There are n items each belonging to zero or one of m groups where 
 * group[i] is the group that the i-th item belongs to and it's equal to 
 * -1 if the i-th item belongs to no group. The items and the groups are 
 * zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted 
list.
There are some relations between these items where beforeItems[i] is a list 
containing all the items that should come before the i-th item in the sorted
 array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty 
list if there is no solution.
 * ========================================================================
 * Two level topological sort
 * Time Space complexity,[: O(V+E)
 * ========================================================================
 */
import java.util.*;

public class LeetCode1203 {
     public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    	//assign each item without group as a new group
         for (int i = 0; i < group.length; i++){
             if (group[i] == -1) group[i] = m++;
             //System.out.println(i + " group number is " + group[i]);
         }
         //indegree for each group and each item
         Map<Integer, Integer> groupIndegree = new HashMap();
         int[] itemIndegree = new int[n];
         Map<Integer, Set<Integer>> groupGraph = new HashMap(); 
         Map<Integer, Set<Integer>> itemGraph= new HashMap();
         Map<Integer, Set<Integer>> g2i = new HashMap(); // group number to item number
         for (int i = 0; i < n; i++){
             if (!g2i.containsKey(group[i])) g2i.put(group[i], new HashSet());
             g2i.get(group[i]).add(i);
         }
         for (int i = 0; i < n; i++){
             List<Integer> list = beforeItems.get(i);
             if (list.size() == 0) {
                 if (!groupIndegree.containsKey(group[i])) groupIndegree.put(group[i], 0);   
             }
             for (int beforeItem: list){
                 //System.out.println(group[beforeItem] +" " + beforeItem + " " + group[i] + " " + i);
                 if (group[beforeItem] != group[i]){
                     if (!groupGraph.containsKey(group[beforeItem])) groupGraph.put(group[beforeItem], new HashSet());
                     if (!groupGraph.get(group[beforeItem]).contains(group[i])){
                         groupGraph.get(group[beforeItem]).add(group[i]);
                         groupIndegree.put(group[i], groupIndegree.getOrDefault(group[i], 0) + 1);
                     }
                     
                     //System.out.println(groupIndegree.get(group[i]) + " " + group[i]);
                 } else{
                     if (!itemGraph.containsKey(beforeItem)) itemGraph.put(beforeItem, new HashSet());
                     itemGraph.get(beforeItem).add(i);
                     //System.out.println(itemGraph.get(beforeItem));
                     itemIndegree[i]++;
                 }
             }
         }
         Queue<Integer> queue = new LinkedList(); //level1 topological sort on group
         for (int key: groupIndegree.keySet()){
             if (groupIndegree.get(key) == 0) {
                 //System.out.println(key);
                 queue.add(key);
             }
         }
         List<List<Integer>> orderList = new ArrayList();
         while (!queue.isEmpty()){
             int size = queue.size();
             //System.out.println(size);
             for (int i = 0; i < size; i++){
                 int cur = queue.poll();
                 //level2 topological sort on item
                 ArrayList<Integer> list = new ArrayList();
                 Queue<Integer> itemQueue = new LinkedList();
                 for (int j: g2i.get(cur)){
                     if (itemIndegree[j] == 0) itemQueue.add(j);
                 }
                 while (!itemQueue.isEmpty()){
                     int itemSize = itemQueue.size();
                     for (int k = 0; k < itemSize; k++){
                         int itemCur = itemQueue.poll();
                         //System.out.println(itemCur);
                         list.add(itemCur);
                         if (itemGraph.containsKey(itemCur)){
                             for (int inext: itemGraph.get(itemCur)){
                             //System.out.println(inext + " " + itemCur);
                             itemIndegree[inext]--;
                             if (itemIndegree[inext] == 0) itemQueue.add(inext);
                             }
                         }
                     }
                 }
                 if (list.size() != g2i.get(cur).size()) return new int[]{}; 
                 orderList.add(new ArrayList(list));
                 if (groupGraph.containsKey(cur)){
                     for (int nextGroup: groupGraph.get(cur)){
                     groupIndegree.put(nextGroup, groupIndegree.get(nextGroup)-1);
                     //System.out.println(nextGroup + " " +groupIndegree.get(nextGroup));
                     if (groupIndegree.get(nextGroup) == 0) queue.add(nextGroup);
                     }
                 }
                 
             }    
         }
         if (orderList.size() != groupIndegree.size()) return new int[]{};
         int[] res = new int[n];
         int cnt = 0;
         for (List<Integer> list: orderList){
             for (int l: list){
                 res[cnt++] = l;
             }
         }
         return res;
     
     }
}
