import java.util.*;
class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = {};
            answer = new int[id_list.length];
            HashMap<String, Block> map = new HashMap<>();
            for (String s: id_list
                 ) {
                map.put(s, new Block());
            }
            for (String s: report
                 ) {
                int index = s.indexOf(" ");
                String who = s.substring(0,index);
                String target = s.substring(s.indexOf(" ")+1);
                map.get(who).reporting.add(target); // 자신이 신고한 사람을 추가
                map.get(target).reported.add(who);  // 나를 신고한 사람을 추가
            }
            int i = 0;
            for (String s: id_list
                 ) {
                long count = map.get(s).reporting.stream().filter(item ->map.get(item).reported.size() >= k).count();
                answer[i] = (int)count;
                i++;
            }
            return answer;
        }
        static class Block{

            Set<String> reporting = new HashSet<>();// 내가 신고한 사람들(중복 가능)

            Set<String> reported = new HashSet<>();  // 나를 신고한 사람들

        }
    }