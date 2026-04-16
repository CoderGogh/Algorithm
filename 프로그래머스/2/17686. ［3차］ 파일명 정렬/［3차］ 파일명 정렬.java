import java.util.*;
class Solution {
        public String[] solution(String[] files) {
            String[][] parsing = new String[files.length][4];

            // 파싱
            for (int i = 0; i < files.length; i++) {
                parsing[i][3] = files[i];   // 원본저장
                String s = files[i];
                int index = 0;
                while (index<s.length() && !Character.isDigit(s.charAt(index))){
                    index++;
                }
                parsing[i][0] = s.substring(0,index).toLowerCase();   // head
                int number_index = index;
                while (number_index<s.length() && Character.isDigit(s.charAt(number_index))){
                    number_index++;
                }
                parsing[i][1] = s.substring(index,number_index);    // number
                parsing[i][2] = s.substring(number_index).toLowerCase();  // tail
            }

            // 비교 후 스왑
            Arrays.sort(parsing, (a, b)-> {
                int comp = compare(a,b,0);
                if(comp != 0){
                    return comp;
                }
                else{   // 같은 경우 -> 길어짐
                    int numA = Integer.parseInt(a[1]);
                    int numB = Integer.parseInt(b[1]);
                    if(numA - numB > 0){
                        return 1;
                    }
                    else if(numA - numB < 0){
                        return -1;
                    }else{
                        return 0;
                    }
                }
            });
            String[] answer = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                answer[i] = parsing[i][3];
            }
            // 정렬된 순서를 리턴
            return answer;
        }
        public static int compare(String[]a, String[] b, int index){
            String position_a = a[index];
            String position_b = b[index];
            int comparision = position_a.compareTo(position_b);
            if(comparision>0){
                return 1;
            }else if(comparision<0){
                return -1;
            }else{
                return 0;
            }

        }
    }