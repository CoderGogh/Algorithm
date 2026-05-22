    class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;
            int current = 1;
            int stationIdx = 0;
            int cover = 2 * w +1;
            while (current <= n) {
                if(stationIdx < stations.length && current >= stations[stationIdx]  -w ) {
                    current = stations[stationIdx] + w + 1 ;    // jump
                    stationIdx++;
                }else{
                    answer++;
                    current += cover;
                }
            }
            return answer;
        }
    }
