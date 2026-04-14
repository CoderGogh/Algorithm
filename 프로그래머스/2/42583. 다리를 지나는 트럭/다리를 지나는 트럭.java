import java.util.*;
class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int time = 0;
            int currentWeight = 0;
            int truckIdx = 0;
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < bridge_length; i++) {
                q.add(0);
            }
            while(truckIdx< truck_weights.length){
                time++;
                currentWeight -= q.poll();
                if(currentWeight + truck_weights[truckIdx]<= weight){
                    q.offer(truck_weights[truckIdx]);
                    currentWeight += truck_weights[truckIdx];
                    truckIdx++;
                }else{
                    q.offer(0);
                }
            }

            return time + bridge_length;
        }
    }