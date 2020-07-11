/* author@ Qian Cai
 * Title@ 24 Game
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether 
 * they could operated through *, /, +, -, (, ) to get the value of 24.
 * 
 */
public class LeetCode679 {
	public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList();
        for (int n: nums){
            list.add((double)n);
        }
        return helper(list);
    }
    public boolean helper(List<Double> list){
    	int n = list.size();
        if (n == 0) return false;
        if (n == 1) return Math.abs(list.get(0) - 24.0) < 1e-6;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i != j){
                    List<Double> temp = new ArrayList();
                    for (int k = 0; k < n; k++){
                        if (k != i && k != j) temp.add(list.get(k));
                    }
                    for (int k = 0; k < 4; k++){
                        if (k < 2 && j > i) continue;
                        switch(k){
                            case 0:
                                temp.add(list.get(i) + list.get(j));
                                break;
                            case 1:
                                temp.add(list.get(i)*list.get(j));
                                break;
                            case 2:
                                temp.add(list.get(i)-list.get(j));
                                break;
                            case 3:
                                if (list.get(j) != 0){
                                    temp.add(list.get(i)/list.get(j));
                                }
                                else return false;
                                break;
                            default:
                                return false;
                        }
                        if (helper(temp)) return true;
                        temp.remove(temp.size()-1);
                    }
                }
            }
        }
        return false;
    }
}
