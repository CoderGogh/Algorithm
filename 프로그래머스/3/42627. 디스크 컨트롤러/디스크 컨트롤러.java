import java.util.*;
class Solution {
        public int solution(int[][] jobs) {
            // job[i][j]
            // 1차배열의 인덱스(작업번호_3순위)
            // 2차배열의 인덱스(i,j) j: 소요시간(1순위) -> i: 요청시점(2순위)

            // 0. 초기화
            int time = 0;
            int completed = 0;
            int volume = jobs.length;
            int jobIndex = 0;
            int responseTime =0;
            Arrays.sort(jobs,(a, b)-> a[0] - b[0]);         // 요청시점을 기준으로 sort
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> a[1] - b[1]); // 소요시간이 짧은 것으로 sort


            // while(작업의 갯수 완료 -- ){}
            while(completed < volume){
                // 2. 현재시간을 기준으로, 요청시점 이전에 해당되는 모든 작업을 queue에 add
                while (jobIndex < jobs.length && time >= jobs[jobIndex][0]){
                    pq.add(jobs[jobIndex++]);
                }
                // 큐에 다음 작업이 있거나 없거나
                if(pq.isEmpty()){   // 없는 경우
                    // 다음 작업 시작시간으로 이동
                    time = jobs[jobIndex][0];
                }else{  // 큐에 다음 작업이 있는 경우
                    int[] job = pq.poll();
                    responseTime += (time + job[1] - job[0]);
                    time += job[1];
                    completed ++;
                }
            }
            return responseTime/volume;
        }
    }