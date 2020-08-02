/* author@ Qian Cai
 * Title@ Compare Version Numbers
 * 
 * 
 */
public class LeetCode165 {
	public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        for (int i = 0; i < Math.max(s1.length, s2.length); i++){
            int v1 = (i < s1.length ? Integer.valueOf(s1[i]) : 0);
            int v2 = (i < s2.length ? Integer.valueOf(s2[i]) : 0);
            //System.out.println(v1);
            //System.out.println(v2);
            if (v1 < v2) return -1;
            if (v1 > v2) return 1;
        }
        return 0;
    }
}
