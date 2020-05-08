/* Author@ Qian Cai
 * Title@ Design In-Memory File System
 * Solution 2 of FileSystem
 */
import java.util.*;
public class LeetCode588 {
	class FileSystem {
	    class File {
	        boolean isFile = false;
	        Map<String, File> children = new HashMap();
	        String content = "";
	    }
	    File root = null;
	    public FileSystem() {
	        root = new File();
	    }
	    
	    public List<String> ls(String path) {
	        String[] dirs = path.split("/");
	        File node = root;
	        List<String> res = new ArrayList();
	        String name = "";
	        for (String dir: dirs){
	            if (dir.length() == 0) continue;
	            if (!node.children.containsKey(dir)) return res;
	            node = node.children.get(dir);
	            name = dir;
	        }
	        if (node.isFile) res.add(name);
	        else res.addAll(new ArrayList<String>(node.children.keySet()));
	        Collections.sort(res);
	        return res;
	    }
	    
	    public void mkdir(String path) {
	        String[] dirs = path.split("/");
	        File node = root;
	        for (String dir: dirs){
	            if (dir.length()==0) continue;
	            if (!node.children.containsKey(dir)){
	                node.children.put(dir, new File());
	            }
	            node = node.children.get(dir);
	        }
	        
	    }
	    
	    public void addContentToFile(String filePath, String content) {
	        String[] dirs = filePath.split("/");
	        File node = root;
	        for (String dir: dirs){
	            if (dir.length()== 0) continue;
	            if (!node.children.containsKey(dir)){
	                node.children.put(dir, new File());
	            }
	            node = node.children.get(dir);
	        }
	        node.isFile = true;
	        node.content += content;
	    }
	    
	    public String readContentFromFile(String filePath) {
	        String[] dirs = filePath.split("/");
	        File node = root;
	        for (String dir: dirs){
	            if (dir.length() == 0) continue;
	            node = node.children.get(dir);
	        }
	        return node.content;
	    }
	}
}
