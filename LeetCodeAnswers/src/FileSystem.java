/* author@ Qian Cai
 * Title@ Design In-Memory File System
 * Design an in-memory file system to simulate the following functions:

ls: Given a path in string format. If it is a file path, return a list that 
only contains this file's name. If it is a directory path, return the list 
of file and directory names in this directory. Your output (file and directory
 names together) should in lexicographic order.

mkdir: Given a directory path that does not exist, you should make a new 
directory according to the path. If the middle directories in the path don't
 exist either, you should create them as well. This function has void return
type.

addContentToFile: Given a file path and file content in string format. If the
 file doesn't exist, you need to create that file containing given content. 
 If the file already exists, you need to append given content to original 
 content. This function has void return type.

readContentFromFile: Given a file path, return its content in string format.
 * 
 * Solution:
 * Time Complexity:
 * ls: O(m+n+klogk)
 * mkdir: O(m+n)
 * addContentToFile, readContentFromFile: O(m+n)
 */
import java.util.*;
public class FileSystem {
	class Dir{
        HashMap<String, Dir> dirs = new HashMap();
        HashMap<String, String> files = new HashMap();
    }
    Dir root;
    public FileSystem() {
        root = new Dir();
    }
    
    public List<String> ls(String path) {
        Dir t = root;
        List<String> files = new ArrayList();
        if (!path.equals("/")){
            String[] d = path.split("/");
            
            for (int i = 1; i < d.length-1; i++){
                t = t.dirs.get(d[i]);
            }
            if (t.files.containsKey(d[d.length-1])){
                files.add(d[d.length-1]);
                return files;
            } else {
                t = t.dirs.get(d[d.length-1]);
            }
        }
        files.addAll(new ArrayList<String>(t.dirs.keySet()));
        files.addAll(new ArrayList<String>(t.files.keySet()));
        Collections.sort(files);
        return files;
    }
    
    public void mkdir(String path) {
        Dir t = root;
        String[] d = path.split("/");
        for (int i = 1; i < d.length; i++){
            if (!t.dirs.containsKey(d[i])){
                t.dirs.put(d[i], new Dir());
            }
            t = t.dirs.get(d[i]);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i < d.length-1; i++){
            t = t.dirs.get(d[i]);
        }
        t.files.put(d[d.length-1], t.files.getOrDefault(d[d.length-1], "")+content);
    }
    
    public String readContentFromFile(String filePath) {
        Dir t = root;
        String[] d = filePath.split("/");
        for (int i = 1; i <d.length-1; i++){
            t = t.dirs.get(d[i]);
        }
        return t.files.get(d[d.length-1]);
    }
}
