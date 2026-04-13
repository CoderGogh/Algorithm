import java.util.*;
class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            List<Integer> answer = new ArrayList<>();
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < progresses.length; i++) {
                for (int j = 1; j <= 100; j++) {
                    if(progresses[i] + speeds[i] * j >=100){
                        queue.add(j);
                        break;
                    }
                }
            }
            while(!queue.isEmpty()){
                int beforeMax = queue.poll();
                int count = 1;
                while(!queue.isEmpty() && queue.peek() <= beforeMax){
                    queue.poll();
                    count++;
                }
                answer.add(count);
            }
            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }