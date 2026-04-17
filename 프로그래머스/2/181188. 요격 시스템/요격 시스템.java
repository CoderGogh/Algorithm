import java.util.*;
class Solution {
        public int solution(int[][] targets) {
            int answer = 0;
            Arrays.sort(targets,(o1,o2)->{
                if(o1[1]== o2[1]){
                    return o1[0] - o2[0];   // 시작점 기준으로 정렬
                }
                return o1[1] - o2[1]; // 끝지점을 기준으로 정렬
            });
            int inRange = -1;
            for (int[] target : targets){
                int start = target[0];
                int end = target[1];
                if(inRange <= start){
                    answer++;
                    inRange = end;
                }
            }
            return answer;
        }
    }