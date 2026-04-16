import java.util.*;
class Solution {
        public int solution(int cacheSize, String[] cities) {
            int time = 0;
            Deque<String> queue = new LinkedList<>();   // 중간 요소 삭제를 위해
            if(cacheSize == 0){
                time = cities.length * 5;
                return time;
            }
            for (int i = 0; i < cities.length; i++) {
                String city = cities[i].toLowerCase();
                if(queue.contains(city)){
                    // 캐시에 있다면
                    queue.remove(city);
                    queue.addLast(city);
                    time += 1;
                }
                else{
                    // 캐시에 없다면
                    if(queue.size() == cacheSize){  // 꽉 차있다면
                        queue.pollFirst();
                    }
                    // 비어있다면
                    queue.addLast(city);
                    time+=5;
                }
            }
            return time;
        }
    }