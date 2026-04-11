import java.util.*;
class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";

            HashMap<String, Integer> map = new HashMap<>();
            for (String s : participant
            ) {
                if (map.containsKey(s)) {
                    int a = map.get(s);
                    map.put(s, a + 1);
                    continue;
                }
                map.put(s, 1);
            }
            for (String s: completion
                 ) {
                map.put(s, map.get(s) -1);
            }
            for (String s: map.keySet()
                 ) {
                if(map.get(s)!=0){
                    answer= s;
                    break;
                }
            }
            return answer;
        }
    }