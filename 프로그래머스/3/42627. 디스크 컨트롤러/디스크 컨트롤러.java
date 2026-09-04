import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int processing_time = 0;
        int jobcnt = jobs.length;
        int current_time = 0;
        int index = 0;
        
        Arrays.sort(jobs,(a,b) -> a[0] - b[0]);
        // int[] job = {요청시각,소요시각}
        // 작업 소요시간 > 작업 요청시각 > 작업 번호
        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (a,b)->{
                if(a[1] != b[1]){
                    return a[1] - b[1];
                }
                if(a[0] != b[0]){
                    return a[0] - b[0];
                }
                return 0;
            });
        
        while(index < jobcnt || !queue.isEmpty()){
            
            while(index < jobcnt && jobs[index][0] <= current_time){
                queue.offer(jobs[index]);
                index++;
            }
            if(queue.isEmpty()){
                current_time = jobs[index][0];
                continue;
            }
            int[] process = queue.poll();
            current_time += process[1];
            processing_time += current_time - process[0];
        }
        return processing_time/jobcnt;
    }
}