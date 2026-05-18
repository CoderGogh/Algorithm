import java.util.*;
class Solution {
        public int[] solution(String[] gems) {
            Set<String> set = new HashSet<>(Arrays.asList(gems));
            int totalSet = set.size();

            Map<String,Integer> map = new HashMap<>();

            int left = 0;
            int minLen = Integer.MAX_VALUE;
            int[] answer = new int[2];

            for(int right = 0; right < gems.length; right++) {
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);

                while(map.size() == totalSet) {
                    int currentLen = right - left;
                    if(currentLen < minLen) {
                        minLen = currentLen;
                        answer[0] = left +1;    // 문제 1부터 시작
                        answer[1] = right +1;
                    }
                    map.put(gems[left], map.getOrDefault(gems[left], 0) -1);
                    if(map.get(gems[left]) == 0) {
                        map.remove(gems[left]);
                    }
                    left++;
                }
            }
            return answer;
        }
    }