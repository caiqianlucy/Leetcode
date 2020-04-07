/*author@ Qian Cai
 * Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.

Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.

The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.
 * Time, Space: O(logn)
 */
import java.util.*;
public class FavoriteGenres {
    public Map<String, List<String>> favoriteGenes(Map<String, List<String>> userMap, Map<String, List<String>> songGenres){
    	Map<String, List<String>> res = new HashMap();
    	Map<String, String> songToGenres = new HashMap();
    	for (String key: songGenres.keySet()) {
    		for (String s: songGenres.get(key)) {
    			songToGenres.put(s, key); 
    		}
    	}
    	
    	for (String user: userMap.keySet()) {
    		Map<String, Integer> count = new HashMap();
    		int max = 0;
    		res.put(user, new ArrayList());
    		for (String song: userMap.get(user)) {
    			String genre = songToGenres.get(song);
    			count.put(genre, count.getOrDefault(genre, 0)+1);
    			max = Math.max(max, count.get(genre));
    		}
    		for (String g: count.keySet()) {
    			if (count.get(g) == max) {
    				res.get(user).add(g);
    			}
    		}
    	}
    	return res;
    }
}
