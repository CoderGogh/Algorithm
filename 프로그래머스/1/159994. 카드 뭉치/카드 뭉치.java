import java.util.Queue;
import java.util.ArrayDeque;
class Solution {
        public String solution(String[] cards1, String[] cards2, String[] goal) {
            String answer = "Yes";
            Queue<String> dack1 = new ArrayDeque<>();
            Queue<String> dack2 = new ArrayDeque<>();
            for (String a: cards1
                 ) {
                dack1.add(a);
            }
            for (String a:cards2
                 ) {
                dack2.add(a);
            }

            int total = goal.length;
            int i = 0;
            while(i < total){
                if(!dack1.isEmpty()){
                    if(dack1.peek().equals(goal[i])){
                        dack1.poll();
                        i++;
                        continue;
                    }
                }
                if(!dack2.isEmpty()){
                    if(dack2.peek().equals(goal[i])){
                        dack2.poll();
                        i++;
                        continue;
                    }
                }
                answer="No";
                break;
            }
            return answer;
        }
    }