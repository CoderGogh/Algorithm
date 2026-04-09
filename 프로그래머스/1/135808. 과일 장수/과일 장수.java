import java.util.*; 
class Solution {
        public int solution(int k, int m, int[] score) {
            int answer = 0;
            Arrays.sort(score);
            int r = score.length%m;
            if(score.length%m != 0){//나머지가 있는 경우 --> 앞에서부터 버림
                for (int i = 1; i <= score.length/m; i++) {
                    int[] array = Arrays.copyOfRange(score,r+m*(i-1),r+m*i);    // 약간 등비수열..? 맞나?
                    int min = Arrays.stream(array).min().getAsInt();
                    answer += min * m;
                }
            }else{// 나머지가 없는 경우 --> 모두 다씀
                for (int i = 1; i <= score.length/m ; i++) {
                    int[] array = Arrays.copyOfRange(score, m* (i-1),m*i);
                    int min = Arrays.stream(array).min().getAsInt();
                    answer += min * m;
                }
            }
            return answer;
        }
    }