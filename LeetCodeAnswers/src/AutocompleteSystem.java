/* author@ Qian Cai
 * Title@ Design Search Autocomplete System
 * Design a search autocomplete system for a search engine.
 *  Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of 
times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot 
degree (The first is the hottest one). If several sentences
 have the same degree of hot, you need to use ASCII-code 
 order (smaller one appears first).
If less than 3 hot sentences exist, then just return as 
many as you can.
When the input is a special character, it means the 
sentence ends, and in this case, you need to return an 
empty list.
 * 
 * Solution:
 * Trie+PriorityQueue
 * AutocompleteSystem O(k*l), k average length of a sentence, l is the len of sentences array
 * input() O(p+q+mlogm) 
 * p: length of the sentence , q is number of nodes in the trie, m is the list for sorting
 */
import java.util.*;
public class AutocompleteSystem {
	class TrieNode{
        Map<Character, TrieNode> children;
        Map<String, Integer> counts;
        boolean isWord;
        public TrieNode(){
            children = new HashMap();
            counts = new HashMap();
            isWord = false;
        }
    }
    class Pair {
        String s;
        int count;
        public Pair(String s, int c){
            this.s = s;
            count = c;
        }
    }
    TrieNode root;
    String prefix;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        for (int i = 0; i < sentences.length; i++){
            add(sentences[i], times[i]);
        }
    }
    public void add(String s, int count){
        TrieNode cur = root;
        for (char c: s.toCharArray()){
           
            if (!cur.children.containsKey(c)){
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
            cur.counts.put(s, cur.counts.getOrDefault(s, 0)+count);
        }
        cur.isWord = true;
    }
    
    public List<String> input(char c) {
        if (c == '#'){
            add(prefix, 1);
            prefix = "";
            return new ArrayList();
        }
        prefix += c;
        TrieNode cur = root;
        for (char cc: prefix.toCharArray()){
            if (!cur.children.containsKey(cc)){
                 return new ArrayList();
            }
            TrieNode next = cur.children.get(cc);
            
            cur = next;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> (a.count==b.count ? a.s.compareTo(b.s): (b.count-a.count)));
        for (String s : cur.counts.keySet()){
            pq.add(new Pair(s, cur.counts.get(s)));
        }
        List<String> res = new ArrayList();
        while (res.size() < 3 && !pq.isEmpty()){
            res.add(pq.poll().s);
        }
        return res;
    }
}
