import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] day = new int[progresses.length];
        
        for(int i = 0; i< progresses.length; i++){
            int remain = 100 - progresses[i];
            day[i] = (remain + speeds[i] -1 ) / speeds[i];
        }
        List<Integer> answer = new ArrayList<>();

        int current = day[0];
        int count = 1;
        
        for(int i=1;i<day.length; i++){
            if(day[i]<= current){
                count ++;
            }
            else{
                answer.add(count);
                count = 1;
                current = day[i];
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(i->i).toArray();
    }
}