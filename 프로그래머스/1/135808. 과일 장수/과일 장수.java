import java.util.*; 
class Solution {
        public int solution(int k, int m, int[] score) {
            // k : 최고 점수, m : 묶어 팔 수 있는 갯수, score : 사과 점수 배열
            int answer = 0;
            // 1, 1, 2, 2, 3
            // m = 3 -> 앞 두 개 버림
            // m = 2 -> 앞 한 개 버림
            // 1, 2, 2, 2, 3
            // m = 3 -> 앞 두 개 버림
            // m = 2 -> 앞 한 개 버림
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