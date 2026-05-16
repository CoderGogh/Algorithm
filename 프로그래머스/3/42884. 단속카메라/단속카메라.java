import java.util.*;
class Solution {
        public int solution(int[][] routes) {
            int answer = 0;
            int lastCamera = -30001;
            Arrays.sort(routes, (a,b)-> Integer.compare(a[0],b[0]));

            for (int[] route : routes ) {
                if(route[0] >  lastCamera){
                    answer ++;
                    lastCamera = route[1];
                }else{
                    lastCamera = Math.min(lastCamera,route[1]);
                }
            }
            return answer;
        }
    }