import java.util.PriorityQueue;
class Solution {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((a,b)-> b.compareTo(a)));
        public int solution(int[] priorities, int location) {
            int answer = 0;
            if(location > priorities.length){
                return -1;
            }
            // 2(1) 1(2) 3(3) 2(4)
            for (int i : priorities) {
                pq.offer(i);    // 3(3) 2(1) 2(4) 1(2)
            }
            while (!pq.isEmpty()) {
                for(int i = 0; i < priorities.length; i++) {    // 값이 같다면 인덱스 순서도 보장
                    if (priorities[i] == pq.peek()) {
                        pq.poll();  // 3(3)
                        answer++;
                        if(i == location){
                            pq.clear();
                            break;
                        }
                    }
                }
            }
            return answer;
        }
    }