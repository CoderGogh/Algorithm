import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
        public int[] solution(String s) {
            int indx = s.length();
            int[] answer = new int[indx];
            List<String> list = new ArrayList<>();

            Arrays.stream(s.split("")).forEach(item ->list.add(item));

            for (int i = 0; i < indx; i++) {
                String now = s.substring(i,i+1);
                if(i == 0){
                    answer[i] = -1;
                }else{
                    int found = 0;
                    for (int j = i-1; j >=0 ; j--) {
                        String val = list.get(j);
                        if(val.equals(now)){
                            answer[i] = i-j;
                            found=1;
                            break;
                        }
                    }
                    if(found==0){
                        answer[i] = -1;
                    }
                }
            }
            return answer;
        }
    }