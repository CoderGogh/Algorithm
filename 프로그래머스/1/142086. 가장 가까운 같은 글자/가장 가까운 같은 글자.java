import java.util.*;
class Solution {
        public int[] solution(String s) {
            int[] answer = new int[s.length()];

            Arrays.fill(answer,-1);
            for (int i = 0; i < answer.length; i++) {
                for (int j = i-1; j >=0 ; j--) {
                    if(s.charAt(j) == s.charAt(i)){
                        answer[i]= i-j;
                        break;
                    }
                }
            }
            return answer;
        }
    }