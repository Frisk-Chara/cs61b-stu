import java.util.*;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> map = new TreeMap<>();
        String wordlist = "abcdefghijklmnopqrstuvwxyz";
        int map_num = 1;
        for (char c: wordlist.toCharArray()) {
            map.put(c, map_num);
            map_num++;
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> square = new HashMap<>();
        for (Integer num: nums) {
            square.put(num, (int)Math.pow(num, 2));
        }
        return square;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        int num_count;
        List<String> lst = new ArrayList<>();
        Map<String, Integer> map = new TreeMap<>();
        for (String n : words) {
            lst.add(n);
        }
        while(!lst.isEmpty()) {
            num_count = 0;
            String temp = lst.getFirst();
            for (String n : lst) {
                if (n.equals(temp)) {
                    num_count++;
                }
            }
            map.put(temp, num_count);
            for (int i = 0; i < map.get(temp); i++) {
                lst.remove(temp);
            }
        }
        return map;
    }
}
