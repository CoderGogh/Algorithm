import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for(int a : scoville){
            min.add(a);
        }
        while(min.peek()< K){
            int first = min.poll();
            if(min.isEmpty()){
                return -1;
            }
            int second = min.poll();
            min.add(second*2 + first);
            answer++;
        }
        return answer;
    }
}