import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i = 0;i<commands.length; i++){
            int c[] = commands[i];
            int first = c[0]-1;
            int last = c[1];
            int find = c[2] -1;
            int[] temp = Arrays.copyOfRange(array,first,last);
            Arrays.sort(temp);
            answer[i] = temp[find];
        }
        return answer;
    }
}