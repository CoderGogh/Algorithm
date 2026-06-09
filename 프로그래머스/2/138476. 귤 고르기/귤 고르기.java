import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
        public int solution(int k, int[] tangerine) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int answer = 0;
            for(int i = 0; i < tangerine.length; i++){
                map.put(tangerine[i], map.getOrDefault(tangerine[i], 0) + 1);
            }
            List<Integer> list =  map.values().stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
            for (int key : list) {
               k -= key;
               answer++;
               if(k<=0){
                   break;
               }
            }
            return answer;
        }
    }