/* author@Qian Cai
 * title@ Guess the word
 * This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 * 
 */
import java.util.*;
public class LeetCode843 {
	public void findSecretWord(String[] wordlist, Master master) {
        int n = 0;
        while (n < 6){
            Map<String, Integer> map = new HashMap(); //the zero match count for word
            for (int i = 0; i < wordlist.length; i++){
                map.put(wordlist[i], 0);
                for (int j = 0; j < wordlist.length; j++){                 
                        int match = matchHelper(wordlist[i], wordlist[j]);   
                        if (match == 0) map.put(wordlist[i], map.get(wordlist[i])+1);
                    }
                }
            
            String guess = keyWithMinVal(map);
            System.out.println(guess);
            n = master.guess(guess);
            wordlist = update(wordlist, guess, n);
        }
   }        
    public int matchHelper(String s1, String s2){
        int res = 0;
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) == s2.charAt(i)) res++;
        }
        return res;
    }
    public String keyWithMinVal(Map<String, Integer> map){
        int temp = Integer.MAX_VALUE;
        String res = null;
        for (String key: map.keySet()){
            if (map.get(key) < temp){
                res = key;
                temp = map.get(key);
            } 
        }
        return res;
    }
    public String[] update(String[] wordlist, String guess, int match){
        List<String> list = new ArrayList();
        for (int i = 0; i < wordlist.length; i++){
            int n = matchHelper(wordlist[i], guess);
            if (n == match) list.add(wordlist[i]);
        }
        String[] res= new String[list.size()];
        for (int i = 0; i < res.length; i++) res[i] = list.get(i);
        return res;
    }
    interface Master {
    	     public default int guess(String word) {}
    }
}
