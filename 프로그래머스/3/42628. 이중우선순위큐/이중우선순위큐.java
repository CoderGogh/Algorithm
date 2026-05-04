import java.util.*;
class Solution {
        public int[] solution(String[] operations) {
            // priorityQueue * 2 + map(중복처리) 사용
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer,Integer> countMap = new HashMap<>();
            int totalSize = 0;
            for (String op: operations
                 ) {
                String []line = op.split(" ");
                String command = line[0];
                int num = Integer.parseInt(line[1]);

                if(command.equals("I")){
                    minHeap.add(num);
                    maxHeap.add(num);
                    countMap.put(num,countMap.getOrDefault(num,0)+1);
                    totalSize++;
                }else{
                    if(totalSize == 0){ // 큐가 비어있는 상황
                        continue;
                    }
                    if(num == 1){   // maxheap에서 지우는 상황
                        deleteElement(maxHeap,countMap);
                    }else{  // minheap에서 지우는 상황
                        deleteElement(minHeap,countMap);
                    }
                    totalSize--;
                }
            }
            if(totalSize == 0){
                return new int[]{0,0};
            }
            int max = getValid(maxHeap,countMap);
            int min = getValid(minHeap,countMap);

            return new int[]{max,min};
        }
        void deleteElement(PriorityQueue<Integer> heap,Map<Integer,Integer> map){
            while(!heap.isEmpty()){
                int peek = heap.poll();
                if(map.getOrDefault(peek,0)>0){
                    map.put(peek,map.get(peek)-1);
                    break;
                }
            }
        }
        int getValid(PriorityQueue<Integer> heap,Map<Integer,Integer> map) {
            while(!heap.isEmpty() && map.getOrDefault(heap.peek(), 0) == 0){
                heap.poll();
            }
            return heap.peek();
        }
    }